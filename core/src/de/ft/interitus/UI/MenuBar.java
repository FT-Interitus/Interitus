/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

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
import de.ft.interitus.UI.newproject.ImportProject;
import de.ft.interitus.UI.newproject.NewProjectWindow;
import de.ft.interitus.Var;
import de.ft.interitus.WindowManager;
import de.ft.interitus.datamanager.programmdata.Data;
import de.ft.interitus.datamanager.programmdata.Updater;
import de.ft.interitus.datamanager.userdata.UserInteractDataManagerDialog;
import de.ft.interitus.datamanager.userdata.load.DataLoader;
import de.ft.interitus.datamanager.userdata.save.DataSaver;
import de.ft.interitus.network.bettertogether.Manager;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.VCS;


public class MenuBar {
    public static boolean fullscreen = false;
    public static MenuItem menuItem_vollbild;
    public static MenuItem menuItem_newproject;
    // public static MenuItem menuItem_reloadproject;
    public static MenuItem menuItem_import;
    public static MenuItem menuItem_new;
    public static MenuItem menuItem_oeffnen;
    public static MenuItem menuItem_speichern;
    public static MenuItem menuItem_speichernunter;
    public static MenuItem menuItem_einstellungen;
    public static MenuItem menuItem_beenden;

    //Better together
    public static MenuItem menuItem_openserver;
    public static MenuItem menuItem_connecttoserver;

    public static MenuItem menuItem_blockloeschen;
    public static MenuItem menuItem_ausschneiden;
    public static MenuItem menuItem_update;
    public static MenuItem menuItem_ueber;
    public static MenuItem menuItem_showruntimeinfo;
    public static MenuItem menuItem_clearram;

    public static MenuItem menuItem_keeplog;

    public static Menu fileMenu;

    protected static void createMenus() {
        fileMenu = new Menu("Datei");
        Menu editMenu = new Menu("Bearbeiten");
        Menu ConnectionMenue = new Menu("Verbindungen");
        Menu windowMenu = new Menu("Ansicht");
        Menu helpMenu = new Menu("Hilfe");

        menuItem_vollbild = new MenuItem("Vollbild", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (fullscreen == false) {
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                    fullscreen = true;
                } else {
                    Gdx.graphics.setWindowedMode(Var.w, Var.h);
                    WindowManager.blockBatch.getProjectionMatrix().setToOrtho2D(0, 0, WindowAPI.getWidth(), WindowAPI.getHeight());
                    Gdx.gl.glViewport(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
                    fullscreen = false;
                }

            }
        }).setShortcut("F11");


