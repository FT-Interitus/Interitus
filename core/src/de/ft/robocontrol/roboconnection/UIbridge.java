package de.ft.robocontrol.roboconnection;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import de.ft.robocontrol.Block.Arduino;
import de.ft.robocontrol.UI.ConnectionWindow;

public class UIbridge {
    public static String selectedport;
    public static String selectedboard;
    public static void UpdateConnectionWindowPortsList() {


        Thread updater = new Thread() {
            @Override
            public void run() {
                //Update ports
                ConnectionWindow.selectportlist.setItems(SerialConnection.getPortNames());
                //Button Listener
                ConnectionWindow.devicemanagebutton.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {

                        /*
                        if(selectedboard.contains("MEGA"))  {
                            BurnProgramm.burn(Arduino.MEGA,"/dev/ttyACM0" );
                        }

                        if(selectedboard.contains("UNO")) {
                            BurnProgramm.burn(Arduino.UNO,"/dev/ttyACM0");
                        }


                         */
                        BurnProgramm.burn(Arduino.MEGA,"/dev/ttyACM0" ); //TODO change ports


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
