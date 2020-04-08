package de.ft.robocontrol.UI.setup.steps.RaspberryPiSteps;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.InputValidator;
import com.kotcrab.vis.ui.widget.*;

import com.sun.tools.javac.util.StringUtils;
import de.ft.robocontrol.roboconnection.arduino.BurnProgramm;
import de.ft.robocontrol.roboconnection.arduino.SerialConnection;
import de.ft.robocontrol.utils.NetworkScan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Step3 {
    public static Timer time;
    public static VisValidatableTextField ipaddresse;
    public static void step3(VisTable builder){



        time = new Timer( 30, new ActionListener()
        {
            public void actionPerformed( ActionEvent evt )
            {





            }
        });
        time.start();

        builder.add(new VisLabel("IP-Addresse:")).padLeft(150);
        ipaddresse = new VisValidatableTextField(NetworkScan.piaddress);
        ipaddresse.addValidator(new InputValidator() {
            @Override
            public boolean validateInput(String input) {
               if( input.length() - input.replace(".","").length()==3) {

                   try {
                       if (!input.split("\\.")[3].isEmpty()) {
                           if(!input.split("\\.")[2].isEmpty()) {
                               if(!input.split("\\.")[1].isEmpty()) {
                                   if(!input.split("\\.")[0].isEmpty()) {
                                        return true;
                                   }else {
                                       return false;
                                   }
                               }else {
                                   return false;
                               }
                           }else {
                               return false;
                           }
                       } else {

                           return false;
                       }
                   }catch (ArrayIndexOutOfBoundsException e){
                       return false;
                   }
               }else{
                   return false;
               }
            }
        });
        builder.add(ipaddresse).expandX().padLeft(-150);



    }
    public static void close(){
        if(time!=null){
            if (time.isRunning()) {
                time.stop();
            }
        }
    }

}
