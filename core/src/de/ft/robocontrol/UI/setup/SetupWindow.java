package de.ft.robocontrol.UI.setup;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.building.GridTableBuilder;
import com.kotcrab.vis.ui.building.StandardTableBuilder;
import com.kotcrab.vis.ui.building.TableBuilder;
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.building.utilities.Padding;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;
import de.ft.robocontrol.UI.UI;
import de.ft.robocontrol.UI.setup.steps.Step1;

public class SetupWindow {
    public static SetupBuilder setupBuilder;
    private static VisTextButton Button_next = new VisTextButton("Next");
    private static VisTextButton Button_previouse = new VisTextButton("Previouse");
    private static VisTextButton Button_cancle = new VisTextButton("Cancel");
    final Padding padding = new Padding(2, 3);

    public SetupWindow() {

        new GridTableBuilder(4);
    }


    public static void update() {
        try {
            setupBuilder.pack();
        } catch (NullPointerException e) { //kann passieren wenn das Fenster noch nicht initialisiert wurde
        }

    }


    public void show() {
        if (setupBuilder == null) {
            setupBuilder = new SetupBuilder("Verbindungs Setup", new StandardTableBuilder(padding));
            setupBuilder.pack();
        }
        UI.stage.addActor(setupBuilder);
    }


    private class SetupBuilder extends VisWindow {
        public SetupBuilder(String name, TableBuilder builder) {
            super(name);


            setModal(true);
            closeOnEscape();
            addCloseButton();
            setLayoutEnabled(true);
            builder.setTablePadding(new Padding(20, 30, 20, 30));

            Step1.step1(builder);
            builder.row();


            VisTable buttonTable = new VisTable(true);
            buttonTable.add(Button_cancle).fillX().width(60).pad(350,600-60-80-50-50,0,0);
            buttonTable.add(Button_previouse).fillX().width(80).pad(350,0,0,0);
            buttonTable.add(Button_next).fillX().width(50).pad(350,0,0,0);

            builder.append(buttonTable);



            Table table = builder.build();

            add(table).expand().fill().size(600,450);


            centerWindow();
            pack();
        }


    }


}
