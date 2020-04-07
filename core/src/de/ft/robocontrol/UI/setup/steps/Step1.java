package de.ft.robocontrol.UI.setup.steps;

import com.badlogic.gdx.graphics.Color;
import com.kotcrab.vis.ui.building.TableBuilder;
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.building.utilities.Padding;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextField;
import de.ft.robocontrol.UI.setup.SetupWindow;

import java.sql.Time;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class Step1 {
    public static VisTextField name = new VisTextField();
    public static CharSequence text = "Name: ";
    public static VisLabel namelable = new VisLabel(text);
    public static CharSequence auftragtext = "Bitte gebe hier einen Name für die neue Verbindung ein.";
    public static VisLabel auftrag = new VisLabel(auftragtext);
    public static Timer time = new Timer();

    public Step1() {

    }
    public static void save(){
            SetupWindow.tempverbindungsspeicher.name = name.getText();

    }

    public static void close(){
        name.setText("");
    }

    public static void loadSettings(){
        name.setText(SetupWindow.tempverbindungsspeicher.name);
    }


    public static void step1(VisTable builder) {
        update();

        builder.add(auftrag).expandX().padBottom(-100).row();
        builder.add(namelable).expandX().padBottom(-200).padLeft(-100);
        builder.add(name).expandX().padBottom(-200).padLeft(-400).row();


    }


    public static void update() {



    time.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {


            if(name.isEmpty()){
                SetupWindow.errorLabel.setColor(new Color(1,0,0,1));
                SetupWindow.errorLabel.setText("Du musst einen Name vergeben");
                SetupWindow.Button_next.setDisabled(true);
            }else{
                SetupWindow.errorLabel.setColor(new Color(0,1,0,1));
                SetupWindow.errorLabel.setText("Alle Voraussetzungen erfüllt");
                SetupWindow.Button_next.setDisabled(false);
            }
        }
    },0,30);

}
}
