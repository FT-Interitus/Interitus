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
import de.ft.interitus.Block.BlockVar;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.Var;
import de.ft.interitus.data.programm.Data;
import de.ft.interitus.data.user.DataLoader;
import de.ft.interitus.data.user.DataSaver;
import de.ft.interitus.data.user.LoadSave;
import de.ft.interitus.data.user.changes.DataManager;
import de.ft.interitus.data.user.changes.SaveChanges;
import de.ft.interitus.data.user.experience.ExperienceVar;
import de.ft.interitus.utils.ClearActOpenProgramm;


public class MenuBar {
    public static boolean fullscreen=false;
    protected static void createMenus() {
        Menu fileMenu = new Menu("Datei");
        Menu editMenu = new Menu("Bearbeiten");
        Menu windowMenu = new Menu("Ansicht");
        Menu helpMenu = new Menu("Hilfe");

        UI.recent = new MenuItem("Letzte Öffnen");

        //recent.setSubMenu(createSubMenu());





        windowMenu.addItem(new MenuItem("Vollbild", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
if(fullscreen==false) {
    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
    fullscreen=true;
}else{
    Gdx.graphics.setWindowedMode(Var.w, Var.h);
    ProgrammingSpace.batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    Gdx.gl.glViewport(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
    fullscreen=false;
}

            }
        }).setShortcut("F11"));





        fileMenu.addItem(new MenuItem("Neu", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {


                if (DataManager.changes) {

                    String[] möglichkeiten = {"Verwerfen", "Speichern", "Abbrechen"};


                    final int nothing = 1;
                    final int everything = 2;
                    final int something = 3;

                    //confirmdialog may return result of any type, here we are just using ints
                    Dialogs.showConfirmDialog(UI.stage, "Ungespeicherte Änderungen", "\nWenn du eine leere Datei öffnest werden womögich Änderungen verworfen.\n",
                            möglichkeiten, new Integer[]{nothing, everything, something},
                            new ConfirmDialogListener<Integer>() {
                                @Override
                                public void result(Integer result) {
                                    if (result == nothing) {
                                        ClearActOpenProgramm.clear();

                                        ExperienceVar.newprojects++;
                                    }

                                    if (result == everything) {
                                        if (DataManager.path != "") {
                                            FileHandle handle = Gdx.files.external(DataManager.path);
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
                    try {
                        ClearActOpenProgramm.clear();
                        ExperienceVar.newprojects++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        }).setShortcut("Strg+N"));


        fileMenu.addItem(UI.recent);
        fileMenu.addItem(new MenuItem("Öffnen", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!Var.isclearing) {
                    if (DataManager.changes) {
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
                                            if (DataManager.path != "") {
                                                FileHandle handle = Gdx.files.external(DataManager.path);
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
                        LoadSave.open();
                    }
                } else {
                    Dialogs.showOKDialog(UI.stage, "Bitte Warten", "Das Programm ist gerade mit deinem zuletzt Geöffnetem Programm beschäftigt. Bitte warte noch bis es fertig ist");
                }


            }
        }).setShortcut("Strg+O"));
        fileMenu.addItem(new MenuItem("Speichern", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (DataManager.path != "") {
                    FileHandle handle = Gdx.files.absolute(DataManager.path);
                    DataSaver.save(handle);
                    DataManager.saved();
                } else {
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
        fileMenu.addItem(new MenuItem("Einstellungen", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                UI.set.show();
            }
        }));
        fileMenu.addItem(new MenuItem("Beenden", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Data.close();
                System.exit(0);
            }
        }).setShortcut("Alt+F4"));


        // ---

        editMenu.addItem(UI.revert = new MenuItem("Rückgänig", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!SaveChanges.checkstack()) { //Test if Stack is empty
                    SaveChanges.revert();
                }
            }
        }).setShortcut("Strg+Z"));
        editMenu.addItem(UI.redo = new MenuItem("Wiederherstellen", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!SaveChanges.checkredostack()) {
                    SaveChanges.redo();
                }
            }
        }).setShortcut("Strg+Y"));
        editMenu.addSeparator();
        editMenu.addItem(new MenuItem("Block löschen", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        }));
        editMenu.addSeparator();

        editMenu.addItem(UI.copy = new MenuItem("Kopieren", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (BlockVar.marked) {
                    de.ft.interitus.data.user.clipboard.Manager.Copy();
                }
            }
        }).setShortcut("Strg+C"));
        editMenu.addItem(new MenuItem("Ausschneiden", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        }).setShortcut("Strg+X"));
        editMenu.addItem(UI.paste = new MenuItem("Einfügen", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (de.ft.interitus.data.user.clipboard.Manager.checkcopy()) {
                    de.ft.interitus.data.user.clipboard.Manager.paste();
                }
            }
        }).setShortcut("Strg+V"));



        helpMenu.addItem(new MenuItem("Updates..", new ChangeListener() {
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
        }));

        helpMenu.addItem(new MenuItem("Über", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Dialogs.showOKDialog(UI.stage, "Über", "Programm-Version: " + Var.PROGRAMM_VERSION);
            }
        }));


        UI.menuBar.addMenu(fileMenu);
        UI.menuBar.addMenu(editMenu);
        UI.menuBar.addMenu(windowMenu);
        UI.menuBar.addMenu(helpMenu);
    }


    protected static PopupMenu createSubMenu(int count, final String[] projects) {
        PopupMenu menu = new PopupMenu();

        for (int i = count; i > 0; i--) {

            final int finalI = i - 1;

            menu.addItem(new MenuItem(projects[i - 1], new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                    if (DataManager.changes) {

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
                                           ClearActOpenProgramm.clear();
                                            DataManager.saved();
                                            DataManager.filename = Data.filename.get(finalI);
                                            DataManager.path = Data.path.get(finalI);

                                            FileHandle handle = Gdx.files.absolute(Data.path.get(finalI));
                                            DataLoader.load(handle);

                                        }

                                        if (result == everything) {
                                            if (DataManager.path != "") {
                                                FileHandle handle = Gdx.files.external(DataManager.path);
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

                        ClearActOpenProgramm.clear();
                        DataManager.saved();
                        DataManager.filename = Data.filename.get(finalI);
                        DataManager.path = Data.path.get(finalI);

                        FileHandle handle = Gdx.files.absolute(Data.path.get(finalI));
                        DataLoader.load(handle);
                    }


                }


            }));
        }
        return menu;
    }

}
