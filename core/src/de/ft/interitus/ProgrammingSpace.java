package de.ft.interitus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockVar;
import de.ft.interitus.UI.CheckShortcuts;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.inputfields.IntegerAuswahl;
import de.ft.interitus.UI.inputfields.PressedKeys;
import de.ft.interitus.UI.inputfields.Switch;
import de.ft.interitus.UI.inputfields.TextField;
import de.ft.interitus.UI.settings.subitems.subitem13;
import de.ft.interitus.UI.shortcut.shortcuts.BlockShortcuts;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.data.user.changes.DataManager;
import de.ft.interitus.deviceconnection.arduino.PortUpdate;
import de.ft.interitus.deviceconnection.arduino.SerialConnection;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.global.*;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.device.BlockTypes.Ev3.Wait;
import de.ft.interitus.projecttypes.device.BlockTypes.ProjectTypesVar;
import de.ft.interitus.utils.PositionSaver;
import de.ft.interitus.utils.animation.Animation;

import java.awt.*;


public class ProgrammingSpace extends ScreenAdapter implements Screen {
    public static SpriteBatch UIbatch;
    public static SpriteBatch batch;

    public static OrthographicCamera cam;
    public static OrthographicCamera UIcam;
    public static Viewport viewport;
    public static Viewport UIviewport;
    public static Component saver;

    public static BitmapFont font;
    public static Switch s;
    public static TextField textfieldtest;
    public static int w = 0;
    public static int h = 0;
    public static Drawable d;
    public static Animation testanim = new Animation(new Texture("ballfeueranimation.png"), 60, 100, 100, 3);

    public static ShapeRenderer shapeRenderer;
    IntegerAuswahl ia;

    public static PressedKeys pressedKeys;

    public ProgrammingSpace() {

        //TODO Debug hier wird immer ein Ev3 Project erstellt

        Var.actProjekt = ProjectTypesVar.projectTypes.get(0);


        pressedKeys=new PressedKeys();
        RechtsKlick.Init();
         BlockTappedBar.init(); //TODO reinit after new Project was created or loaded


        ia = new IntegerAuswahl(400, 400, 50, 25);
        s = new Switch(500, 500);
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        UIcam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new ScreenViewport(cam);
        UIviewport = new ScreenViewport(UIcam);
        batch = new SpriteBatch();
        UIbatch = new SpriteBatch();


        de.ft.interitus.UI.Viewport.init();

        Gdx.graphics.setTitle("New File");
        DataManager.filename = "New File";

        s.setBackground(AssetLoader.switch_background);
        s.setBackgroundgreen(AssetLoader.switch_background_green);
        s.setInside(AssetLoader.switch_inside);

/*
        Thread blockdebugcreater = new Thread() {
            @Override
            public void run() {
                try {


                    for (int i = 0; i < 1; i = i + 1) {


                            BlockVar.blocks.add(new Block(i, 400+i*250, 552, 150, 70, new Wait()));





                        System.out.println(i);
                        //  MainGame.logger.finest(String.valueOf(i));
                    }


                    System.out.println("Block creating done");
                } catch (Exception e) {
                    DisplayErrors.error = e;
                    e.printStackTrace(); //for debug to find errors
                }
            }
        };


        blockdebugcreater.start();


 */



        cam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        UIcam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

        UI.init();

        textfieldtest = new TextField(500, 600, 100, 25);


        ThreadManager.init();


        SerialConnection.searchArduino();

        PortUpdate.UpdateConnectionWindowPortsList();



    }


