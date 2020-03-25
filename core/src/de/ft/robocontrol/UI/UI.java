package de.ft.robocontrol.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuItem;
import com.kotcrab.vis.ui.widget.PopupMenu;
import de.ft.robocontrol.MainGame;
import de.ft.robocontrol.Var;
import de.ft.robocontrol.utils.JSONParaser;
import sun.rmi.rmic.Main;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class UI {

    public static void createMenus () {
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

                Thread open = new Thread() {
                    @Override
                    public void run() {
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                        fileChooser.setFileFilter(new FileNameExtensionFilter("Projektdatei (.rac)", "rac"));
                        int result = fileChooser.showOpenDialog(MainGame.saver);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                            FileHandle handle = Gdx.files.internal(selectedFile.getAbsolutePath());
                            Var.path = selectedFile.getAbsolutePath();
                            JSONParaser.load(handle);

                        }
                    }
                } ;
                open.start();

            }
        }).setShortcut("Strg+O"));
        fileMenu.addItem(new MenuItem("Speichern", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(Var.path !="") { //TODO hier fehler
                    FileHandle handle = Gdx.files.internal(Var.path);
                    JSONParaser.load(handle);
                }else {
                    Thread save = new Thread() {
                        @Override
                        public void run() {


                            JFileChooser fileChooser = new JFileChooser();
                            fileChooser.setDialogTitle("Projekt-Ordner wählen");

                            int userSelection = fileChooser.showSaveDialog(MainGame.saver);
                            fileChooser.setMultiSelectionEnabled(false);


                            if (userSelection == JFileChooser.APPROVE_OPTION) {
                                File fileToSave = fileChooser.getSelectedFile();
                                System.out.println("Save as file: " + fileToSave.getAbsolutePath()+".rac");

                                if(fileToSave.getAbsolutePath().contains(".rac")) {
                                    Var.path = fileToSave.getAbsolutePath();
                                    JSONParaser.writerarray(Gdx.files.absolute(fileToSave.getAbsolutePath()));
                                }else{
                                    Var.path = fileToSave.getAbsolutePath()+".rac";
                                    JSONParaser.writerarray(Gdx.files.absolute(fileToSave.getAbsolutePath()+".rac"));
                                }
                                System.out.println(Var.path);

                            }
                        }
                        //SAVE DATA
                    };

                    save.start();
                }
             }
        }).setShortcut("Strg+S"));
        fileMenu.addItem(new MenuItem("Speichern unter", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                Thread save = new Thread() {
                    @Override
                    public void run() {


                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Projekt-Ordner wählen");

                        int userSelection = fileChooser.showSaveDialog(MainGame.saver);
                        fileChooser.setMultiSelectionEnabled(false);


                        if (userSelection == JFileChooser.APPROVE_OPTION) {
                            File fileToSave = fileChooser.getSelectedFile();
                            System.out.println("Save as file: " + fileToSave.getAbsolutePath()+".rac");

                            if(fileToSave.getAbsolutePath().contains(".rac")) {
                                Var.path = fileToSave.getAbsolutePath();
                                JSONParaser.writerarray(Gdx.files.absolute(fileToSave.getAbsolutePath()));
                            }else{
                                Var.path = fileToSave.getAbsolutePath()+".rac";
                                JSONParaser.writerarray(Gdx.files.absolute(fileToSave.getAbsolutePath()+".rac"));
                            }
                        }
                    }
                    //SAVE DATA
                };

                save.start();
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
                Dialogs.showOKDialog(MainGame.stage, "Über", "Programm-Version: " + Var.GAME_VERSION);
            }
        }));

        MainGame.menuBar.addMenu(fileMenu);
        MainGame.menuBar.addMenu(editMenu);
        MainGame.menuBar.addMenu(windowMenu);
       MainGame.menuBar.addMenu(helpMenu);
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
}
