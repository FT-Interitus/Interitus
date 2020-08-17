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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockDrawer;
import de.ft.interitus.UI.CheckShortcuts;
import de.ft.interitus.UI.Notification.Notification;
import de.ft.interitus.UI.Notification.NotificationManager;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.PressedKeys;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.popup.PopupHandler;
import de.ft.interitus.UI.settings.subitems.subitem17;
import de.ft.interitus.UI.setup.SetupWindow;
import de.ft.interitus.UI.shortcut.shortcuts.BlockShortcuts;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.datamanager.programmdata.Updater;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.plugin.Native;
import de.ft.interitus.plugin.Plugin;
import de.ft.interitus.projecttypes.BlockTypes.ProjectTypesVar;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.PositionSaver;
import de.ft.interitus.utils.ShapeRenderer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;


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
    public static PressedKeys pressedKeys;
    public static float delta;
    public static Plugin nativ = new Native();


    public ProgrammingSpace() {


        pressedKeys = new PressedKeys();


        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
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

        //DEBUG todo remove

        if (Gdx.input.isKeyJustPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            SetupWindow sw = new SetupWindow();
            sw.show();
        }

        ProgrammingSpace.delta = delta;


        renderstarttime = System.currentTimeMillis();

        if (Var.openprojects.size() != 0 && ProjectManager.getActProjectVar().projectType == null) {
            Programm.INSTANCE.setScreen(new Welcome());
        }

        try {


            PositionSaver.save();


            cam.update();
            UI.UIcam.update();

            Gdx.gl.glClearColor(Settings.theme.ClearColor().r, Settings.theme.ClearColor().g, Settings.theme.ClearColor().b, Settings.theme.ClearColor().a);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
            batch.setProjectionMatrix(cam.combined);
            UI.UIbatch.setProjectionMatrix(UI.UIcam.combined);
            shapeRenderer.setProjectionMatrix(UI.UIcam.combined);


            UI.updatedragui(shapeRenderer, true, batch);


            BlockDrawer.Draw();


            de.ft.interitus.UI.Viewport.update(delta);


        } catch (Exception e) {
            DisplayErrors.error = e;
            e.printStackTrace();
        }


        //s.setSize(1);
        //s.setSize(1f);
        //s.setWackelstärke(1);
        // s.draw();
        //ia.setButtonposition(1);
        //ia.draw(shapeRenderer, batch);
        //textfieldtest.setTextAnordnung(1);
        //textfieldtest.draw();
        //tb.setX(UIVar.);

        try {
            UI.update();

        } catch (Exception e) {
            //Falls die UI nicht richtig initialisiert werden konnte
            DisplayErrors.customErrorstring = "Fehler in der UI";
            DisplayErrors.error = e;

        }



        NotificationManager.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            NotificationManager.sendNotification(new Notification(AssetLoader.information, "Wichtige Information", "\nEs steht kein Update bereit!"));
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
        if (subitem17.saveme != null) {
            AssetLoader.storeimages.add(new Texture(subitem17.saveme));
            subitem17.saveme = null;

        }


        //Um alle shortcuts für das Programm zu überprüfen
        CheckShortcuts.check();


        //Import all Donwloaded images
        if (AssetLoader.finishpluginimageloading) { //Import all

            for (int i = 0; i < AssetLoader.pixmap.size(); i++) {
                AssetLoader.storeimages.add(new Texture(AssetLoader.pixmap.get(i)));
            }

            AssetLoader.finishpluginimageloading = false; //
        }
    }


}
