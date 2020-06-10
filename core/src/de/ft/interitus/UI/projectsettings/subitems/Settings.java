package de.ft.interitus.UI.projectsettings.subitems;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisRadioButton;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import de.ft.interitus.UI.MenuBar;
import de.ft.interitus.projecttypes.ProjectVar;
import de.ft.interitus.projecttypes.VCS;

public class Settings {
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

        if(ProjectVar.vcs== VCS.NONE) {
            none.setChecked(true);
            itev.setChecked(false);
            git.setChecked(false);
        }

        if(ProjectVar.vcs== VCS.ITEV) {
            none.setChecked(false);
            itev.setChecked(true);
            git.setChecked(false);
        }

        if(ProjectVar.vcs== VCS.GIT) {
            none.setChecked(false);
            itev.setChecked(false);
            git.setChecked(true);
        }

        //TODO not included yet
        git.setDisabled(true);

        none.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(none.isChecked()) {

                    if(ProjectVar.vcs== VCS.NONE) {
                        apply.setDisabled(true);

                    }else{
                        apply.setDisabled(false);
                    }


                   if(itev.isChecked()) {
                       itev.setChecked(false);
                   }
                    if(git.isChecked()) {
                        git.setChecked(false);
                    }
                }else{
                    if(none.isOver()) {
                        none.setChecked(true);
                    }
                }
            }
        });

        itev.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(itev.isChecked())   {

                    if(ProjectVar.vcs== VCS.ITEV) {
                        apply.setDisabled(true);

                    }else{
                        apply.setDisabled(false);
                    }

                    if(git.isChecked()) {
                        git.setChecked(false);
                    }
                    if(none.isChecked()) {
                        none.setChecked(false);
                    }
                }else if(!itev.isChecked()){
                    if(itev.isOver()) {
                        itev.setChecked(true);
                    }
                }
            }
        });

        git.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(git.isChecked()) {

                    if(ProjectVar.vcs== VCS.GIT) {
                        apply.setDisabled(true);

                    }else{
                        apply.setDisabled(false);
                    }

                    if(itev.isChecked()) {
                        itev.setChecked(false);
                    }
                    if(none.isChecked()) {
                        none.setChecked(false);
                    }
                }else{
                    if(git.isOver()) {
                        git.setChecked(true);
                    }
                }
            }
        });

        apply.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if(none.isChecked()) {
                    ProjectVar.vcs = VCS.NONE;
                    MenuBar.menuItem_speichern.setText("Speichern");
                } else if(git.isChecked()) {
                    ProjectVar.vcs = VCS.GIT;
                }else if(itev.isChecked()) {
                    ProjectVar.vcs = VCS.ITEV;
                    MenuBar.menuItem_speichern.setText("Revision speichern");
                }

                apply.setDisabled(true);

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
