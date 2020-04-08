package de.ft.robocontrol.UI.setup.steps.ArduinoSteps;

import com.badlogic.gdx.graphics.Color;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.robocontrol.UI.setup.SetupWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Step3 {
    public static CharSequence auftragtext = "Bitte gebe hier einen Name für die neue Verbindung ein.";
    public static VisLabel auftrag = new VisLabel(auftragtext);
    public static Timer time;
    public static void step3(VisTable builder){
        time = new Timer( 30, new ActionListener()
        {
            public void actionPerformed( ActionEvent evt )
            {

            }
        });
        time.start();


        builder.add(auftrag).expandX().row();



    }
    public static void close(){
        if(time!=null){
            if (time.isRunning()) {
                time.stop();
            }
        }
    }
}
