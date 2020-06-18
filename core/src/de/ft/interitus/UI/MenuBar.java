package de.ft.interitus.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuItem;
import com.kotcrab.vis.ui.widget.PopupMenu;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.newproject.NewProjectWindow;
import de.ft.interitus.UI.setup.SetupWindow;
import de.ft.interitus.Var;
import de.ft.interitus.data.programm.Data;
import de.ft.interitus.data.user.DataLoader;
import de.ft.interitus.data.user.DataSaver;
import de.ft.interitus.data.user.LoadSave;
import de.ft.interitus.data.user.changes.DataManager;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.UI.UIOpenSettingsEvent;
import de.ft.interitus.plugin.PluginGateway;
import de.ft.interitus.projecttypes.VCS;
import de.ft.interitus.utils.ClearActOpenProgramm;


public class MenuBar {
    public static boolean fullscreen = false;
    public static MenuItem menuItem_vollbild;
    public static MenuItem menuItem_neues_projekt;
    public static MenuItem menuItem_oeffnen;
    public static MenuItem menuItem_speichern;
    public static MenuItem menuItem_speichernunter;
    public static MenuItem menuItem_einstellungen;
    public static MenuItem menuItem_beenden;
    public static MenuItem menuItem_neueverbindung;
    public static MenuItem menuItem_verbindungsmanager;
    public static MenuItem menuItem_blockloeschen;
    public static MenuItem menuItem_ausschneiden;
    public static MenuItem menuItem_update;
    public static MenuItem menuItem_ueber;

    public static Menu fileMenu;

    protected static void createMenus() {
        fileMenu = new Menu("Datei");
        Menu editMenu = new Menu("Bearbeiten");
        Menu ConnectionMenue = new Menu("Verbindungen");
        Menu windowMenu = new Menu("Ansicht");
        Menu helpMenu = new Menu("Hilfe");

        menuItem_vollbild=new MenuItem("Vollbild", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (fullscreen == false) {
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                    fullscreen = true;
                } else {
                    Gdx.graphics.setWindowedMode(Var.w, Var.h);
                    ProgrammingSpace.batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                    Gdx.gl.glViewport(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
                    fullscreen = false;
                }

            }
        }).setShortcut("F11");


