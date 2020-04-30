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
import de.ft.interitus.UI.UI;
import de.ft.interitus.plugin.PluginManagerHandler;

import java.util.ArrayList;

public class NewProjectWindow {
    public static SetupBuilder setupBuilder;
    public static VisTable content;

    public static VisTextButton Button_next = new VisTextButton("Erstellen");
    //public static VisTextButton Button_previouse = new VisTextButton("Previouse");
    public static VisTextButton Button_cancle = new VisTextButton("Cancel");
    public static VisLabel errorLabel = new VisLabel("Das Wichtigste hier DAS ERROR LABLE");

    public static VisTextField nameinput = new VisTextField();
    public static VisTextField pfadinput = new VisTextField();
    public static CharSequence text = "Name: ";
    public static VisLabel namelable = new VisLabel(text);
    public static CharSequence pfadtext = "Pfad: ";
    public static VisLabel pfadlable = new VisLabel(pfadtext);
    public static CharSequence auftragtext = "Bitte gebe hier einen Name für das neue Projekt ein.";
    public static VisLabel auftrag = new VisLabel(auftragtext);
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

            ArrayList<String> items = new ArrayList<>();
            for (int i = 0; i < PluginManagerHandler.projekttypes.size(); i++) {
                if (PluginManagerHandler.projekttypes.get(i).getPO() != null) {
                    items.add(PluginManagerHandler.projekttypes.get(i).getName());
                }
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
            //buttonTable.add(Button_previouse).fillX().width(80).pad(350,0,0,0);
            buttonTable.add(Button_next).fillX().width(70).pad(350, 0, 0, 0);

            builder.append(buttonTable);

            pfadinput.setText(Settings.defaultpfad);

            content.add(auftrag).row();
            content.add(namelable).expandX().padLeft(-400).padBottom(-50);
            content.add(nameinput).expandX().width(400).padLeft(-500).padBottom(-50).row();

            content.add(pfadlable).expandX().padLeft(-400).padBottom(-150);
            content.add(pfadinput).expandX().width(400).padLeft(-500).padBottom(-150).row();
            content.add(selectProjectType).expandX().padBottom(-250).row();


            Table table = builder.build();

            add(table).expand().fill().size(600, 450);


            centerWindow();
            pack();


            Button_cancle.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                    setupBuilder.close();
                }
            });

        }


        public boolean testopen() {
            return super.getParent().isVisible();
        }
    }


}
