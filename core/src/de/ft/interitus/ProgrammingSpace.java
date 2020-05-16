package de.ft.interitus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockVar;
import de.ft.interitus.Block.TapBarBlockItem;
import de.ft.interitus.UI.CheckShortcuts;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.input.IntegerAuswahl;
import de.ft.interitus.UI.input.Switch;
import de.ft.interitus.UI.input.TextField;
import de.ft.interitus.UI.input.bar.tappedbar.TapContent;
import de.ft.interitus.UI.input.bar.tappedbar.TappedBar;
import de.ft.interitus.UI.input.popup.PopupManager;
import de.ft.interitus.UI.input.popup.PopupMenue;
import de.ft.interitus.UI.settings.subitems.subitem13;
import de.ft.interitus.data.user.changes.DataManager;
import de.ft.interitus.deviceconnection.arduino.PortUpdate;
import de.ft.interitus.deviceconnection.arduino.SerialConnection;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.device.BlockTypes.Arduino.Arduino.Wait;
import de.ft.interitus.projecttypes.device.BlockTypes.BlockTypesVar;
import de.ft.interitus.utils.PositionSaver;
import de.ft.interitus.utils.animation.Animation;

import java.awt.*;

import static com.badlogic.gdx.Gdx.input;
import static de.ft.interitus.Settings.darkmode;

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

    public static PopupManager popupmanager = new PopupManager();
    public static ShapeRenderer shapeRenderer;
    IntegerAuswahl ia;

    public static TappedBar tb= new TappedBar(100,100);

    public ProgrammingSpace() {

        //TODO in einen INIT verlagern

        TapContent content1=new TapContent(AssetLoader.img_mappe1);
        TapBarBlockItem tbbi=new TapBarBlockItem(new Wait(),AssetLoader.img_mappe2);

        TapContent content2=new TapContent(AssetLoader.img_mappe2);
        TapContent content3=new TapContent(AssetLoader.img_mappe3);
        TapContent content4=new TapContent(AssetLoader.img_mappe4);
        TapContent content5=new TapContent(AssetLoader.img_mappe5);
        TapContent content6=new TapContent(AssetLoader.img_mappe6);


        content1.setItems(tbbi,new TapBarBlockItem(new Wait(),AssetLoader.img_mappe3),new TapBarBlockItem(new Wait(),AssetLoader.img_mappe3),new TapBarBlockItem(new Wait(),AssetLoader.img_mappe3),new TapBarBlockItem(new Wait(),AssetLoader.img_mappe3));
        tb.setContent(content1,content2,content3,content4,content5,content6);

        //TODO in einen INIT verlagern
        popupmanager.addPopup(new PopupMenue("ein popup"));
        popupmanager.addPopup(new PopupMenue("Löschen", "Fixieren", "Umbenennen", "Befreien"));
        popupmanager.addPopup(new PopupMenue("Löschen", "Node einfügen"));

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
        Thread blockdebugcreater = new Thread() {
            @Override
            public void run() {
                try {


                    for (int i = 0; i < 1; i = i + 1) {

                        BlockVar.blocks.add(new Block(i, 400, 552, 150, 70, BlockTypesVar.blocks.get(0).get(0)));

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


        cam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        UIcam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

        UI.init();

        textfieldtest = new TextField(500, 600, 100, 25);


        ThreadManager.init();


        SerialConnection.searchArduino();

        PortUpdate.UpdateConnectionWindowPortsList();
        Gdx.graphics.setWindowedMode(Var.w, Var.h);


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

            if (darkmode) {
                Gdx.gl.glClearColor(60f/255f,63f/255f,65f/255f,1);
            } else {
                Gdx.gl.glClearColor(0.54f, 0.533f, 0.51f, 1);
            }
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.setProjectionMatrix(cam.combined);
            UIbatch.setProjectionMatrix(UIcam.combined);



            UI.updatedragui(shapeRenderer, true, batch);
            UI.updatedragui(shapeRenderer, false, batch);

            tb.setX(UIVar.BlockBarX+UIVar.BlockBarW/2);
            tb.setY(UIVar.BlockBarY+UIVar.BlockBarH/2-(tb.getHeight()+UIVar.abstandvonRand*2)/2);
            tb.draw();
            if (!Var.isloading) {
                Block Temp = null;
                for (int i = 0; i < BlockVar.visibleblocks.size(); i = i + 1) {
                    try {
                        batch.begin();
                    } catch (IllegalStateException e) {
                        batch.end();
                        batch.begin();
                    }

                    try {
                        if (BlockVar.visibleblocks.get(i).isMarked()) {
                            Temp = BlockVar.visibleblocks.get(i);
                        } else {
                            BlockVar.visibleblocks.get(i).draw(batch, shapeRenderer, font);
                        }

                        batch.end();
                        if (BlockVar.visibleblocks.get(i).isMarked()) {


                            if (input.isKeyJustPressed(Input.Keys.FORWARD_DEL)) {
                                BlockVar.visibleblocks.get(i).delete(false);
                            }


                        }


                        if (Temp != null) {

                            batch.begin();
                            Temp.draw(batch, shapeRenderer, font);
                            batch.end();
                        }


                    } catch (Exception e) {

                    }
                }
            }




           de.ft.interitus.UI.Viewport.update();


        } catch (Exception e) {
            DisplayErrors.error = e;
            e.printStackTrace();
        }


        if (darkmode) {
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

        } catch (NullPointerException e) {
            //Falls die UI nicht richtig initialisiert werden konnte
        }


      //  testanim.startAnimation();
//batch.draw(testanim.getAnimation(),50,50);
        // pm.setBounds(700,200);

        popupmanager.draw(); //Show Popups

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
