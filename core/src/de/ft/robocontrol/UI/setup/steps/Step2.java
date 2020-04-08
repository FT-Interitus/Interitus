package de.ft.robocontrol.UI.setup.steps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.building.TableBuilder;
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.building.utilities.Padding;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSelectBox;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextField;
import de.ft.robocontrol.UI.setup.SetupWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Step2 {

    public static VisSelectBox<String> selectPlatform;
    public static VisSelectBox<String> selectBoardArt;
    public static CharSequence auftragtext = "Hier wählst du bitte deine Plattform aus.";
    public static VisLabel auftrag = new VisLabel(auftragtext);
    public static Timer time;

    public static int shownext=0; ///////////////////0 Bitte Auswählen;1 Arduino;2 EV3;3 Raspberry Pi/////////////////

    public Step2() {

    }

    public static void step2(final VisTable builder) {
        update();
        builder.add(auftrag).expandX().row();
        selectPlatform = new VisSelectBox<String>();
        selectBoardArt = new VisSelectBox<String>();
        selectPlatform.setItems("Bitte auswählen","Arduino","EV3","Raspberry Pi");
        builder.add(selectPlatform).row();
        builder.row();
        builder.add(selectBoardArt).padBottom(-50);
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
                    SetupWindow.Button_next.setDisabled(true);
                    SetupWindow.errorLabel.setColor(1,0,0,1);
                    SetupWindow.errorLabel.setText("Bitte wähle eine Platform aus");
                    selectBoardArt.setVisible(false);
                    selectPlatform.setColor(1,0,0,1);
                    selectBoardArt.setSelected("Bitte auswählen");
                    shownext=0;


                }else{
                    selectPlatform.setColor(0,1,0,1);
                    if(selectPlatform.getSelected().equals("Arduino")){
                        selectBoardArt.setItems("Bitte auswählen","Arduino UNO","Arduino MEGA");
                        selectBoardArt.setVisible(true);
                        SetupWindow.errorLabel.setColor(1,0,0,1);
                        selectBoardArt.setSelected("Bitte auswählen");

                        SetupWindow.errorLabel.setColor(1,0,0,1);
                        SetupWindow.errorLabel.setText("Bitte wähle ein Arduino Board aus");
                        shownext=1;



                    }
                    if(selectPlatform.getSelected().equals("EV3")){

                        selectBoardArt.setItems("Bitte auswählen","Normal","EV3-DEV");
                        selectBoardArt.setVisible(true);
                        SetupWindow.errorLabel.setColor(1,0,0,1);
                        selectBoardArt.setSelected("Bitte auswählen");
                        SetupWindow.errorLabel.setColor(1,0,0,1);
                        SetupWindow.errorLabel.setText("Bitte wähle ein EV3 aus");
                        shownext=2;



                    }
                    if(selectPlatform.getSelected().equals("Raspberry Pi")){
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
                        }else
                        if(selectBoardArt.getSelected().equals("Arduino MEGA")){
                            selectBoardArt.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setText("Alle Voraussetzungen erfüllt");
                            SetupWindow.Button_next.setDisabled(false);
                        }else{
                            SetupWindow.errorLabel.setColor(1,0,0,1);
                            SetupWindow.errorLabel.setText("Bitte wähle ein Arduino Board aus");
                            SetupWindow.Button_next.setDisabled(true);
                        }
                        break;

                    case 2:

                        if(selectBoardArt.getSelected().equals("Normal")){
                            selectBoardArt.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setText("Alle Voraussetzungen erfüllt");
                            SetupWindow.Button_next.setDisabled(false);
                        }else
                        if(selectBoardArt.getSelected().equals("EV3-DEV")){
                            selectBoardArt.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setText("Alle Voraussetzungen erfüllt");
                            SetupWindow.Button_next.setDisabled(false);
                        }

                        break;
                    case 3:
                        if(selectBoardArt.getSelected().equals("Raspberry Pi 4b")){
                            selectBoardArt.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setText("Alle Voraussetzungen erfüllt");
                            SetupWindow.Button_next.setDisabled(false);
                        }else
                        if(selectBoardArt.getSelected().equals("Raspberry pi 3b/3b+")){
                            selectBoardArt.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setText("Alle Voraussetzungen erfüllt");
                            SetupWindow.Button_next.setDisabled(false);
                        }else
                        if(selectBoardArt.getSelected().equals("Raspberry Pi Zero W")){
                            selectBoardArt.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setColor(0,1,0,1);
                            SetupWindow.errorLabel.setText("Alle Voraussetzungen erfüllt");
                            SetupWindow.Button_next.setDisabled(false);
                        }else{
                            SetupWindow.errorLabel.setColor(1,0,0,1);
                            SetupWindow.errorLabel.setText("Bitte wähle einen Raspberry Pi aus");
                            SetupWindow.Button_next.setDisabled(true);
                        }
                        break;
                }
            }
        });

    }

    public static void close(){

    }

    public static void loadSettings(){

    }


    public static void update() {



        time = new javax.swing.Timer( 30, new ActionListener()
        {
            public void actionPerformed( ActionEvent evt )
            {

            }
        });

    }
}
