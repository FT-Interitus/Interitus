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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.ft.interitus.Block.BlockDrawer;
import de.ft.interitus.Block.ThreadManager;
import de.ft.interitus.UI.CheckShortcuts;
import de.ft.interitus.UI.ProgramGrid;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.PressedKeys;
import de.ft.interitus.UI.codehovering.CodeHovering;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.datamanager.programmdata.Updater;
import de.ft.interitus.plugin.Native;
import de.ft.interitus.plugin.Plugin;
import de.ft.interitus.plugin.PluginDrawer;
import de.ft.interitus.plugin.PluginManagerHandler;
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

        //ProjectManager.addProject(ProjectTypesVar.projectTypes.get(0).init());
        //ProjectManager.change(0);

    }

    public  void init() {

        UI.updatedragui(WindowManager.shapeRenderer, true, WindowManager.batch);
        //ProjectManager.getActProjectVar().projectType.initProject();

        BlockTappedBar.init();

        de.ft.interitus.UI.Viewport.init();


        ThreadManager.init();


        System.gc(); //Clean RAM after Loading

        Updater.initprogress();

    }


    @Override
    public void render(float delta) {
        WindowManager.updateWindow();
        if(Var.openprojects.size()==0) {
            WindowManager.switchto(WindowManager.Windows.welcome);
        }



        ProgramingSpace.delta = delta;


        renderstarttime = System.currentTimeMillis();





        //RechtsKlick.Rechtsklickupdate();

        try {

            //logger.finest("Blöcke "+BlockVar.blocks.size()+" Sichtbare "+ BlockVar.visibleblocks.size());

            PositionSaver.save();


            cam.update();



            Gdx.gl.glClearColor(Settings.theme.ClearColor().r, Settings.theme.ClearColor().g, Settings.theme.ClearColor().b, Settings.theme.ClearColor().a);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            WindowManager.batch.setProjectionMatrix(cam.combined);
            WindowManager.BlockshapeRenderer.setProjectionMatrix(cam.combined);
            WindowManager.update();



            UI.updatedragui( WindowManager.shapeRenderer, true, WindowManager.batch);

            ProgramGrid.draw();

            BlockDrawer.Draw();


            de.ft.interitus.UI.Viewport.update(delta);


        } catch (Exception e) {
            if(ProjectManager.getActProjectVar()!=null&&Var.inProgram) {
                DisplayErrors.error = e;
                e.printStackTrace();
            }
        }


        if (!loadimagesfromplugin) {
            PluginDrawer.draw();
        }


        if(ProjectManager.getActProjectVar()!=null&&ProjectManager.getActProjectVar().projectType.isCodeshowable()) {
            if (Gdx.input.isKeyPressed(Input.Keys.TAB)) {
                CodeHovering.drawHovering();
            }


        }

        WindowManager.drawer();



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
