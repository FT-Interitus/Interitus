/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.newproject;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.building.GridTableBuilder;
import com.kotcrab.vis.ui.building.StandardTableBuilder;
import com.kotcrab.vis.ui.building.TableBuilder;
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.building.utilities.Padding;
import com.kotcrab.vis.ui.widget.*;
import de.ft.interitus.Settings;
import de.ft.interitus.UI.MenuBar;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.BlockTypes.ProjectTypesVar;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.ArrayList;

import java.util.Timer;
import java.util.TimerTask;


public class NewProjectWindow {
    public static SetupBuilder setupBuilder;
    public static VisTable content;

    public final static VisTextButton Button_next = new VisTextButton("Erstellen");
    public final static VisTextButton Button_cancle = new VisTextButton("Cancel");
    public final static VisLabel errorLabel = new VisLabel("Bitte gib einen gültigen Namen an");

    public final static VisTextField nameinput = new VisTextField();
    public final static VisTextField pfadinput = new VisTextField();
    public final static CharSequence text = "Name: ";
    public final static VisLabel namelable = new VisLabel(text);
    public final static CharSequence pfadtext = "Pfad: ";
    public final static CharSequence auftragtext = "Bitte gebe hier einen Name für das neue Projekt ein.";
    public final static VisLabel auftrag = new VisLabel(auftragtext);
    public static VisSelectBox<String> selectProjectType;

    final Padding padding = new Padding(2, 3);

    public NewProjectWindow() {

        new GridTableBuilder(4);
    }


    public static void update() {
        try {
            setupBuilder.pack();
        } catch (NullPointerException e) { //kann passieren wenn das Fenster noch nicht initialisiert wurde
        }

    }

    public static boolean isopend() {
        try {
            return setupBuilder.testopen();
        } catch (Exception e) {
            return false;
        }
    }


    public void show() {
        if (setupBuilder == null) {
            content = new VisTable();
            setupBuilder = new SetupBuilder("Neues Projekt Setup", new StandardTableBuilder(padding));
            setupBuilder.pack();
        } else {
            setupBuilder.pack();
        }

        nameinput.setText("");
        Button_next.setDisabled(true);
        UIVar.isdialogeopend = true;

        UI.stage.addActor(setupBuilder);


    }


    private class SetupBuilder extends VisWindow {
        public SetupBuilder(String name, final TableBuilder builder) {
            super(name);


            setModal(true);

            setLayoutEnabled(true);
            builder.setTablePadding(new Padding(20, 30, 20, 30));

            builder.append(CellWidget.of(content).fillX().fillY().expandX().expandY().wrap());
            builder.row();

            selectProjectType = new VisSelectBox<String>();

            final ArrayList<String> items = new ArrayList<>();
            for (int i = 0; i < ProjectTypesVar.projectTypes.size(); i++) {
                items.add(ProjectTypesVar.projectTypes.get(i).getName());

            }


            String[] itemsstring = new String[items.size()];
            for (int i = 0; i < items.size(); i++) {
                itemsstring[i] = items.get(i);
            }

            selectProjectType.setItems(itemsstring);

            errorLabel.setColor(1, 0, 0, 1);
            VisTable buttonTable = new VisTable(true);
            buttonTable.add(errorLabel).fillX().width(60).pad(350, 0, 0, 350);
            buttonTable.add(Button_cancle).fillX().width(60).pad(350, 0, 0, 0);
            buttonTable.add(Button_next).fillX().width(70).pad(350, 0, 0, 0);

            builder.append(buttonTable);

            pfadinput.setText(Settings.defaultpfad);

            content.add(auftrag).row();
            content.add(namelable).expandX().padLeft(-400).padBottom(-50);
            content.add(nameinput).expandX().width(400).padLeft(-500).padBottom(-50).row();

            // content.add(pfadlable).expandX().padLeft(-400).padBottom(-150);
            // content.add(pfadinput).expandX().width(400).padLeft(-500).padBottom(-150).row();
            content.add(selectProjectType).expandX().padBottom(-250).row();


            Table table = builder.build();

            add(table).expand().fill().size(600, 450);


            centerWindow();
            pack();


            Button_cancle.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                    UIVar.isdialogeopend = false;
                    setupBuilder.close();
                }
            });
            Button_next.setDisabled(true);

            Button_next.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    ProjectManager.addProject(ProjectTypesVar.projectTypes.get(items.indexOf(selectProjectType.getSelected())).init());


                    ProjectManager.change(Var.openprojects.size() - 1);
                    ProjectManager.getActProjectVar().setFilename(nameinput.getText());

                    MenuBar.menuItem_speichern.setText("Speichern");


                    BlockTappedBar.init();
                    UIVar.isdialogeopend = false;
                    // ClearActOpenProgramm.clear();


                    ProjectManager.getActProjectVar().projectType.initProject();


                    setupBuilder.close();
                }
            });


            nameinput.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent changeEvent, Actor actor) {
                    if (nameinput.getText().length() < 35 && nameinput.getText().length() > 2 && !nameinput.getText().startsWith(" ") && !nameinput.getText().endsWith(" ") && !nameinput.getText().contains("ä") && !nameinput.getText().contains("ü") && !nameinput.getText().contains("ö")) {
                        Button_next.setDisabled(false);
                        errorLabel.setText("");
                    } else {
                        Button_next.setDisabled(true);
                        errorLabel.setText("Bitte gib einen gültigen Namen an");
                    }


                }
            });

            Timer time = new Timer();
            time.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {
                    if (UIVar.isdialogeopend && !NewProjectWindow.isopend()) {
                        UIVar.isdialogeopend = false;

                        this.cancel();
                    }

                    if (!UIVar.isdialogeopend && NewProjectWindow.isopend()) {
                        UIVar.isdialogeopend = true;
                    }
                }
            }, 0, 100);

        }


        public boolean testopen() {
            return super.getParent().isVisible();
        }
    }


}