        menuItem_newproject = new MenuItem("Neues Projekt", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                NewProjectWindow NPW = new NewProjectWindow();
                NPW.show();
            }
        }).setShortcut("Strg+N");

        //menuItem_reloadproject = new MenuItem("Projekt neuladen", new ChangeListener() {
        //    @Override
        //    public void changed(ChangeEvent event, Actor actor) {
        //        ProjectManager.reloadProject(Var.openprojectindex);
        //   }
        // });

        menuItem_import = new MenuItem("Import Projekt", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                new ImportProject().show();
            }
        }).setShortcut("Strg+I");
        menuItem_new = new MenuItem("Neu");
        PopupMenu newsubmenu = new PopupMenu();
        newsubmenu.addItem(menuItem_newproject);
        newsubmenu.addItem(menuItem_import);
        menuItem_new.setSubMenu(newsubmenu);

        UI.recent = new MenuItem("Letzte Öffnen");

        menuItem_oeffnen = new MenuItem("Öffnen", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if (!UserInteractDataManagerDialog.isopenopen()) {
                    if (!Var.isclearing) {
                        UserInteractDataManagerDialog.open();
                    } else {
                        Dialogs.showOKDialog(UI.stage, "Bitte Warten", "Das Programm ist gerade mit deinem zuletzt Geöffnetem Programm beschäftigt. Bitte warte noch bis es fertig ist");
                    }
                }


            }
        }).setShortcut("Strg+O");

        menuItem_speichern = new MenuItem("Speichern", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if (ProjectManager.getActProjectVar().vcs == VCS.NONE) {
                    if (ProjectManager.getActProjectVar().path != "") {
                        FileHandle handle = Gdx.files.absolute(ProjectManager.getActProjectVar().path);
                        DataSaver.save(handle);
                        // DataManager.saved();
                    } else {
                        if (!UserInteractDataManagerDialog.issaveopen()) {
                            UserInteractDataManagerDialog.saveas();
                        }
                    }
                } else if (ProjectManager.getActProjectVar().vcs == VCS.ITEV) {

                }
            }
        }).setShortcut("Strg+S");

        menuItem_speichernunter = new MenuItem("Speichern unter", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {


                if (!UserInteractDataManagerDialog.issaveopen()) {
                    UserInteractDataManagerDialog.saveas();
                }
            }
        }).setShortcut("Strg+Shift+S");
        menuItem_einstellungen = new MenuItem("Einstellungen", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                UI.set.show();
            }
        }).setShortcut("Strg+Alt+S");
        menuItem_beenden = new MenuItem("Beenden", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Data.close(true);
                System.exit(0);
            }
        }).setShortcut("Alt+F4");


        menuItem_blockloeschen = new MenuItem("Block löschen", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
        menuItem_ausschneiden = new MenuItem("Ausschneiden", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        }).setShortcut("Strg+X");
        menuItem_update = new MenuItem("Updates..", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                String[] möglichkeiten = {"Überprüfen", "Abbrechen"};
                final int yes = 1;
                final int no = 2;


                Dialogs.showConfirmDialog(UI.stage, "Auf Updates prüfen", "\nSicher das du nach Updates prüfen willst?\nAlle Änderungen in geöffneten Programmen werden dabei verworfen!\n",
                        möglichkeiten, new Integer[]{yes, no},
                        new ConfirmDialogListener<Integer>() {
                            @Override
                            public void result(Integer result) {

                                if (result == yes) {
                                    Updater.check();
                                }

                            }
                        });
            }
        });
        menuItem_ueber = new MenuItem("Über", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Dialogs.showOKDialog(UI.stage, "Über", "Programm-Version: " + Var.PROGRAMM_VERSION);
            }
        });
        menuItem_showruntimeinfo = new MenuItem("Laufzeit-Info", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                Dialogs.showOKDialog(UI.stage, "Laufzeit-Info", "Verfügbarer Arbeitsspeicher: " + Runtime.getRuntime().maxMemory() / 1000000 + " MB" + "\nGenutzer RAM: " + (Gdx.app.getNativeHeap() + Gdx.app.getJavaHeap()) / 1000000 + " MB");
            }
        });

        menuItem_clearram = new MenuItem("Clear RAM", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.gc();
            }
        });

        menuItem_keeplog = new MenuItem("Log behalten", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Var.keeplog = true;
                menuItem_keeplog.setText("Wird behalten");
                menuItem_keeplog.setDisabled(true);

            }
        });

        menuItem_openserver = new MenuItem("Server starten", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Manager.startserver();
            }
        });
        menuItem_connecttoserver = new MenuItem("Mit Server verbinden", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Manager.startclient();


            }
        });

        ConnectionMenue.addItem(menuItem_openserver);
        ConnectionMenue.addItem(menuItem_connecttoserver);

        //recent.setSubMenu(createSubMenu());

        windowMenu.addItem(menuItem_vollbild);
        fileMenu.addItem(menuItem_new);
        fileMenu.addItem(UI.recent);
        fileMenu.addItem(menuItem_oeffnen);
        fileMenu.addItem(menuItem_speichern);
        fileMenu.addItem(menuItem_speichernunter);
        //fileMenu.addItem(menuItem_reloadproject);
        fileMenu.addSeparator();
        fileMenu.addItem(menuItem_einstellungen);
        fileMenu.addItem(menuItem_beenden);
        // ------------------------------------

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

            }
        }).setShortcut("Strg+C"));
        editMenu.addItem(menuItem_ausschneiden);
        editMenu.addItem(UI.paste = new MenuItem("Einfügen", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        }).setShortcut("Strg+V"));


        helpMenu.addItem(menuItem_update);
        helpMenu.addItem(menuItem_showruntimeinfo);
        helpMenu.addItem(menuItem_clearram);

        helpMenu.addItem(menuItem_keeplog);

        helpMenu.addSeparator();

        helpMenu.addItem(menuItem_ueber);


        UI.menuBar.addMenu(fileMenu);
        UI.menuBar.addMenu(editMenu);
        // UI.menuBar.addMenu(ConnectionMenue);
        UI.menuBar.addMenu(windowMenu);
        UI.menuBar.addMenu(helpMenu);


        //   ProgramRegistry.addMenuBarItems();


    }


    protected static PopupMenu createProjectsSubMenu(int count, final String[] projects) {
        PopupMenu menu = new PopupMenu();

        for (int i = count; i > 0; i--) {

            final int finalI = i - 1;

            menu.addItem(new MenuItem(projects[i - 1], new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                    FileHandle handle = Gdx.files.absolute(Data.path.get(finalI));
                    DataLoader.load(handle, Data.filename.get(finalI), Data.path.get(finalI));


                }


            }));
        }
        return menu;
    }

}
