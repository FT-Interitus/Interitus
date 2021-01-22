/*
 * Copyright (c) 2021.
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
import de.ft.interitus.Block.BlockUpdate.BlockUpdateManager;
import de.ft.interitus.Block.ThreadManager;
import de.ft.interitus.UI.ProgramGrid;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.PressedKeys;
import de.ft.interitus.UI.WindowAPI;
import de.ft.interitus.UI.codehovering.CodeHovering;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.datamanager.programmdata.Updater;
import de.ft.interitus.events.EventManager;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.projecttypes.ProjectManager;

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

    public void open() {


        cam = new OrthographicCamera(WindowAPI.getWidth(), WindowAPI.getHeight());

        viewport = new ScreenViewport(cam);


        cam.position.set(WindowAPI.getWidth() / 2f + 50, WindowAPI.getHeight() / 2f, 0);
        UI.UIcam.position.set(WindowAPI.getWidth() / 2f + 50, WindowAPI.getHeight() / 2f, 0);


    }

    public void init() {

        UI.UpdateDragUI(WindowManager.shapeRenderer, true, WindowManager.blockBatch,1);
        //ProjectManager.getActProjectVar().projectType.initProject();

        BlockTappedBar.init();

        de.ft.interitus.UI.Viewport.init(ProgramingSpace.cam, WindowManager.inputManager);


        ThreadManager.init();


        System.gc(); //Clean RAM after Loading

        Updater.initprogress();
        EventManager.addListenerClass(EventVar.class);
        BlockUpdateManager.init();


    }


    @Override
    public void render(float delta) {


        WindowManager.updateWindow();

        if (Var.openprojects.size() == 0) {
            WindowManager.switchTo(WindowManager.Windows.welcome);
        }


        renderstarttime = System.currentTimeMillis();


        //rightKlick.rightklickupdate();

        try {

            //logger.finest("Blöcke "+BlockVar.blocks.size()+" Sichtbare "+ BlockVar.visibleblocks.size());




            cam.update();


            Gdx.gl.glClearColor(Settings.theme.ClearColor().r, Settings.theme.ClearColor().g, Settings.theme.ClearColor().b, Settings.theme.ClearColor().a);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            WindowManager.blockBatch.setProjectionMatrix(cam.combined);
            WindowManager.BlockshapeRenderer.setProjectionMatrix(cam.combined);
            WindowManager.update();


            UI.UpdateDragUI(WindowManager.shapeRenderer, true, WindowManager.blockBatch,delta);

            ProgramGrid.draw(WindowManager.shapeRenderer, ProgramingSpace.cam);



            /***
             * do not separate this to avoid Marking issues
             */
            BlockUpdateManager.updateBlocks();
            BlockDrawer.Draw(delta);


            de.ft.interitus.UI.Viewport.update(delta, ProgramingSpace.cam);


        } catch (Exception e) {
            if (ProjectManager.getActProjectVar() != null && Var.inProgram) {
                DisplayErrors.error = e;
                e.printStackTrace();
            }
        }




        if (ProjectManager.getActProjectVar() != null && ProjectManager.getActProjectVar().projectType.isCodeshowable()) {
            if (WindowAPI.isKeyPressed(Input.Keys.TAB)) {
                CodeHovering.drawHovering();
            }


        }

        WindowManager.drawer(delta);




        Gdx.graphics.requestRendering();
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





}
