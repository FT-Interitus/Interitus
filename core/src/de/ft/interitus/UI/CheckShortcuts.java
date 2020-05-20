package de.ft.interitus.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.UI.input.SpecialKeys;
import de.ft.interitus.UI.input.shortcut.ShortCut;
import de.ft.interitus.UI.newproject.NewProjectWindow;
import de.ft.interitus.Var;
import de.ft.interitus.data.user.DataSaver;
import de.ft.interitus.data.user.LoadSave;
import de.ft.interitus.data.user.changes.DataManager;

public class CheckShortcuts {
    public static boolean blockshortcuts = false;
    public static ShortCut shortCut_newprojektwindow=new ShortCut(SpecialKeys.dualStrg,Input.Keys.N);
    public static ShortCut shortCut_oefnen=new ShortCut(SpecialKeys.dualStrg, Input.Keys.O);
    public static ShortCut shortCut_speichern=new ShortCut(SpecialKeys.dualStrg, Input.Keys.S);
    public static ShortCut shortCut_speichern_unter=new ShortCut(SpecialKeys.dualStrg, Input.Keys.S,SpecialKeys.dualShift);



    public static void check() {

        if(shortCut_newprojektwindow.isPressed()){
            NewProjectWindow NPW = new NewProjectWindow();
            NPW.show();
        }
        if(shortCut_oefnen.isPressed() && !LoadSave.isopenopen()){
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

        if(shortCut_speichern.isPressed()){
            if (DataManager.path != "") {
                FileHandle handle = Gdx.files.absolute(DataManager.path);
                DataSaver.save(handle);
                DataManager.saved();
            } else {
                if (!LoadSave.issaveopen()) {
                    LoadSave.saveas();
                }
            }
        }

        if(shortCut_speichern_unter.isPressed()){
            if (!LoadSave.issaveopen()) {
                LoadSave.saveas();
            }
        }

        if (!blockshortcuts) {

            //TODO Changeable in the Settings   <- Deswegen habe ich erstmal nur Datei gemacht
            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {






            }


        }


    }
}
