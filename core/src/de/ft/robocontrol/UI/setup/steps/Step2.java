package de.ft.robocontrol.UI.setup.steps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.building.TableBuilder;
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.building.utilities.Padding;
import com.kotcrab.vis.ui.widget.*;
import de.ft.robocontrol.Block.Devices;
import de.ft.robocontrol.UI.setup.SetupWindow;
import de.ft.robocontrol.loading.AssetLoader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Step2 {
    public static VisImage helpimage=new VisImage(AssetLoader.help_platforms);
    public static VisSelectBox<String> selectPlatform;
    public static VisSelectBox<String> selectBoardArt;
    public static CharSequence auftragtext = "Hier wählst du bitte deine Plattform aus.";
    public static VisLabel auftrag = new VisLabel(auftragtext);
    public static Timer time;

    public static int shownext=0; ///////////////////0 Bitte Auswählen;1 Arduino;2 EV3;3 Raspberry Pi/////////////////

    public Step2() {

    }

    public static void step2(final VisTable builder) {


        time = new javax.swing.Timer( 30, new ActionListener()
        {
            public void actionPerformed( ActionEvent evt )
            {

            }
        });
        time.start();




        builder.add(auftrag).expandX().row();
        selectPlatform = new VisSelectBox<String>();
        selectBoardArt = new VisSelectBox<String>();
        selectPlatform.setItems("Bitte auswählen","Arduino","EV3","Raspberry Pi");
        builder.add(selectPlatform).row();
        builder.row();
        builder.add(selectBoardArt).padBottom(-50).row();
        builder.add(helpimage).padBottom(-400).height(300*0.8f).width(500*0.8f);
        selectBoardArt.setVisible(false);

        SetupWindow.Button_next.setDisabled(true);
        SetupWindow.errorLabel.setColor(1,0,0,1);
        SetupWindow.errorLabel.setText("Bitte wähle eine Platform aus");
        selectPlatform.setColor(1,0,0,1);
        selectBoardArt.setColor(1,0,0,1);


        selectPlatform.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(selectPlatform.getSelected().equals("Bitte auswählen")){
                    helpimage.setDrawable(AssetLoader.help_platforms);
                    SetupWindow.Button_next.setDisabled(true);
                    SetupWindow.errorLabel.setColor(1,0,0,1);
                    SetupWindow.errorLabel.setText("Bitte wähle eine Platform aus");
                    selectBoardArt.setVisible(false);
                    selectPlatform.setColor(1,0,0,1);
                    selectBoardArt.setSelected("Bitte auswählen");
                    shownext=0;
                    SetupWindow.tempverbindungsspeicher.device= Devices.UNDEFINIERT;


                }else{
                    selectPlatform.setColor(0,1,0,1);
                    if(selectPlatform.getSelected().equals("Arduino")){
                        helpimage.setDrawable(AssetLoader.help_arduino_boards);
                        selectBoardArt.setItems("Bitte auswählen","Arduino UNO","Arduino MEGA");
                        selectBoardArt.setVisible(true);
                        SetupWindow.errorLabel.setColor(1,0,0,1);
                        selectBoardArt.setSelected("Bitte auswählen");

                        SetupWindow.errorLabel.setColor(1,0,0,1);
                        SetupWindow.errorLabel.setText("Bitte wähle ein Arduino Board aus");
                        shownext=1;



                    }
                    if(selectPlatform.getSelected().equals("EV3")){
                        helpimage.setDrawable(AssetLoader.help_ev3);
                        selectBoardArt.setItems("Bitte auswählen","Normal","EV3-DEV");
                        selectBoardArt.setVisible(true);
                        SetupWindow.errorLabel.setColor(1,0,0,1);
                        selectBoardArt.setSelected("Bitte auswählen");
                        SetupWindow.errorLabel.setColor(1,0,0,1);
                        SetupWindow.errorLabel.setText("Bitte wähle ein EV3 aus");
                        shownext=2;



                    }
                    if(selectPlatform.getSelected().equals("Raspberry Pi")){
                        helpimage.setDrawable(AssetLoader.help_raspberrypi_boards);
                        selectBoardArt.setItems("Bitte auswählen","Raspberry Pi 4b","Raspberry pi 3b/3b+","Raspberry Pi Zero W");
                        selectBoardArt.setVisible(true);
                        SetupWindow.errorLabel.setColor(1,0,0,1);
                        selectBoardArt.setSelected("Bitte auswählen");
                        SetupWindow.errorLabel.setColor(1,0,0,1);
                        SetupWindow.errorLabel.setText("Bitte wähle ein Raspberry Pi aus");
                        shownext=3;


                    }

                }

            }
        });


        selectBoardArt.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(selectBoardArt.getSelected().equals("Bitte auswählen")){
                    selectBoardArt.setColor(1,0,0,1);
                }
                switch (shownext){
                    case 0:

                        break;
                    case 1:
                        if(selectBoardArt.getSelected().equals("Arduino UNO")){
                            selectBoardArt.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setText("Alle Voraussetzungen erfüllt");
                            SetupWindow.Button_next.setDisabled(false);
                            SetupWindow.tempverbindungsspeicher.device= Devices.ARDUINO_UNO;
                        }else
                        if(selectBoardArt.getSelected().equals("Arduino MEGA")){
                            selectBoardArt.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setText("Alle Voraussetzungen erfüllt");
                            SetupWindow.Button_next.setDisabled(false);
                            SetupWindow.tempverbindungsspeicher.device= Devices.ARDUINO_MEGA;
                        }else{
                            SetupWindow.errorLabel.setColor(1,0,0,1);
                            SetupWindow.errorLabel.setText("Bitte wähle ein Arduino Board aus");
                            SetupWindow.Button_next.setDisabled(true);
                            SetupWindow.tempverbindungsspeicher.device= Devices.UNDEFINIERT;
                        }
                        break;

                    case 2:

                        if(selectBoardArt.getSelected().equals("Normal")){
                            selectBoardArt.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setText("Alle Voraussetzungen erfüllt");
                            SetupWindow.Button_next.setDisabled(false);
                            SetupWindow.tempverbindungsspeicher.device= Devices.EV3;
                        }else
                        if(selectBoardArt.getSelected().equals("EV3-DEV")){
                            selectBoardArt.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setText("Alle Voraussetzungen erfüllt");
                            SetupWindow.Button_next.setDisabled(false);
                            SetupWindow.tempverbindungsspeicher.device= Devices.EV3_DEV;
                        }else{
                            SetupWindow.errorLabel.setColor(1,0,0,1);
                            SetupWindow.errorLabel.setText("Bitte wähle ein EV3 aus");
                            SetupWindow.Button_next.setDisabled(true);
                        }

                        break;
                    case 3:
                        if(selectBoardArt.getSelected().equals("Raspberry Pi 4b")){
                            selectBoardArt.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setText("Alle Voraussetzungen erfüllt");
                            SetupWindow.Button_next.setDisabled(false);
                            SetupWindow.tempverbindungsspeicher.device= Devices.RASPBERRYPI4;
                        }else
                        if(selectBoardArt.getSelected().equals("Raspberry pi 3b/3b+")){
                            selectBoardArt.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setText("Alle Voraussetzungen erfüllt");
                            SetupWindow.Button_next.setDisabled(false);
                            SetupWindow.tempverbindungsspeicher.device= Devices.RASPBERRY3Bplus;
                        }else
                        if(selectBoardArt.getSelected().equals("Raspberry Pi Zero W")){
                            selectBoardArt.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setText("Alle Voraussetzungen erfüllt");
                            SetupWindow.Button_next.setDisabled(false);
                            SetupWindow.tempverbindungsspeicher.device= Devices.RASPBERRYPIZEROW;
                        }else{
                            SetupWindow.errorLabel.setColor(1,0,0,1);
                            SetupWindow.errorLabel.setText("Bitte wähle einen Raspberry Pi aus");
                            SetupWindow.Button_next.setDisabled(true);
                            SetupWindow.tempverbindungsspeicher.device= Devices.UNDEFINIERT;
                        }
                        break;
                }
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
