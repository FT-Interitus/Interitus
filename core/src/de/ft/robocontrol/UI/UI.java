package de.ft.robocontrol.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;
import de.ft.robocontrol.input.Button;
import de.ft.robocontrol.ProgrammingSpace;
import de.ft.robocontrol.Settings;
import de.ft.robocontrol.Var;
import de.ft.robocontrol.data.programm.Data;
import de.ft.robocontrol.data.user.changes.SaveChanges;
import de.ft.robocontrol.input.check.InputManager;
import de.ft.robocontrol.roboconnection.SerialConnection;
import de.ft.robocontrol.roboconnection.UIbridge;
import de.ft.robocontrol.utils.RoundRectangle;


import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static de.ft.robocontrol.UI.MenuBar.createSubMenu;

public class UI {
    static final Table root = new Table();
    public static Stage stage;
    public static ConnectionWindow connectionWindow;
    protected static MenuItem recent;
    protected static MenuItem revert;
    protected static MenuItem redo;
    protected static MenuItem copy;
    protected static MenuItem paste;
    protected static MenuBar menuBar;
    protected static SettingsUI set;
    public static Texture img_button_verbindungadd;
    public static Texture img_button_verbindungadd_white;
    protected static Button testbutton = new Button();
    Vector3 pos = new Vector3();

    static int abstandvonRand =5;


   static int unteneinteilung = 300;
    static int untenhohe = 125;
   static int radius = 3;


    private static Vector2 lastframecamposition = new Vector2(ProgrammingSpace.cam.position.x, ProgrammingSpace.cam.position.y);


    public static void updatedragui(ShapeRenderer renderer, boolean flaeche, SpriteBatch batch) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        if (Settings.darkmode) {
            renderer.setColor(Colors.darkmode_middle);
        } else {
            renderer.setColor(Colors.whitearea);
        }




        if (flaeche == true) {
            RoundRectangle.abgerundetesRechteck(renderer, abstandvonRand, untenhohe + abstandvonRand, Var.w - abstandvonRand * 2, Var.h - untenhohe + abstandvonRand - 45 - abstandvonRand, radius);
        } else {
            if (Settings.darkmode) {
                renderer.setColor(Colors.darkmode_blockbar);

            } else {
                renderer.setColor(Colors.whitearea);
            }
            RoundRectangle.abgerundetesRechteck(renderer, abstandvonRand, abstandvonRand, Var.w - abstandvonRand * 2 - unteneinteilung, untenhohe - abstandvonRand, radius);
            if (Settings.darkmode) {
                renderer.setColor(Colors.darkmode_connections);
            } else {
                renderer.setColor(Colors.whitearea);
            }
            RoundRectangle.abgerundetesRechteck(renderer, Var.w - unteneinteilung, abstandvonRand, unteneinteilung - abstandvonRand, untenhohe - abstandvonRand, radius);
        }
        renderer.end();


        testbutton.setBounds(Gdx.graphics.getWidth() - unteneinteilung+5,untenhohe-30-5,30,30);
        if(Settings.darkmode) {
            testbutton.setImage(img_button_verbindungadd);
        }else {
            testbutton.setImage(img_button_verbindungadd_white);
        }
        testbutton.draw(renderer,batch);
        testbutton.setVisible(true);
        if(Var.isdialogeopend) {
            testbutton.setDisable(true);

        }else{
            testbutton.setDisable(false);
        }
        if(testbutton.isjustPressed()){
            connectionWindow = new ConnectionWindow();

            connectionWindow.show();
            connectionWindow.selectportlist.setItems(SerialConnection.getPortNames());
            if(!UIbridge.thread.isAlive()) {

            }
            UIbridge.thread.start();

        }

    }

    public static void init() {
        VisUI.load(VisUI.SkinScale.X1);
        stage = new Stage(ProgrammingSpace.viewport, ProgrammingSpace.UIbatch);

        root.setFillParent(true);
        stage.addActor(root);

        InputManager.addProcessor(stage);
        InputManager.updateMultiplexer();

        set = new SettingsUI();

        menuBar = new MenuBar();


        root.add(menuBar.getTable()).expandX().fillX().row();
        root.add().expand().fill().row();




        de.ft.robocontrol.UI.MenuBar.createMenus();


        Thread UIthread = new Thread() {

            @Override
            public void run() {
                Timer time = new Timer();
                time.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {

                        try {

                            ////////////recent//////////////////////////


                            for (int i = 0; i < Data.path.size(); i++) {
                                if (!(new File(Data.path.get(i)).exists())) {
                                    Data.path.remove(i);
                                    Data.filename.remove(i);
                                }
                            }

                            if (Data.path.size() == 0) {
                                recent.setDisabled(true);
                            } else {
                                recent.setDisabled(false);
                            }


                            ///////////////////////////////////


                            /////////////revert//////////////

                            if (SaveChanges.checkstack()) {
                                revert.setDisabled(true);
                            } else {
                                revert.setDisabled(false);
                            }

                            ///////Redo//////////////


                            if (SaveChanges.checkredostack()) {
                                redo.setDisabled(true);
                            } else {
                                redo.setDisabled(false);
                            }

                        }catch (Exception e) {
                            e.printStackTrace(); //for debug to find errors
                        }
                    }
                }, 0, 500);
            }
        };

        UIthread.start();


    }

    public static void update() {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));

        //root.setPosition(0,0);


        root.setPosition(ProgrammingSpace.cam.position.x - ((float) Gdx.graphics.getWidth()) / 2, ProgrammingSpace.cam.position.y - ((float) Gdx.graphics.getHeight()) / 2);


        stage.draw();

        recent.setSubMenu(createSubMenu(Data.filename.size(), GetStringArray(Data.filename)));

    }


    public static String[] GetStringArray(ArrayList<String> arr) {

        // declaration and initialise String Array
        String[] str = new String[arr.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {


            // Assign each value to String array
            str[j] = arr.get(j);
        }

        return str;
    }

    public static void updateView(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
