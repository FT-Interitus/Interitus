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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.ft.interitus.Block.BlockDrawer;
import de.ft.interitus.UI.CheckShortcuts;
import de.ft.interitus.UI.ProgramGrid;
import de.ft.interitus.UI.Notification.Notification;
import de.ft.interitus.UI.Notification.NotificationManager;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.PressedKeys;
import de.ft.interitus.UI.UIElements.UIElementBar;
import de.ft.interitus.UI.UIElements.UIElements.Button;
import de.ft.interitus.UI.popup.PopupHandler;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.datamanager.programmdata.Updater;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.plugin.*;
import de.ft.interitus.projecttypes.BlockTypes.ProjectTypesVar;
import de.ft.interitus.projecttypes.ProgrammArea.ProgrammAreaManager;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.PositionSaver;
import de.ft.interitus.utils.ShapeRenderer;


import java.awt.*;


public class ProgrammingSpace extends ScreenAdapter {
    public static SpriteBatch batch;

    public static OrthographicCamera cam;
    public static Viewport viewport;
    public static Component saver;

    public static BitmapFont font;


    public static long renderstarttime = 0;
    public static long rendertimediff = 0;
    public static long rendersleeptime = 0;

    public static ShapeRenderer shapeRenderer;
    public static ShapeRenderer BlockshapeRenderer;
    public static PressedKeys pressedKeys;
    public static float delta;
    public boolean loadimagesfromplugin = true;
    public static Plugin nativ = new Native();


    public ProgrammingSpace() {





        pressedKeys = new PressedKeys();


        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        BlockshapeRenderer = new ShapeRenderer();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        viewport = new ScreenViewport(cam);

        batch = new SpriteBatch();

        cam.position.set(Gdx.graphics.getWidth() / 2f + 50, Gdx.graphics.getHeight() / 2f, 0);
        UI.UIcam.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0);

//TODO Debug hier wird immer ein Arduino Project erstellt
        ProjectManager.addProject(ProjectTypesVar.projectTypes.get(0).init());
        ProjectManager.change(0);

        UI.updatedragui(shapeRenderer, true, batch);
        ProjectManager.getActProjectVar().projectType.initProject();


        BlockTappedBar.init();


        de.ft.interitus.UI.Viewport.init();

        Gdx.graphics.setTitle("New File");
        ProjectManager.getActProjectVar().setFilename("New File");


        ThreadManager.init();


        System.gc(); //Clean RAM after Loading

    Updater.initprogress();




    }




    @Override
    public void render(float delta) {

if(ProjectManager.getActProjectVar().markedblock!=null) {
    ProgrammAreaManager.getProgrammArea(ProjectManager.getActProjectVar().markedblock.getIndex());
}
        ProgrammingSpace.delta = delta;


        renderstarttime = System.currentTimeMillis();

        if (Var.openprojects.size() != 0 && ProjectManager.getActProjectVar().projectType == null) {
            Programm.INSTANCE.setScreen(new Welcome());
        }

        //RechtsKlick.Rechtsklickupdate();

        try {

            //logger.finest("Blöcke "+BlockVar.blocks.size()+" Sichtbare "+ BlockVar.visibleblocks.size());

            PositionSaver.save();


            cam.update();
            UI.UIcam.update();

           Gdx.gl.glClearColor(Settings.theme.ClearColor().r, Settings.theme.ClearColor().g, Settings.theme.ClearColor().b, Settings.theme.ClearColor().a);
           Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.setProjectionMatrix(cam.combined);
            UI.UIbatch.setProjectionMatrix(UI.UIcam.combined);
            shapeRenderer.setProjectionMatrix(UI.UIcam.combined);
            BlockshapeRenderer.setProjectionMatrix(cam.combined);


            UI.updatedragui(shapeRenderer, true, batch);

            ProgramGrid.draw();

            BlockDrawer.Draw();


            de.ft.interitus.UI.Viewport.update(delta);


        } catch (Exception e) {
            DisplayErrors.error = e;
            e.printStackTrace();
        }


        if(!loadimagesfromplugin) {
            PluginDrawer.draw();
        }


        NotificationManager.draw();
        try {
            UI.update();

        } catch (Exception e) {
            //Falls die UI nicht richtig initialisiert werden konnte
            DisplayErrors.customErrorstring = "Fehler in der UI";
            DisplayErrors.error = e;

        }




        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
          Notification notification =  new Notification(AssetLoader.information, "Wichtige Information", "\nEs steht kein Update bereit!");
          notification.setButtonBar(new UIElementBar().addButton(new Button().setText("Test")));
            NotificationManager.sendNotification(notification);
        }

        PopupHandler.drawPopUp();




        try {
            DisplayErrors.checkerror(); //Check if there are undisplayed Errors
        } catch (IllegalStateException e) {
            //Bei eienem VisUI absturz
        }

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


        batch.dispose();


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
