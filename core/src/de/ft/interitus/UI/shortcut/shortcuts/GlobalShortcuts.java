/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.shortcut.shortcuts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.MenuBar;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.newproject.NewProjectWindow;
import de.ft.interitus.UI.settings.SettingsUI;
import de.ft.interitus.UI.shortcut.ShortCut;
import de.ft.interitus.UI.shortcut.ShortCutChecker;
import de.ft.interitus.UI.shortcut.SpecialKeys;
import de.ft.interitus.Var;
import de.ft.interitus.datamanager.userdata.UserInteractDataManagerDialog;
import de.ft.interitus.datamanager.userdata.save.DataSaver;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.UI.UIOpenSettingsEvent;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.VCS;
import de.ft.interitus.utils.ArrayList;

public class GlobalShortcuts implements ShortCutChecker {
    public static ShortCut shortCut_newprojektwindow = new ShortCut("Neues Projekt", MenuBar.menuItem_neues_projekt, SpecialKeys.dualStrg, Input.Keys.N);
    public static ShortCut shortCut_oefnen = new ShortCut("Projekt Öffnen", MenuBar.menuItem_oeffnen, SpecialKeys.dualStrg, Input.Keys.O);
    public static ShortCut shortCut_speichern = new ShortCut("Speichern", MenuBar.menuItem_speichern, SpecialKeys.dualStrg, Input.Keys.S);
    public static ShortCut shortCut_speichern_unter = new ShortCut("Speichern unter", MenuBar.menuItem_speichernunter, SpecialKeys.dualStrg, SpecialKeys.dualShift, Input.Keys.S);
    public static ShortCut shortCut_vollbild = new ShortCut("Vollbild", MenuBar.menuItem_vollbild, Input.Keys.F11);
    public static ShortCut shortCut_einstellungen = new ShortCut("Einstellungen öffnen", MenuBar.menuItem_einstellungen, SpecialKeys.dualStrg, Input.Keys.ALT_LEFT, Input.Keys.S);

    public static ShortCut shortCut_Copy = new ShortCut("Copy", UI.copy, SpecialKeys.dualStrg, Input.Keys.C);
    public static ShortCut shortCut_Paste = new ShortCut("Paste", UI.paste, SpecialKeys.dualStrg, Input.Keys.V);


    public GlobalShortcuts() {

    }

    public static ArrayList<ShortCut> retunrarray() {

        ArrayList<ShortCut> returnarraylist = new ArrayList<>();
        returnarraylist.add(shortCut_newprojektwindow);
        returnarraylist.add(shortCut_oefnen);
        returnarraylist.add(shortCut_speichern);
        returnarraylist.add(shortCut_speichern_unter);
        returnarraylist.add(shortCut_vollbild);
        returnarraylist.add(shortCut_einstellungen);

        returnarraylist.add(shortCut_Copy);
        returnarraylist.add(shortCut_Paste);

        return returnarraylist;

    }

    @Override
    public void check() {

        if (shortCut_einstellungen.isPressed() && !SettingsUI.isopend()) {
            EventVar.uiEventManager.UIOpenSettingsEvent(new UIOpenSettingsEvent(this));
        }

        if (shortCut_vollbild.isPressed()) {
            if (MenuBar.fullscreen == false) {
                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                MenuBar.fullscreen = true;
            } else {
                Gdx.graphics.setWindowedMode(Var.w, Var.h);
                ProgrammingSpace.batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                Gdx.gl.glViewport(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
                MenuBar.fullscreen = false;
            }
        }
        if (shortCut_newprojektwindow.isPressed()) {
            NewProjectWindow NPW = new NewProjectWindow();
            NPW.show();
        }
        if (shortCut_oefnen.isPressed() && !UserInteractDataManagerDialog.isopenopen()) {
            if (!Var.isclearing) {
                if (ProjectManager.getActProjectVar().changes) {
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

                                        UserInteractDataManagerDialog.open();
                                    }

                                    if (result == everything) {
                                        if (ProjectManager.getActProjectVar().vcs == VCS.NONE) {
                                            if (ProjectManager.getActProjectVar().path != "") {
                                                FileHandle handle = Gdx.files.external(ProjectManager.getActProjectVar().path);
                                                DataSaver.save(handle);
                                                //      DataManager.saved();
                                            } else {
                                                UserInteractDataManagerDialog.saveas();
                                            }
                                        } else if (ProjectManager.getActProjectVar().vcs == VCS.ITEV) {

                                        }
                                    }

                                    if (result == something) {


                                    }
                                }
                            });


                } else {
                    UserInteractDataManagerDialog.open();
                }
            } else {
                Dialogs.showOKDialog(UI.stage, "Bitte Warten", "Das Programm ist gerade mit deinem zuletzt Geöffnetem Programm beschäftigt. Bitte warte noch bis es fertig ist");
            }
        }

        if (shortCut_speichern.isPressed()) {
            if (ProjectManager.getActProjectVar().vcs == VCS.NONE) {
                if (ProjectManager.getActProjectVar().path != "") {
                    FileHandle handle = Gdx.files.absolute(ProjectManager.getActProjectVar().path);
                    DataSaver.save(handle);
                    //     DataManager.saved();
                } else {
                    if (!UserInteractDataManagerDialog.issaveopen()) {
                        UserInteractDataManagerDialog.saveas();
                    }
                }
            } else if (ProjectManager.getActProjectVar().vcs == VCS.ITEV) {

            }
        }

        if (shortCut_speichern_unter.isPressed()) {
            if (!UserInteractDataManagerDialog.issaveopen()) {
                UserInteractDataManagerDialog.saveas();
            }
        }
    }
}
