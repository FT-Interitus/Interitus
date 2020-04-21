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
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.setup.SetupWindow;
import de.ft.interitus.data.programm.Data;
import de.ft.interitus.data.user.changes.DataManager;
import de.ft.interitus.input.IntegerAuswahl;
import de.ft.interitus.input.Switch;
import de.ft.interitus.input.TextField;
import de.ft.interitus.input.popup.PopupManager;
import de.ft.interitus.input.popup.PopupMenue;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.roboconnection.arduino.PortUpdate;
import de.ft.interitus.roboconnection.arduino.SerialConnection;
import de.ft.interitus.utils.PositionSaver;
import de.ft.interitus.utils.animation.Animation;

import java.awt.*;

import static com.badlogic.gdx.Gdx.input;
import static de.ft.interitus.Settings.darkmode;

public class ProgrammingSpace extends ScreenAdapter implements Screen {
    public static SpriteBatch UIbatch;
    public static SpriteBatch batch;

    public static OrthographicCamera cam;
    public static Viewport viewport;
    public static Component saver;
    //BlockUpdate bu[] = new BlockUpdate[0];


    public static BitmapFont font;
    public static Switch s;
    public static TextField textfieldtest;
    public static int w = 0;
    public static int h = 0;
    public static Drawable d;
    public static Animation testanim = new Animation(new Texture("ballfeueranimation.png"), 60, 100, 100, 3);

    public static PopupManager popupmanager=new PopupManager(new PopupMenue("ein popup"),new PopupMenue("BlockPopup","delete"));
    IntegerAuswahl ia;

    ShapeRenderer shapeRenderer;

    public ProgrammingSpace() {



        ia = new IntegerAuswahl(400, 400, 50, 25);
        s = new Switch(500, 500);
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new ScreenViewport(cam);
        batch = new SpriteBatch();
        UIbatch = new SpriteBatch();


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
                        BlockVar.blocks.add(new Block(i, 738, 552, 150, 70));

                        //  MainGame.logger.finest(String.valueOf(i));
                    }

                    System.out.println("Block creating done");
                } catch (Exception e) {
                    displayErrors.error = e;
                    e.printStackTrace(); //for debug to find errors
                }
            }
        };


        blockdebugcreater.start();


        cam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);



        UI.init();

        textfieldtest = new TextField(500, 600, 100, 25);


        ThreadManager.init();


        SerialConnection.searchArduino();

        PortUpdate.UpdateConnectionWindowPortsList();
        Gdx.graphics.setWindowedMode(Var.w, Var.h);

        SetupWindow sw = new SetupWindow();
        sw.show();
    }


    @Override
    public void render(float delta) {
        RechtsKlick.checkblockmouse();

        try {

            //logger.finest("Blöcke "+BlockVar.blocks.size()+" Sichtbare "+ BlockVar.visibleblocks.size());

            PositionSaver.save();


            cam.update();


            if (darkmode) {
                Gdx.gl.glClearColor(1, 0, 0, 1);
            } else {
                Gdx.gl.glClearColor(0.54f, 0.533f, 0.51f, 1);
            }
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.setProjectionMatrix(cam.combined);


            if (input.isKeyJustPressed(Input.Keys.INSERT)) {

                BlockVar.blocks.add(new Block(BlockVar.blocks.size(), 100, 200, 150, 70));
                DataManager.change(BlockVar.blocks.get(BlockVar.blocks.size() - 1), true, false);
            }


            UI.updatedragui(shapeRenderer, true, batch);

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

            UI.updatedragui(shapeRenderer, false, batch);

			/*

		for(int b=0;b<BlockVar.blocks.size();b=b+1) {
			Block block = BlockVar.blocks.get(b);
			if(!BlockVar.blocks.get(b).blockupdate.toggle) {
				batch.draw(img_block, block.getX(), block.getY(), block.getW(), block.getH());
			}else{
				batch.draw(img_selected, block.getX(), block.getY(), block.getW(), block.getH());
			}

		}
		*/
            if (input.isKeyJustPressed(Input.Keys.LEFT)) {
                cam.position.set(cam.position.x -= 20, cam.position.y, 0);
            }

            if (input.isKeyJustPressed(Input.Keys.RIGHT)) {
                cam.position.set(cam.position.x += 20, cam.position.y, 0);
            }

            if (input.isKeyJustPressed(Input.Keys.UP)) {
                cam.position.set(cam.position.x, cam.position.y += 20, 0);
            }

            if (input.isKeyJustPressed(Input.Keys.DOWN)) {
                cam.position.set(cam.position.x, cam.position.y -= 20, 0);
            }


        } catch (Exception e) {
            displayErrors.error = e;
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
        s.setSize(1f);
        s.setWackelstärke(1);
        s.draw();
        ia.setButtonposition(1);
        ia.draw(shapeRenderer, batch);
        textfieldtest.setTextAnordnung(1);
        textfieldtest.draw();

        UI.update();


        batch.begin();
        testanim.startAnimation();
//batch.draw(testanim.getAnimation(),50,50);
        // pm.setBounds(700,200);

        popupmanager.draw();
        batch.end();

        displayErrors.checkerror();

    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);


        UI.updateView(width, height);
        viewport.update(width, height);


        w = width;
        h = height;

    }


    public void hide() {
        System.out.println("Stop");
    }

    public void dispose() {


        //TODO stop Thread Manager Thread

        System.out.println("Stop stop stop stop stop stop stop stop stop stopo stop");

        batch.dispose();

    }
}
