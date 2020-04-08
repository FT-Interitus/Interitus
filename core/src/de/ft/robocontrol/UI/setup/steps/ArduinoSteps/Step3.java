package de.ft.robocontrol.UI.setup.steps.ArduinoSteps;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSelectBox;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import de.ft.robocontrol.Block.Devices;
import de.ft.robocontrol.UI.setup.SetupWindow;
import de.ft.robocontrol.roboconnection.arduino.BurnProgramm;
import de.ft.robocontrol.roboconnection.arduino.SerialConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Step3 {
    public static VisSelectBox<String> selectportlist;
    public static VisTextButton neuladen_button = new VisTextButton("Neuladen");
    public static VisTextButton brennen_button=new VisTextButton("Firmware aufspielen");
    public static VisLabel brenntext = new VisLabel("error");

    public static CharSequence auftragtext = "Bitte Stelle eine Verbindung zum Arduino her";
    public static VisLabel auftrag = new VisLabel(auftragtext);
    public static Timer time;
    public static boolean a;
    public static boolean isBurning=false;



    public static void brennen(){
        final String[] getrennt = selectportlist.getSelected().split(" ");
        System.out.println(getrennt[0]);
        Thread burningThread = new Thread() {
            @Override
            public void run() {
                isBurning=true;
                try {

                    if (SetupWindow.tempverbindungsspeicher.device == Devices.ARDUINO_MEGA) {
                        BurnProgramm.burn(Devices.ARDUINO_MEGA, getrennt[0], "sketch_mega.hex");
                    }

                    if (SetupWindow.tempverbindungsspeicher.device == Devices.ARDUINO_UNO) {
                        BurnProgramm.burn(Devices.ARDUINO_UNO, getrennt[0], "sketch_uno.hex");
                    }

                }catch(Exception e){

                }
                isBurning=false;
            }
        };
        if(!burningThread.isAlive()) {
            burningThread.start();;
        }else{

        }
    }

    public static void step3(VisTable builder){
        time = new Timer( 30, new ActionListener()
        {
            public void actionPerformed( ActionEvent evt )
            {


                    brenntext.setText(BurnProgramm.ausgabe);

                    if (SerialConnection.isRunning || isBurning==true) {
                        neuladen_button.setDisabled(true);
                        brennen_button.setDisabled(true);
                    } else {
                        neuladen_button.setDisabled(false);
                        brennen_button.setDisabled(false);
                    }

            }
        });
        time.start();

        selectportlist = new VisSelectBox<String>();

        builder.add(auftrag).expandX().row();
        builder.add(selectportlist);
        builder.add(neuladen_button).row();
        builder.add(brennen_button).padBottom(-50).row();
        builder.add(brenntext).padBottom(-200);
        selectportlist.setItems(SerialConnection.getPortNames());

        neuladen_button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SerialConnection.searchArduino();
            }
        });

        brennen_button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                     brennen();
            }
        });

    }
    public static void close(){
        if(time!=null){
            if (time.isRunning()) {
                time.stop();
            }
        }
    }
}
