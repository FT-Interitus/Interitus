/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.projectsettings.subitems;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisRadioButton;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import de.ft.interitus.UI.MenuBar;
import de.ft.interitus.UI.UI;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.VCS;

public class VersionControll {
    private static VisRadioButton none;
    private static VisRadioButton itev;
    private static VisRadioButton git;
    private static VisTextButton apply;

    public static void add(VisTable builder) {


        none = new VisRadioButton("Kein");
        itev = new VisRadioButton("ITEV");
        git = new VisRadioButton("Git");
        apply = new VisTextButton("Anwenden");
        apply.setDisabled(true);

        if (ProjectManager.getActProjectVar().vcs == VCS.NONE) {
            none.setChecked(true);
            itev.setChecked(false);
            git.setChecked(false);
        }

        if (ProjectManager.getActProjectVar().vcs == VCS.ITEV) {
            none.setChecked(false);
            itev.setChecked(true);
            git.setChecked(false);
        }

        if (ProjectManager.getActProjectVar().vcs == VCS.GIT) {
            none.setChecked(false);
            itev.setChecked(false);
            git.setChecked(true);
        }

        //TODO not included yet
        git.setDisabled(true);
        itev.setDisabled(true);

        none.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (none.isChecked()) {

                    apply.setDisabled(ProjectManager.getActProjectVar().vcs == VCS.NONE);


                    if (itev.isChecked()) {
                        itev.setChecked(false);
                    }
                    if (git.isChecked()) {
                        git.setChecked(false);
                    }
                } else {
                    if (none.isOver()) {
                        none.setChecked(true);
                    }
                }
            }
        });

        itev.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (itev.isChecked()) {

                    apply.setDisabled(ProjectManager.getActProjectVar().vcs == VCS.ITEV);

                    if (git.isChecked()) {
                        git.setChecked(false);
                    }
                    if (none.isChecked()) {
                        none.setChecked(false);
                    }
                } else if (!itev.isChecked()) {
                    if (itev.isOver()) {
                        itev.setChecked(true);
                    }
                }
            }
        });

        git.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (git.isChecked()) {

                    apply.setDisabled(ProjectManager.getActProjectVar().vcs == VCS.GIT);

                    if (itev.isChecked()) {
                        itev.setChecked(false);
                    }
                    if (none.isChecked()) {
                        none.setChecked(false);
                    }
                } else {
                    if (git.isOver()) {
                        git.setChecked(true);
                    }
                }
            }
        });

        apply.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                ProjectManager.getActProjectVar().changes = true;
                if (none.isChecked()) {
                    if (ProjectManager.getActProjectVar().vcs == VCS.ITEV) {
                        String[] möglichkeiten = {"OK", "Abbrechen"};


                        final int nothing = 1;
                        final int everything = 2;


                        //confirmdialog may return result of any type, here we are just using ints
                        Dialogs.showConfirmDialog(UI.stage, "Änderung des VCS", "\nAchtung wenn du von ITEV auf kein VCS wechselst, werden alle Revisionen in eine zusammengefasst.\n" +
                                        "Du kannst danach die Revisionen nicht wiederherstellen.\n",
                                möglichkeiten, new Integer[]{nothing, everything},
                                new ConfirmDialogListener<Integer>() {
                                    @Override
                                    public void result(Integer result) {
                                        if (result == nothing) {
                                            ProjectManager.getActProjectVar().vcs = VCS.NONE;
                                            MenuBar.menuItem_speichern.setText("Speichern");
                                            apply.setDisabled(true);
                                        }

                                        if (result == everything) {
                                            itev.setChecked(true);
                                            none.setChecked(false);

                                        }

                                    }
                                });
                    }

                } else if (git.isChecked()) {
                    ProjectManager.getActProjectVar().vcs = VCS.GIT;
                    apply.setDisabled(true);
                } else if (itev.isChecked()) {
                    ProjectManager.getActProjectVar().vcs = VCS.ITEV;
                    MenuBar.menuItem_speichern.setText("Revision speichern");
                    apply.setDisabled(true);
                }


            }
        });

        builder.add(new VisLabel("Wähle dein Versions-Verwaltungs-System:")).align(Align.center).padRight(-200).padTop(-50);
        builder.row();
        builder.add(none).expandX().fillY().padLeft(5);
        builder.add(itev).expandX().fillY().padLeft(5);
        builder.add(git).expandX().fillY().padLeft(5);
        builder.row();
        builder.add(apply).padBottom(-50).align(Align.center).padRight(-200);

    }
}