    @Override
    public void render(float delta) {


        if(Var.actProjekt==null) {
           // Programm.INSTANCE.setScreen(new Welcome()); //TODO auskommentiert zu debug zwecken
        }

        RechtsKlick.Rechtsklickupdate();

        try {

            //logger.finest("Blöcke "+BlockVar.blocks.size()+" Sichtbare "+ BlockVar.visibleblocks.size());

            PositionSaver.save();


            cam.update();
            UIcam.update();

            Gdx.gl.glClearColor(Settings.theme.ClearColor().r,Settings.theme.ClearColor().g,Settings.theme.ClearColor().b,Settings.theme.ClearColor().a);

            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
            batch.setProjectionMatrix(cam.combined);
            UIbatch.setProjectionMatrix(UIcam.combined);
            shapeRenderer.setProjectionMatrix(UIcam.combined);



            UI.updatedragui(shapeRenderer, true, batch);



            if (!Var.isloading) {
                Block Temp = null;
                Block Temp2 = null;
                for (int i = 0; i < BlockVar.visibleblocks.size(); i = i + 1) {
                    try {
                        batch.begin();
                    } catch (IllegalStateException e) {
                        batch.end();
                        batch.begin();
                    }

                    try {
                        if (BlockVar.visibleblocks.get(i).isMarked()) {
                            if(BlockVar.visibleblocks.get(i).isMoving()) {
                                Temp2 = BlockVar.visibleblocks.get(i);
                            }else {
                                Temp = BlockVar.visibleblocks.get(i);
                            }
                        } else {
                            BlockVar.visibleblocks.get(i).draw(batch, shapeRenderer, font);
                        }

                        batch.end();
                        if (BlockVar.visibleblocks.get(i).isMarked()) {


                            if (BlockShortcuts.shortCut_deleteBlock.isPressed()) {
                                BlockVar.visibleblocks.get(i).delete(false);
                            }


                        }


                    } catch (Exception e) {
e.printStackTrace();
                    }
                }
                if (Temp != null) {

                    try {
                        batch.begin();
                        Temp.draw(batch, shapeRenderer, font);
                        batch.end();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                UI.updatedragui(shapeRenderer, false, batch);
                BlockTappedBar.tb.setX(UIVar.BlockBarX+UIVar.BlockBarW/2);
                BlockTappedBar.tb.setY(UIVar.BlockBarY+UIVar.BlockBarH/2-(BlockTappedBar.tb.getHeight()+UIVar.abstandvonRand*2)/2);
                BlockTappedBar.tb.draw();

                if (Temp2 != null) {

                    try {
                        batch.begin();
                        Temp2.draw(batch, shapeRenderer, font);
                        batch.end();
                    }catch (Exception e) {

                    }
                }


            }




           de.ft.interitus.UI.Viewport.update();


        } catch (Exception e) {
            DisplayErrors.error = e;
            e.printStackTrace();
        }


        if (Settings.theme.isdark()) {
            s.setBackground(AssetLoader.switch_background);
            s.setBackgroundgreen(AssetLoader.switch_background_green);
            s.setInside(AssetLoader.switch_inside);
        } else {
            s.setBackground(AssetLoader.switch_background_white);
            s.setBackgroundgreen(AssetLoader.switch_background_green_white);
            s.setInside(AssetLoader.switch_inside);
        }

        for (int i = 0; i < BlockVar.visiblewires.size(); i++) {
            BlockVar.visiblewires.get(i).draw();
        }
        for (int i = 0; i < BlockVar.visibleWireNodes.size(); i++) {
            BlockVar.visibleWireNodes.get(i).draw();
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
        }


      //  testanim.startAnimation();
//batch.draw(testanim.getAnimation(),50,50);
        // pm.setBounds(700,200);

        RechtsKlick.popupmanager.draw(); //Show Popups

        DisplayErrors.checkerror(); //Check if there are undisplayed Images

        loader(); //Load Images in OpenGL context



    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);


        try {
            UI.updateView(width, height);
        } catch (NullPointerException e) { //Falls die UI nicht initialisiert werden konnte

        }
        viewport.update(width, height);
        UIviewport.update(width, height);
        w = width;
        h = height;



    }


    public void hide() {
        System.out.println("Stop");
    }

    public void dispose() {




        batch.dispose();

    }

    public void loader() {
        if (subitem13.saveme != null) {
            AssetLoader.storeimages.add(new Texture(subitem13.saveme));
            subitem13.saveme = null;

        }


        //Um alle shortcuts für das Programm zu überprüfen
        CheckShortcuts.check();
        //Import all Donwloaded images
        if(AssetLoader.finishpluginimageloading) { //Import all

            for(int i=0;i<AssetLoader.pixmap.size();i++) {
                AssetLoader.storeimages.add(new Texture(AssetLoader.pixmap.get(i)));
            }

            AssetLoader.finishpluginimageloading = false; //
        }
    }


}
