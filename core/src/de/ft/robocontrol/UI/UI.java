package de.ft.robocontrol.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;
import com.kotcrab.vis.ui.widget.PopupMenu;
import de.ft.robocontrol.MainGame;
import de.ft.robocontrol.Var;
import de.ft.robocontrol.data.programm.Data;
import de.ft.robocontrol.data.user.DataLoader;
import de.ft.robocontrol.data.programm.DataManager;
import de.ft.robocontrol.data.user.DataSaver;
import de.ft.robocontrol.data.user.LoadSave;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class UI {
    public static Stage stage;
    private static MenuBar menuBar;
   public static MenuItem recent;

    public static void initdragui() {

    }

    public static void abgerundetesRechteck(ShapeRenderer renderer,int x, int y, int w, int h, int r){
        w=w-r*2;
        h=h-r*2;
        renderer.circle(x+r,y+r,r);
        renderer.circle(x+r+w,y+r,r);
        renderer.circle(x+r,y+r+h,r);
        renderer.circle(x+r+w,y+r+h,r);

        renderer.rect(x+r,y,w,40);
        renderer.rect(x+r,y+h,w,r*2);
        renderer.rect(x,y+r,r*2,h);
        renderer.rect(x+w,y+r,r*2,h);

        renderer.rect(x+r,y+r,w,h);
    }



    public static void updatedragui(ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);




        renderer.setColor(new Color(0.15f,0.15f,0.15f,1));

        int abstandvonRand=10;
        int unteneinteilung=300;
        int untenhohe=125;
        int radius=10;

        abgerundetesRechteck(renderer,abstandvonRand,untenhohe+abstandvonRand,Gdx.graphics.getWidth()-abstandvonRand*2,Gdx.graphics.getHeight()-untenhohe+abstandvonRand-45-abstandvonRand,radius);


        renderer.setColor(new Color(0.2f,0.1f,0.1f,1));
        abgerundetesRechteck(renderer,abstandvonRand,abstandvonRand,Gdx.graphics.getWidth()-abstandvonRand*2-unteneinteilung,untenhohe-abstandvonRand,radius);
        renderer.setColor(new Color(0.2f,0.2f,0.2f,1));
        abgerundetesRechteck(renderer,Gdx.graphics.getWidth()-unteneinteilung,abstandvonRand,unteneinteilung-abstandvonRand,untenhohe-abstandvonRand,radius);

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


       Thread test = new Thread(){

           @Override
           public void run() {
               Timer time = new Timer();
               time.scheduleAtFixedRate(new TimerTask() {
                   @Override
                   public void run() {
                       for(int i = 0; i<Data.path.size();i++) {
                           if(!(new File(Data.path.get(i)).exists())) {
                               Data.path.remove(i);
                               Data.filename.remove(i);
                           }
                       }
                   }
               },0,500);
           }
       };

       test.start();


    }
public static void update() {
    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));

    stage.draw();

    recent.setSubMenu(createSubMenu(Data.filename.size(),GetStringArray(Data.filename)));

}

    private static void createMenus () {
        Menu fileMenu = new Menu("Datei");
        Menu editMenu = new Menu("Bearbeiten");
        Menu windowMenu = new Menu("Ansicht");
        Menu helpMenu = new Menu("Hilfe");

        recent = new MenuItem("Letzte Öffnen");

        //recent.setSubMenu(createSubMenu());

        fileMenu.addItem(new MenuItem("Neu", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                final Thread clear =new Thread() {
                    @Override
                    public void run() {

                        if(DataManager.changes) {

                            String[] möglichkeiten = {"Verwerfen", "Speichern", "Abbrechen"};


                            final int nothing = 1;
                            final int everything = 2;
                            final int something = 3;

                            //confirmdialog may return result of any type, here we are just using ints
                            Dialogs.showConfirmDialog(stage, "Ungespeicherte Änderungen", "\nWenn du eine leere Datei öffnest werden womögich Änderungen verworfen.\n",
                                    möglichkeiten, new Integer[]{nothing, everything, something},
                                    new ConfirmDialogListener<Integer>() {
                                        @Override
                                        public void result(Integer result) {
                                            if (result == nothing) {
                                                for (int i = 0; i < MainGame.blocks.size(); i = i + 1) {
                                                    MainGame.blocks.get(i).delete();
                                                }
                                                DataManager.saved();
                                                DataManager.filename = "New File";
                                                DataManager.path = "";
                                                MainGame.blocks.clear();
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

                        }else {


                            for (int i = 0; i < MainGame.blocks.size(); i = i + 1) {
                                MainGame.blocks.get(i).delete();
                            }
                            DataManager.saved();
                            DataManager.filename = "New File";
                            DataManager.path = "";
                            MainGame.blocks.clear();
                        }
                    }
                };
                clear.start();


            }
        }).setShortcut("Strg+N"));



        fileMenu.addItem(recent);
        fileMenu.addItem(new MenuItem("Öffnen", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(DataManager.changes) {
                    String[] möglichkeiten ={"Verwerfen","Speichern","Abbrechen"};


                    final int nothing = 1;
                    final int everything = 2;
                    final int something = 3;

                    //confirmdialog may return result of any type, here we are just using ints
                    Dialogs.showConfirmDialog(stage, "Ungespeicherte Änderungen", "\nWenn du eine neue Datei öffnest werden womögich Änderungen verworfen.\n",
                            möglichkeiten, new Integer[]{nothing, everything, something},
                            new ConfirmDialogListener<Integer>() {
                                @Override
                                public void result (Integer result) {
                                    if (result == nothing) {
                                        for (int i = 0; i < MainGame.blocks.size(); i = i + 1) {
                                            MainGame.blocks.get(i).delete();
                                        }
                                        MainGame.blocks.clear();
                                        DataManager.saved();
                                        DataManager.filename = "New File";
                                        DataManager.path = "";
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






                }else{
                    LoadSave.open();
                }


            }
        }).setShortcut("Strg+O"));
        fileMenu.addItem(new MenuItem("Speichern", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(DataManager.path !="") {
                    FileHandle handle = Gdx.files.absolute(DataManager.path);
                    DataSaver.save(handle);
                    DataManager.saved();
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

    public static PopupMenu createSubMenu(int count, final String[] projects) {
        PopupMenu menu = new PopupMenu();

        for(int i = count; i>0;i--) {

            final int finalI = i-1;

            menu.addItem(new MenuItem(projects[i-1], new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                if(DataManager.changes) {

                    String[] möglichkeiten = {"Verwerfen", "Speichern", "Abbrechen"};
                    final int nothing = 1;
                    final int everything = 2;
                    final int something = 3;

                    Dialogs.showConfirmDialog(stage, "Ungespeicherte Änderungen", "\nWenn du eine neue Datei öffnest werden womögich Änderungen verworfen.\n",
                            möglichkeiten, new Integer[]{nothing, everything, something},
                            new ConfirmDialogListener<Integer>() {
                                @Override
                                public void result (Integer result) {
                                    if (result == nothing) {
                                        for (int i = 0; i < MainGame.blocks.size(); i = i + 1) {
                                            MainGame.blocks.get(i).delete();
                                        }
                                        MainGame.blocks.clear();
                                        DataManager.saved();
                                        DataManager.filename =  Data.filename.get(finalI);
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


                }else{
                    for (int i = 0; i < MainGame.blocks.size(); i = i + 1) {
                        MainGame.blocks.get(i).delete();
                    }
                    MainGame.blocks.clear();
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

    public static String[] GetStringArray(ArrayList<String> arr)
    {

        // declaration and initialise String Array
        String str[] = new String[arr.size()];

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
