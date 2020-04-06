package de.ft.robocontrol;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.ft.robocontrol.Block.Block;
import de.ft.robocontrol.Block.BlockVar;
import de.ft.robocontrol.input.IntegerAuswahl;
import de.ft.robocontrol.input.Switch;
import de.ft.robocontrol.UI.UI;
import de.ft.robocontrol.data.programm.Data;
import de.ft.robocontrol.data.user.changes.DataManager;
import de.ft.robocontrol.input.TextField;
import de.ft.robocontrol.loading.AssetLoader;
import de.ft.robocontrol.roboconnection.SerialConnection;
import de.ft.robocontrol.roboconnection.UIbridge;
import de.ft.robocontrol.utils.PositionSaver;


import java.awt.*;
import java.util.logging.Logger;

import static com.badlogic.gdx.Gdx.input;
import static de.ft.robocontrol.Settings.*;

public class MainGame extends ScreenAdapter implements Screen {
    public static SpriteBatch UIbatch;
    public static SpriteBatch batch;



    public static OrthographicCamera cam;
    public static Viewport viewport;
    public static Component saver;
    //BlockUpdate bu[] = new BlockUpdate[0];
    public static Logger logger;

   public static BitmapFont font;
    public static Switch s;
    public static TextField textfieldtest;

    IntegerAuswahl ia;
    public static int w=0;
    public static int h=0;

public static Drawable d;



    ShapeRenderer shapeRenderer;
    public MainGame() {
        Programm.inProgramm = true;

        textfieldtest=new TextField(500,600,100,25);
        ia=new IntegerAuswahl(400,400,50,25);
        s=new Switch(500,500);
        font=  new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new ScreenViewport(cam);
        batch = new SpriteBatch();
        UIbatch = new SpriteBatch();
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(logLevel);

        Gdx.graphics.setTitle("New File");
        DataManager.filename = "New File";

        s.setBackground(AssetLoader.switch_background);
        s.setBackgroundgreen(AssetLoader.switch_background_green);
        s.setInside(AssetLoader.switch_inside);
        Thread blockdebugcreater = new Thread() {
            @Override
            public void run() {
                try {

                    for (int i = 0; i < 12; i = i + 1) {
                        BlockVar.blocks.add(new Block(i, i * 150, 150, 150, 70));

                        System.out.println(i);
                        //  MainGame.logger.finest(String.valueOf(i));
                    }

                    System.out.println("Block creating done");
                }catch (Exception e) {
                    e.printStackTrace(); //for debug to find errors
                }
            }
        };


        blockdebugcreater.start();




        cam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

//Achtung hier ist die Reihenfolge richtig
        Data.init();
        UI.init();

        textfieldtest=new TextField(500,600,100,25);


        ThreadManager.init();


        SerialConnection.searchArduino();

        UIbridge.UpdateConnectionWindowPortsList();
        Gdx.graphics.setWindowedMode(Var.w,Var.h);

        Gdx.graphics.setContinuousRendering(true);
       // Gdx.graphics.requestRendering();


    }



    public void render(float delta) {

        System.out.println("Test");
        try {

            //logger.finest("Blöcke "+BlockVar.blocks.size()+" Sichtbare "+ BlockVar.visibleblocks.size());

            PositionSaver.save();

            System.out.println("Test");


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

                        logger.finer(BlockVar.blocks.get(i).isMarked() + "  id: "+BlockVar.blocks.get(i).getIndex());

                    } catch (Exception e) {

                    }
                }
            }

            UI.updatedragui(shapeRenderer, false, batch);
            UI.update();
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

            if(darkmode) {
                s.setBackground(AssetLoader.switch_background);
                s.setBackgroundgreen(AssetLoader.switch_background_green);
                s.setInside(AssetLoader.switch_inside);
            }else{
                s.setBackground(AssetLoader.switch_background_white);
                s.setBackgroundgreen(AssetLoader.switch_background_green_white);
                s.setInside(AssetLoader.switch_inside);
            }

            //s.setSize(1);
            s.setSize(1f);
            s.setWackelstärke(1);
            s.draw();
            ia.draw(shapeRenderer, batch);
            textfieldtest.setTextAnordnung(1);
            textfieldtest.draw();

        } catch (Exception e) {
            e.printStackTrace();
        }


        if(darkmode) {
            s.setBackground(background);
            s.setBackgroundgreen(Backgroundgreen);
            s.setInside(inside);
        }else{
            s.setBackground(background_white);
            s.setBackgroundgreen(Backgroundgreen_white);
            s.setInside(inside_white);
        }

        //s.setSize(1);
        s.setSize(1f);
        s.setWackelstärke(1);
        s.draw();
        ia.setButtonposition(1);
        ia.draw(shapeRenderer, batch);
        textfieldtest.setTextAnordnung(1);
        textfieldtest.draw();






    }


    public void resize(int width, int height) {



        UI.updateView(width, height);
        viewport.update(width, height);


w=width;
h=height;

    }




    public void hide() {

    }



    public void dispose() {


        //TODO stop Thread Manager Thread
        Data.close();
        batch.dispose();
        Gdx.app.exit();
        System.exit(0);
    }
}
