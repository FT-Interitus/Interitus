package de.ft.interitus;

import com.badlogic.gdx.Gdx;
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
import de.ft.interitus.UI.CheckShortcuts;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.IntegerAuswahl;
import de.ft.interitus.UI.UIElements.PressedKeys;
import de.ft.interitus.UI.UIElements.Switch;
import de.ft.interitus.UI.UIElements.TextField;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.settings.subitems.subitem17;
import de.ft.interitus.UI.shortcut.shortcuts.BlockShortcuts;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.plugin.Configuration;
import de.ft.interitus.plugin.Nativ;
import de.ft.interitus.plugin.Plugin;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.BlockTypes.ProjectTypesVar;
import de.ft.interitus.utils.PositionSaver;
import de.ft.interitus.utils.animation.Animation;

import java.awt.*;


public class ProgrammingSpace extends ScreenAdapter {
    public static SpriteBatch batch;

    public static OrthographicCamera cam;
    public static Viewport viewport;
    public static Component saver;

    public static BitmapFont font;
    public static Switch s;
    public static TextField textfieldtest;
    public static int w = 0;
    public static int h = 0;
    public static Drawable d;
    public static Animation testanim = new Animation(new Texture("ballfeueranimation.png"), 60, 100, 100, 3);

    public static long renderstarttime = 0;
    public static long rendertimediff = 0;
    public static long rendersleeptime = 0;

    public static ShapeRenderer shapeRenderer;
    public static PressedKeys pressedKeys;
    public static float delta;
    public static Plugin nativ = new Nativ();
    IntegerAuswahl ia;


    public ProgrammingSpace() {



        //TODO Debug hier wird immer ein Ev3 Project erstellt




        pressedKeys = new PressedKeys();



        ia = new IntegerAuswahl(400, 400, 50, 25);
        s = new Switch(500, 500);
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        viewport = new ScreenViewport(cam);

        batch = new SpriteBatch();

        cam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        UI.UIcam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);


        Var.openprojects.add(ProjectTypesVar.projectTypes.get(0).init());

        UI.updatedragui(shapeRenderer, true, batch);
        ProjectManager.getActProjectVar().projectType.initProject();

        RechtsKlick.Init();
        BlockTappedBar.init();


        de.ft.interitus.UI.Viewport.init();

        Gdx.graphics.setTitle("New File");
        ProjectManager.getActProjectVar().filename = "New File";

        s.setBackground(AssetLoader.switch_background);
        s.setBackgroundgreen(AssetLoader.switch_background_green);
        s.setInside(AssetLoader.switch_inside);




        textfieldtest = new TextField(500, 600, 100, 25);


        ThreadManager.init();


        //  SerialConnection.searchArduino();

        //   PortUpdate.UpdateConnectionWindowPortsList();




        System.gc(); //Clean RAM after Loading




    }


    @Override
    public void render(float delta) {



        this.delta=delta;




        renderstarttime = System.currentTimeMillis();

        if (ProjectManager.getActProjectVar().projectType == null) {
            Programm.INSTANCE.setScreen(new Welcome());
        }

        RechtsKlick.Rechtsklickupdate();

        try {

            //logger.finest("Blöcke "+BlockVar.blocks.size()+" Sichtbare "+ BlockVar.visibleblocks.size());

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


            if (!Var.isloading) {
                Block Temp = null;
                Block Temp2 = null;
                for (int i = 0; i < ProjectManager.getActProjectVar().visibleblocks.size(); i = i + 1) {
                    try {
                        batch.begin();
                    } catch (IllegalStateException e) {
                        batch.end();
                        batch.begin();
                    }

                    try {
                        if (ProjectManager.getActProjectVar().visibleblocks.get(i).isMarked()) {
                            if (ProjectManager.getActProjectVar().visibleblocks.get(i).isMoving()) {
                                Temp2 = ProjectManager.getActProjectVar().visibleblocks.get(i);
                            } else {
                                Temp = ProjectManager.getActProjectVar().visibleblocks.get(i);
                            }
                        } else {
                            ProjectManager.getActProjectVar().visibleblocks.get(i).draw(batch, shapeRenderer, font);
                        }

                        batch.end();
                        if (ProjectManager.getActProjectVar().visibleblocks.get(i).isMarked()) {


                            if (BlockShortcuts.shortCut_deleteBlock.isPressed()&&ProjectManager.getActProjectVar().visibleblocks.get(i).getBlocktype().canbedeleted()) {
                                ProjectManager.getActProjectVar().visibleblocks.get(i).delete(false);
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
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                UI.updatedragui(shapeRenderer, false, batch);
                BlockTappedBar.tb.setX(UIVar.BlockBarX + UIVar.BlockBarW / 2);
                BlockTappedBar.tb.setY(UIVar.BlockBarY + UIVar.BlockBarH / 2 - (BlockTappedBar.tb.getHeight() + UIVar.abstandvonRand * 2) / 2);
                BlockTappedBar.tb.draw();

                if (Temp2 != null) {

                    try {
                        batch.begin();
                        Temp2.draw(batch, shapeRenderer, font);



                        batch.end();
                    } catch (Exception e) {

                    }
                }


            }


            de.ft.interitus.UI.Viewport.update(delta);


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

        for (int i = 0; i < ProjectManager.getActProjectVar().visiblewires.size(); i++) {
            if (!Var.isloading) {
                ProjectManager.getActProjectVar().visiblewires.get(i).draw();
            }
        }
        for (int i = 0; i < ProjectManager.getActProjectVar().visibleWireNodes.size(); i++) {
            ProjectManager.getActProjectVar().visibleWireNodes.get(i).draw();
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


        //  testanim.startAnimation();
//batch.draw(testanim.getAnimation(),50,50);
        // pm.setBounds(700,200);

        RechtsKlick.popupmanager.draw(); //Show Popups

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
        w = width;
        h = height;


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
