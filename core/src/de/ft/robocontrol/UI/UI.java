package de.ft.robocontrol.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;
import com.kotcrab.vis.ui.widget.PopupMenu;
import de.ft.robocontrol.MainGame;
import de.ft.robocontrol.Var;
import de.ft.robocontrol.data.LoadSave;
import de.ft.robocontrol.utils.JSONParaser;
import sun.rmi.rmic.Main;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class UI {
    private static Stage stage;
    private static MenuBar menuBar;
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
            public void menuOpened (Menu menu) {
                MainGame.logger.info("Opened menu: " + menu.getTitle());

            }

            @Override
            public void menuClosed (Menu menu) {
                MainGame.logger.info("Closed menu: " + menu.getTitle());
            }
        });

        root.add(menuBar.getTable()).expandX().fillX().row();
        root.add().expand().fill();
        createMenus();
    }
public static void update() {
    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
    stage.draw();
}

    private static void createMenus () {
        Menu fileMenu = new Menu("Datei");
        Menu editMenu = new Menu("Bearbeiten");
        Menu windowMenu = new Menu("Ansicht");
        Menu helpMenu = new Menu("Hilfe");

        MenuItem subMenuItem = new MenuItem("Letzte Öffnen");

        subMenuItem.setSubMenu(createSubMenu());

        fileMenu.addItem(new MenuItem("Neu", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                final Thread clear =new Thread() {
                    @Override
                    public void run() {
                        for(int i = 0; i< MainGame.blocks.size(); i=i+1){
                            MainGame.blocks.get(i).delete();
                        }
                    }
                };
                clear.start();
                MainGame.blocks.clear();
            }
        }).setShortcut("Strg+N"));



        fileMenu.addItem(subMenuItem);
        fileMenu.addItem(new MenuItem("Öffnen", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                LoadSave.open();

            }
        }).setShortcut("Strg+O"));
        fileMenu.addItem(new MenuItem("Speichern", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(Var.path !="") {
                    FileHandle handle = Gdx.files.internal(Var.path);
                    JSONParaser.writerarray(handle);
                }else {
                    LoadSave.saveas();
                }
             }
        }).setShortcut("Strg+S"));
        fileMenu.addItem(new MenuItem("Speichern unter", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {



               LoadSave.saveas();
            }
        }).setShortcut("Strg+shift+S"));
        fileMenu.addSeparator();
        fileMenu.addItem(new MenuItem("Einstellungen"));
        fileMenu.addItem(new MenuItem("Beenden", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.exit(0);
            }
        }).setShortcut("Alt+F4"));


        // ---

        editMenu.addItem(new MenuItem("Rückgänig").setShortcut("Strg+Z"));
        editMenu.addItem(new MenuItem("Wiederherstellen").setShortcut("Strg+Y"));
        editMenu.addSeparator();
        editMenu.addItem(new MenuItem("Kopieren").setShortcut("Strg+C"));
        editMenu.addItem(new MenuItem("Ausschneiden").setShortcut("Strg+X"));
        editMenu.addItem(new MenuItem("Einfügen").setShortcut("Strg+V"));


        windowMenu.addItem(new MenuItem("menuitem #9"));
        windowMenu.addItem(new MenuItem("menuitem #10"));
        windowMenu.addItem(new MenuItem("menuitem #11"));
        windowMenu.addSeparator();
        windowMenu.addItem(new MenuItem("menuitem #12"));

        helpMenu.addItem(new MenuItem("Über", new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                Dialogs.showOKDialog(stage, "Über", "Programm-Version: " + Var.GAME_VERSION);
            }
        }));

       menuBar.addMenu(fileMenu);
       menuBar.addMenu(editMenu);
       menuBar.addMenu(windowMenu);
       menuBar.addMenu(helpMenu);
    }

    private static PopupMenu createSubMenu() {
        PopupMenu menu = new PopupMenu();
        menu.addItem(new MenuItem("submenuitem #1"));
        menu.addItem(new MenuItem("submenuitem #2"));
        menu.addSeparator();
        menu.addItem(new MenuItem("submenuitem #3"));
        menu.addItem(new MenuItem("submenuitem #4"));
        return menu;
    }

    public static void updateView(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
