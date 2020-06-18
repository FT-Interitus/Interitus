package de.ft.interitus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import com.kotcrab.vis.ui.VisUI;
import de.ft.interitus.UI.CheckShortcuts;
import de.ft.interitus.UI.Theme.ThemeManager;
import de.ft.interitus.UI.UI;
import de.ft.interitus.data.programm.Data;
import de.ft.interitus.data.user.UserDataInit;
import de.ft.interitus.data.user.experience.ExperienceManager;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.UI.UILoadEvent;
import de.ft.interitus.loading.Loading;
import de.ft.interitus.loading.SplashScreen;
import de.ft.interitus.plugin.PluginManagerHandler;
import de.ft.interitus.plugin.store.ReadStorePlugins;
import de.ft.interitus.projecttypes.device.BlockTypes.Init;
import de.ft.interitus.utils.NetworkScan;
import de.ft.interitus.utils.UserNameGetter;

import java.util.Timer;
import java.util.TimerTask;

public class Programm extends Game {

    public static Programm INSTANCE;
    public static boolean inLoading = true;


    public Programm() {


        INSTANCE = this;
    }

    @Override
    public void create() {
        ((Lwjgl3Graphics)Gdx.graphics).getWindow().iconifyWindow();
        Var.splashscreen = SplashScreen.create();


      //  Manager.init();

        Var.username = UserNameGetter.get();

        ThemeManager.register(); //Load all Themes

        Thread loadplugins = new Thread() {
            @Override
            public void run() {
                Var.pluginManager = new PluginManagerHandler();
                DisplayErrors.error = Var.pluginManager.init();

            }
        };
        VisUI.load(VisUI.SkinScale.X1);

        EventVar.uiEventManager.UILoadEvent(new UILoadEvent(this));

        try {
            if(!Var.disablePluginSubSystem) {
                loadplugins.start(); //Plugins laden
                ReadStorePlugins.read(); //Ersten 10 Plugins im Store laden
            }
        } catch (Exception e) {
            Var.nointernetconnection = true;
        }

        Init.initBlocks();
        UI.init();
        CheckShortcuts.loadArrayList();//bevor CheckShortcuts.loatArraylist muss die ui schon die menuebar eleente erstellt haben!!!!!!!!!
        Thread seachnetwork = new Thread() {
            @Override
            public void run() {
                NetworkScan.get();
            }
        }; //TODO doenst work on Mac
        seachnetwork.start();
        Data.init();
        UserDataInit.init();
        ExperienceManager.init();
        Gdx.graphics.setVSync(Settings.Vsync);

        Timer saveprogrammdatatimer  = new Timer();
        saveprogrammdatatimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Data.close();
            }
        },1000*60*15,1000*60*15);
        setScreen(new Loading());

    }


    public void dispose() {

        ThreadManager.stopall();

        Data.close();

       // AL.destroy(); //Destroy Sound System //TODO lwjgl 3

       System.exit(0);

    }


    public void pause() {

    }


}


