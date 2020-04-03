package de.ft.robocontrol.roboconnection;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import de.ft.robocontrol.Block.Arduino;
import de.ft.robocontrol.UI.ConnectionWindow;
import de.ft.robocontrol.data.VerbindungsSpeicher;


import java.util.Timer;
import java.util.TimerTask;

public class UIbridge {
    public static String selectedport;
    public static String selectedboard = "Arduino UNO";


    public static boolean setup = false;

    public static void UpdateConnectionWindowPortsList() {

        final int[] portsold = {0};

        Thread updater = new Thread() {
            @Override
            public void run() {

                //Update ports
                Timer time = new Timer();

                time.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {


                        try {
                            if (!ConnectionWindow.devicemanagebutton.getText().toString().contains("Warten")) {
                                if (ConnectionWindow.selectportlist.getSelected().contains("(Authentifiziert)")) {

                                    if(true) {

                                        ConnectionWindow.devicemanagebutton.setText("Konfigurieren"); //TODO heir prüfund ob der Arduino schon eingerichtet wurde
                                        setup = true;
                                    }else{
                                        ConnectionWindow.devicemanagebutton.setText("Software brennen");
                                    }
                                } else {

                                    ConnectionWindow.devicemanagebutton.setText("Software brennen");
                                    setup = false;

                                }
                            }
                        }catch (Exception e) {
                            e.printStackTrace();
                        }



                        if (SerialConnection.getPorts().length > portsold[0]) {
                            portsold[0] = SerialConnection.getPorts().length;
                            SerialConnection.searchArduino();
                            ConnectionWindow.selectportlist.setItems(SerialConnection.getPortNames()); //TODO hier Nullpointer bei zu schnellem ein und ausstecken des Arduinos
                            ConnectionWindow.update();
                        } else if (SerialConnection.getPorts().length < portsold[0]) {

                            ConnectionWindow.selectportlist.setItems(SerialConnection.getPortNames());
                            ConnectionWindow.update();
                            portsold[0] = SerialConnection.getPorts().length;

                            for (int i = 0; i < SerialConnection.Arduinos.size(); i++) {
                                boolean found = false;
                                for (int b = 0; b < SerialConnection.getPorts().length; b++) {
                                    if (SerialConnection.Arduinos.get(i).getSystemPortName() == SerialConnection.getPorts()[b].getSystemPortName()) {
                                        found = true;
                                    }
                                }
                                if (found == false) {
                                    SerialConnection.Arduinos.remove(i);
                                }
                            }

                        }

                    }
                }, 0, 200);


                //Button Listener
                ConnectionWindow.devicemanagebutton.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {


                      if(!setup) {
                          ConnectionWindow.error.setText("Wird gebrannt...");


                          String[] getrennt = ConnectionWindow.selectportlist.getSelected().split(" ");

                          if (selectedboard.contains("MEGA")) {
                              BurnProgramm.burn(Arduino.MEGA, getrennt[0], "sketch_mega.hex");
                          }

                          if (selectedboard.contains("UNO")) {
                              BurnProgramm.burn(Arduino.UNO, getrennt[0], "sketch_uno.hex");
                          }
                      }else{
                          VerbindungsSpeicher.verbundungen.add(new VerbindungsSpeicher("Neue Verbindung"));
                          KnownDeviceManager.addnewdevice();
                      }


                    }
                });


                ConnectionWindow.neuladen_button.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {


                        ConnectionWindow.error.setText("Neuladen...");


                        SerialConnection.searchArduino();


                    }
                });


            }
        };

        ConnectionWindow.selectboardlist.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {


                selectedboard = ConnectionWindow.selectboardlist.getSelected();

            }
        });

        ConnectionWindow.selectportlist.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectedport = ConnectionWindow.selectportlist.getSelected();
            }
        });


        updater.start();


    }
}
