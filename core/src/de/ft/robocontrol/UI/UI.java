package de.ft.robocontrol.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;
import de.ft.robocontrol.MainGame;
import de.ft.robocontrol.data.programm.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static de.ft.robocontrol.UI.MenuBar.createSubMenu;

public class UI {
    public static Stage stage;
    public static MenuItem recent;
    protected static MenuBar menuBar;

    public static void initdragui() {

    }

    public static void abgerundetesRechteck(ShapeRenderer renderer, int x, int y, int w, int h, int r) {
        w = w - r * 2;
        h = h - r * 2;
        renderer.circle(x + r, y + r, r);
        renderer.circle(x + r + w, y + r, r);
        renderer.circle(x + r, y + r + h, r);
        renderer.circle(x + r + w, y + r + h, r);

        renderer.rect(x + r, y, w, 40);
        renderer.rect(x + r, y + h, w, r * 2);
        renderer.rect(x, y + r, r * 2, h);
        renderer.rect(x + w, y + r, r * 2, h);

        renderer.rect(x + r, y + r, w, h);
    }


    public static void updatedragui(ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);


        renderer.setColor(new Color(0.15f, 0.15f, 0.15f, 1));

        int abstandvonRand = 10;
        int unteneinteilung = 300;
        int untenhohe = 125;
        int radius = 10;

        abgerundetesRechteck(renderer, abstandvonRand, untenhohe + abstandvonRand, Gdx.graphics.getWidth() - abstandvonRand * 2, Gdx.graphics.getHeight() - untenhohe + abstandvonRand - 45 - abstandvonRand, radius);


        renderer.setColor(new Color(0.2f, 0.1f, 0.1f, 1));
        abgerundetesRechteck(renderer, abstandvonRand, abstandvonRand, Gdx.graphics.getWidth() - abstandvonRand * 2 - unteneinteilung, untenhohe - abstandvonRand, radius);
        renderer.setColor(new Color(0.2f, 0.2f, 0.2f, 1));
        abgerundetesRechteck(renderer, Gdx.graphics.getWidth() - unteneinteilung, abstandvonRand, unteneinteilung - abstandvonRand, untenhohe - abstandvonRand, radius);

        renderer.end();

    }

    public static void init() {
        VisUI.load(VisUI.SkinScale.X1);
        stage = new Stage(MainGame.viewport);
        final Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        Gdx.input.setInputProcessor(stage);

        menuBar = new MenuBar();
        menuBar.setMenuListener(new MenuBar.MenuBarListener() {
            @Override
            public void menuOpened(Menu menu) {
                MainGame.logger.info("Opened menu: " + menu.getTitle());

            }

            @Override
            public void menuClosed(Menu menu) {
                MainGame.logger.info("Closed menu: " + menu.getTitle());
            }
        });

        root.add(menuBar.getTable()).expandX().fillX().row();
        root.add().expand().fill();
        de.ft.robocontrol.UI.MenuBar.createMenus();
        Settings.init();


        Thread UIthread = new Thread() {

            @Override
            public void run() {
                Timer time = new Timer();
                time.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
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

                    }
                }, 0, 500);
            }
        };

        UIthread.start();


    }

    public static void update() {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));

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
