package de.ft.interitus.UI.neuesprojekt;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.building.GridTableBuilder;
import com.kotcrab.vis.ui.building.StandardTableBuilder;
import com.kotcrab.vis.ui.building.TableBuilder;
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.building.utilities.Padding;
import com.kotcrab.vis.ui.widget.*;
import de.ft.interitus.Block.Devices;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.setup.steps.ArduinoSteps.Step3;
import de.ft.interitus.UI.setup.steps.generalSteps.Step1;
import de.ft.interitus.UI.setup.steps.generalSteps.Step2;
import de.ft.interitus.data.VerbindungsSpeicher;

public class NeuesProjektWindow {
    public static SetupBuilder setupBuilder;
    public static VisTable content;

    public static VisTextButton Button_next = new VisTextButton("Erstellen");
    //public static VisTextButton Button_previouse = new VisTextButton("Previouse");
    public static VisTextButton Button_cancle = new VisTextButton("Cancel");
    public static VisLabel errorLabel = new VisLabel("Das Wichtigste hier DAS ERROR LABLE");

    public static VisTextField nameinput = new VisTextField();
    public static CharSequence text = "Name: ";
    public static VisLabel namelable = new VisLabel(text);
    public static CharSequence auftragtext = "Bitte gebe hier einen Name für das neue Projekt ein.";
    public static VisLabel auftrag = new VisLabel(auftragtext);

    final Padding padding = new Padding(2, 3);

    public NeuesProjektWindow() {

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
        }catch (Exception e) {
            return false;
        }
    }


    public void show() {
        if (setupBuilder == null) {
            content = new VisTable();
            setupBuilder = new SetupBuilder("Neues Projekt Setup", new StandardTableBuilder(padding));
            setupBuilder.pack();
        }else{
            content.clearChildren();
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

            errorLabel.setColor(1,0,0,1);
            VisTable buttonTable = new VisTable(true);
            buttonTable.add(errorLabel).fillX().width(60).pad(350,0,0,350);
            buttonTable.add(Button_cancle).fillX().width(60).pad(350,0,0,0);
            //buttonTable.add(Button_previouse).fillX().width(80).pad(350,0,0,0);
            buttonTable.add(Button_next).fillX().width(70).pad(350,0,0,0);

            content.add(auftrag).row();
            content.add(namelable).expandX().padLeft(-310);
            content.add(nameinput).expandX().padLeft(-350).width(400).row();


            builder.append(buttonTable);



            Table table = builder.build();

            add(table).expand().fill().size(600,450);


            centerWindow();
            pack();





        }


        public boolean testopen() {
            return super.getParent().isVisible();
        }
    }


}