        menuItem_neues_projekt=new MenuItem("Neues Projekt", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                NewProjectWindow NPW = new NewProjectWindow();
                NPW.show();
            }
        }).setShortcut("Strg+N");

        UI.recent = new MenuItem("Letzte Öffnen");

        menuItem_oeffnen=new MenuItem("Öffnen", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if (!LoadSave.isopenopen()) {
                    if (!Var.isclearing) {
                        if (Var.openprojects.get(Var.openprojectindex).changes) {
                            String[] möglichkeiten = {"Verwerfen", "Speichern", "Abbrechen"};


                            final int nothing = 1;
                            final int everything = 2;
                            final int something = 3;

                            //confirmdialog may return result of any type, here we are just using ints
                            Dialogs.showConfirmDialog(UI.stage, "Ungespeicherte Änderungen", "\nWenn du eine neue Datei öffnest werden womögich Änderungen verworfen.\n",
                                    möglichkeiten, new Integer[]{nothing, everything, something},
                                    new ConfirmDialogListener<Integer>() {
                                        @Override
                                        public void result(Integer result) {
                                            if (result == nothing) {

                                                LoadSave.open();
                                            }

                                            if (result == everything) {
                                                if(Var.openprojects.get(Var.openprojectindex).vcs== VCS.NONE) {
                                                    if (Var.openprojects.get(Var.openprojectindex).path != "") {
                                                        FileHandle handle = Gdx.files.external(Var.openprojects.get(Var.openprojectindex).path);
                                                        DataSaver.save(handle);
                                                        DataManager.saved();
                                                    } else {
                                                        LoadSave.saveas();
                                                    }
                                                }else if(Var.openprojects.get(Var.openprojectindex).vcs==VCS.ITEV) {

                                                }
                                            }

                                            if (result == something) {


                                            }
                                        }
                                    });


                        } else {
                            LoadSave.open();
                        }
                    } else {
                        Dialogs.showOKDialog(UI.stage, "Bitte Warten", "Das Programm ist gerade mit deinem zuletzt Geöffnetem Programm beschäftigt. Bitte warte noch bis es fertig ist");
                    }
                }


            }
        }).setShortcut("Strg+O");

        menuItem_speichern=new MenuItem("Speichern", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if(Var.openprojects.get(Var.openprojectindex).vcs== VCS.NONE) {
                    if (Var.openprojects.get(Var.openprojectindex).path != "") {
                        FileHandle handle = Gdx.files.absolute(Var.openprojects.get(Var.openprojectindex).path);
                        DataSaver.save(handle);
                        DataManager.saved();
                    } else {
                        if (!LoadSave.issaveopen()) {
                            LoadSave.saveas();
                        }
                    }
                }else if(Var.openprojects.get(Var.openprojectindex).vcs==VCS.ITEV){

                }
            }
        }).setShortcut("Strg+S");

        menuItem_speichernunter=new MenuItem("Speichern unter", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {


                if (!LoadSave.issaveopen()) {
                    LoadSave.saveas();
                }
            }
        }).setShortcut("Strg+Shift+S");
        menuItem_einstellungen=new MenuItem("Einstellungen", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                EventVar.uiEventManager.UIOpenSettingsEvent(new UIOpenSettingsEvent(this));
            }
        }).setShortcut("Strg+Alt+S");
        menuItem_beenden=new MenuItem("Beenden", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Data.close();
                System.exit(0);
            }
        }).setShortcut("Alt+F4");
        menuItem_neueverbindung=new MenuItem("Neue Verbindung", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                try {
                    SetupWindow sw = new SetupWindow();
                    sw.show();
                } catch (NullPointerException e) {

                }
            }
        });
        menuItem_verbindungsmanager=new MenuItem("Verbindungs Manager", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Dialogs.showOKDialog(UI.stage, "++++ )-: ++++", "Dieses Fenster Exestiert noch nicht");
            }
        });
        menuItem_blockloeschen=new MenuItem("Block löschen", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
        menuItem_ausschneiden=new MenuItem("Ausschneiden", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        }).setShortcut("Strg+X");
        menuItem_update=new MenuItem("Updates..", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                String[] möglichkeiten = {"Überprüfen", "Abbrechen"};
                final int yes = 1;
                final int no = 2;


                Dialogs.showConfirmDialog(UI.stage, "Auf Updates prüfen", "\nSicher das du nach Updates prüfen willst?\n",
                        möglichkeiten, new Integer[]{yes, no},
                        new ConfirmDialogListener<Integer>() {
                            @Override
                            public void result(Integer result) {

                                if (result == yes) {
                                    de.ft.interitus.data.programm.Updater.check(true);
                                }

                            }
                        });
            }
        });
        menuItem_ueber=new MenuItem("Über", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Dialogs.showOKDialog(UI.stage, "Über", "Programm-Version: " + Var.PROGRAMM_VERSION);
            }
        });
        //recent.setSubMenu(createSubMenu());

        windowMenu.addItem(menuItem_vollbild);
        fileMenu.addItem(menuItem_neues_projekt);
        fileMenu.addItem(UI.recent);
        fileMenu.addItem(menuItem_oeffnen);
        fileMenu.addItem(menuItem_speichern);
        fileMenu.addItem(menuItem_speichernunter);
        fileMenu.addSeparator();
        fileMenu.addItem(menuItem_einstellungen);
        fileMenu.addItem(menuItem_beenden);
        // ------------------------------------
        ConnectionMenue.addItem(menuItem_neueverbindung);
        ConnectionMenue.addItem(menuItem_verbindungsmanager);
        // --------------------------------------------
        editMenu.addItem(UI.revert = new MenuItem("Rückgänig", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        }).setShortcut("Strg+Z"));
        editMenu.addItem(UI.redo = new MenuItem("Wiederherstellen", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        }).setShortcut("Strg+Y"));
        editMenu.addSeparator();
        editMenu.addItem(menuItem_blockloeschen);
        editMenu.addSeparator();
        editMenu.addItem(UI.copy = new MenuItem("Kopieren", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (Var.openprojects.get(Var.openprojectindex).marked) {
                    de.ft.interitus.data.user.clipboard.Manager.Copy();
                }
            }
        }).setShortcut("Strg+C"));
        editMenu.addItem(menuItem_ausschneiden);
        editMenu.addItem(UI.paste = new MenuItem("Einfügen", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (de.ft.interitus.data.user.clipboard.Manager.checkcopy()) {
                    de.ft.interitus.data.user.clipboard.Manager.paste();
                }
            }
        }).setShortcut("Strg+V"));


        helpMenu.addItem(menuItem_update);

        helpMenu.addItem(menuItem_ueber);


        UI.menuBar.addMenu(fileMenu);
        UI.menuBar.addMenu(editMenu);
        UI.menuBar.addMenu(ConnectionMenue);
        UI.menuBar.addMenu(windowMenu);
        UI.menuBar.addMenu(helpMenu);

        for (int i = 0; i < PluginGateway.pluginMenubar.size(); i++) { //Alle Plugins MenuBar werden der MenuBar
            UI.menuBar.addMenu(PluginGateway.pluginMenubar.get(i));
        }

    }


    protected static PopupMenu createSubMenu(int count, final String[] projects) {
        PopupMenu menu = new PopupMenu();

        for (int i = count; i > 0; i--) {

            final int finalI = i - 1;

            menu.addItem(new MenuItem(projects[i - 1], new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                    if (Var.openprojects.get(Var.openprojectindex).changes) {

                        String[] möglichkeiten = {"Verwerfen", "Speichern", "Abbrechen"};
                        final int nothing = 1;
                        final int everything = 2;
                        final int something = 3;

                        Dialogs.showConfirmDialog(UI.stage, "Ungespeicherte Änderungen", "\nWenn du eine neue Datei öffnest werden womögich Änderungen verworfen.\n",
                                möglichkeiten, new Integer[]{nothing, everything, something},
                                new ConfirmDialogListener<Integer>() {
                                    @Override
                                    public void result(Integer result) {
                                        if (result == nothing) {

                                            DataManager.saved();

                                            FileHandle handle = Gdx.files.absolute(Data.path.get(finalI));
                                            DataLoader.load(handle,Data.filename.get(finalI),Data.path.get(finalI));

                                        }

                                        if (result == everything) {
                                            if (Var.openprojects.get(Var.openprojectindex).path != "") {
                                                FileHandle handle = Gdx.files.external(Var.openprojects.get(Var.openprojectindex).path);
                                                DataSaver.save(handle);
                                                DataManager.saved();
                                            } else {
                                                LoadSave.saveas();
                                            }
                                        }

                                        if (result == something) {


                                        }
                                    }
                                });


                    } else {

                        //ClearActOpenProgramm.clear();
                        DataManager.saved();


                        FileHandle handle = Gdx.files.absolute(Data.path.get(finalI));
                        DataLoader.load(handle,Data.filename.get(finalI),Data.path.get(finalI));
                    }


                }


            }));
        }
        return menu;
    }

}
