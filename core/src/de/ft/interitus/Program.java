/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.Logging.DebugPrinter;
import de.ft.interitus.Logging.LoggerInit;
import de.ft.interitus.UI.CheckShortcuts;
import de.ft.interitus.UI.InputManager;
import de.ft.interitus.UI.Theme.ThemeManager;
import de.ft.interitus.UI.UI;
import de.ft.interitus.datamanager.programmdata.Data;
import de.ft.interitus.datamanager.programmdata.experience.ExperienceManager;
import de.ft.interitus.datamanager.userdata.UserDataInit;
import de.ft.interitus.loading.Loading;
import de.ft.interitus.loading.SplashScreen;
import de.ft.interitus.plugin.PluginManagerHandler;
import de.ft.interitus.plugin.PluginSandboxSecurityPolicy;
import de.ft.interitus.plugin.ProgramRegistry;
import de.ft.interitus.projecttypes.Importer.Importer;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.FolderUtils;
import de.ft.interitus.utils.UserNameGetter;


import java.nio.file.Path;
import java.security.Policy;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class Program extends Game {

    public static Program INSTANCE;
    public static boolean inLoading = true;
    public static Logger logger = Logger.getLogger(Program.class.getName());


    public Program() {


        INSTANCE = this;
    }

    @Override
    public void create() {



        Gdx.graphics.setTitle("Interitus - Home Version");
        Var.programingSpace = new ProgramingSpace();
        Var.welcome = new  Welcome();


        Policy.setPolicy(new PluginSandboxSecurityPolicy());
        System.setSecurityManager(new SecurityManager());


        LoggerInit.init();

        DebugPrinter.detect();




        if (Var.savemode) {
            logger.warning("Programm is running in savemode");
        }
        Var.splashscreen = SplashScreen.create();
        if(Var.window==null) {

            logger.severe("Window not found... Exiting");
            System.exit(-1);
        }
        Var.window.setVisible(false);

        WindowManager.inputManager = new InputManager();

        Var.username = UserNameGetter.get();


        ThemeManager.register(); //Load all Themes

        Thread loadplugins = new Thread(() -> DisplayErrors.error = PluginManagerHandler.register());

        VisUI.load(VisUI.SkinScale.X1);

        Importer.init();


        Program.logger.config("Loaded Vis-UI");


        try {
            if (!Var.disablePluginSubSystem) {
                loadplugins.start(); //Plugins laden

            }
        } catch (Exception e) {
        }


    /*
     * Wait until all Plugins are registred
     */



        UI.init();
        ProjectManager.init();

        Program.logger.config("UI element loaded");
        if (!Var.savemode) {
            CheckShortcuts.loadArrayList();//bevor CheckShortcuts.loatArraylist muss die ui schon die menuebar eleente erstellt haben!!!!!!!!!
        }




        if (!Var.savemode) {
            Data.init();
        } else {
            Dialogs.showErrorDialog(UI.stage, "Achtung Interitus läuft im Abgesicherten Modus!\nAlle Einstellungen die die hier vornimmst werden nicht übernommen.\nDieser Modus dient nur dazu, Projekte zu retten und um Plugins zu testen.");
            Data.init(".temp");
        }
        Program.logger.finest("");
        Program.logger.config("Theme: " + Settings.theme.getName());
        Program.logger.config("Limit-FPS: " + Settings.limitfps);
        Program.logger.config("Vsync: " + Settings.Vsync);
        Program.logger.finest("");
        if (!Var.savemode) {
            UserDataInit.init();
        }
        Program.logger.config("Setted File drop Listener");
        ExperienceManager.init();
        Gdx.graphics.setVSync(Settings.Vsync);



        Timer saveprogrammdatatimer = new Timer();
        saveprogrammdatatimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Data.close(false);
            }
        }, 1000 * 60 * 15, 1000 * 60 * 15);

        Thread pluginloadingthread = new Thread() {
            @Override
            public void run() {
                while(loadplugins.isInterrupted());

                ProgramRegistry.addProjectTypes();
                ProgramRegistry.addMenuBarItems();
                ProgramRegistry.addShortCuts();

                logger.config("Plugin Registering done");

            }
        };
        pluginloadingthread.start();

        setScreen(new Loading());

    }


    public void dispose() {


        Data.close(true);

        if (Var.savemode) {
            try {
                FolderUtils.deleteFileOrFolder(Path.of(System.getProperty("user.home") + "/" + Data.foldername));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //ALC.destroy();//Destroy Sound System todo lwjgl3 if we want to use sound



        System.exit(0);

    }





}


