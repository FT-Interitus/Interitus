/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.tools.javac.Main;
import de.ft.interitus.Block.BlockDrawer;
import de.ft.interitus.Block.ThreadManager;
import de.ft.interitus.UI.CheckShortcuts;
import de.ft.interitus.UI.Notification.Notification;
import de.ft.interitus.UI.Notification.NotificationManager;
import de.ft.interitus.UI.ProgramGrid;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.PressedKeys;
import de.ft.interitus.UI.popup.PopupHandler;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.datamanager.programmdata.Updater;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.plugin.Native;
import de.ft.interitus.plugin.Plugin;
import de.ft.interitus.plugin.PluginDrawer;
import de.ft.interitus.plugin.PluginManagerHandler;
import de.ft.interitus.projecttypes.BlockTypes.ProjectTypesVar;
import de.ft.interitus.projecttypes.ProgrammArea.ProgrammAreaManager;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.PositionSaver;

import java.awt.*;


public class ProgramingSpace extends ScreenAdapter {

    public static OrthographicCamera cam;
    public static Viewport viewport;
    public static Component saver;

   // public static BitmapFont font;


    public static long renderstarttime = 0;
    public static long rendertimediff = 0;
    public static long rendersleeptime = 0;


    public static PressedKeys pressedKeys;
    public static float delta;
    public static Plugin nativ = new Native();
    public boolean loadimagesfromplugin = true;



    public void open() {


        pressedKeys = new PressedKeys();



        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        viewport = new ScreenViewport(cam);



        cam.position.set(Gdx.graphics.getWidth() / 2f + 50, Gdx.graphics.getHeight() / 2f, 0);
        UI.UIcam.position.set(Gdx.graphics.getWidth() / 2f + 50, Gdx.graphics.getHeight() / 2f, 0);

        //TODO Debug hier wird immer ein Arduino Project erstellt -> Welcome Screen
        ProjectManager.addProject(ProjectTypesVar.projectTypes.get(0).init());
        ProjectManager.change(0);

        UI.updatedragui(MainRendering.shapeRenderer, true, MainRendering.batch);
        ProjectManager.getActProjectVar().projectType.initProject();


        BlockTappedBar.init();

        de.ft.interitus.UI.Viewport.init();

        Gdx.graphics.setTitle("Interitus - Home Version");
        ProjectManager.getActProjectVar().setFilename("New File");


        ThreadManager.init();


        System.gc(); //Clean RAM after Loading

        Updater.initprogress();
        Program.logger.config(String.valueOf(System.currentTimeMillis()- Program.time));
    }


    @Override
    public void render(float delta) {

        if(Var.openprojects.size()==0) {
        Program.INSTANCE.setScreen(Var.welcome);
        }


        if (ProjectManager.getActProjectVar().marked_block != null) {
            ProgrammAreaManager.getProgrammArea(ProjectManager.getActProjectVar().marked_block.getIndex());
        }
        ProgramingSpace.delta = delta;


        renderstarttime = System.currentTimeMillis();

        if (ProjectManager.getActProjectVar().projectType == null) {
           this.dispose();
           Program.INSTANCE.setScreen(Var.welcome);
        }



        //RechtsKlick.Rechtsklickupdate();

        try {

            //logger.finest("Blöcke "+BlockVar.blocks.size()+" Sichtbare "+ BlockVar.visibleblocks.size());

            PositionSaver.save();


            cam.update();



            Gdx.gl.glClearColor(Settings.theme.ClearColor().r, Settings.theme.ClearColor().g, Settings.theme.ClearColor().b, Settings.theme.ClearColor().a);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            MainRendering.batch.setProjectionMatrix(cam.combined);
            MainRendering.BlockshapeRenderer.setProjectionMatrix(cam.combined);
            MainRendering.update();



            UI.updatedragui( MainRendering.shapeRenderer, true, MainRendering.batch);

            ProgramGrid.draw();

            BlockDrawer.Draw();


            de.ft.interitus.UI.Viewport.update(delta);


        } catch (Exception e) {
            DisplayErrors.error = e;
            e.printStackTrace();
        }


        if (!loadimagesfromplugin) {
            PluginDrawer.draw();
        }

        MainRendering.drawer();



        loader(); //Load Images in OpenGL context


        de.ft.interitus.UI.Viewport.limitfps();


    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);


        try {
            UI.updateView(width, height);
        } catch (NullPointerException e) { //Falls die UI nicht initialisiert werden konnte

        }
        viewport.update(width, height);
        UI.UIviewport.update(width, height);


    }


    public void dispose() {

        /*
        if (Var.openprojects.size() > 0) {

            for (int i = 0; i < Var.openprojects.size(); i++) {
                try {
                    ProjectManager.CloseProject(i);
                } catch (Exception ignored) {

                }
            }


        }

         */


        MainRendering.batch.dispose();


    }

    public void loader() {


        //Um alle shortcuts für das Programm zu überprüfen
        CheckShortcuts.check();


        //Import all Plugin Images
        if (loadimagesfromplugin) { //Import all

            PluginDrawer.loadimages();

            loadimagesfromplugin = false; //

            PluginManagerHandler.init();

        }
    }


}
