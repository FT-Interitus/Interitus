package de.ft.interitus.roboconnection.arduino;

import de.ft.interitus.UI.setup.steps.ArduinoSteps.Step3;


import java.util.Timer;
import java.util.TimerTask;

public class PortUpdate {


   public static Thread thread;
    private static boolean found;

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




                            if (SerialConnection.getPorts().length > portsold[0]) {
                                portsold[0] = SerialConnection.getPorts().length;
                                SerialConnection.searchArduino();


                                if(Step3.selectportlist!=null) { //TODO @Felix ich hatte hier einen Null Pointer
                                    Step3.selectportlist.setItems(SerialConnection.getPortNames());
                                }
                            } else if (SerialConnection.getPorts().length < portsold[0]) {
                                if(Step3.selectportlist!=null) {
                                    Step3.selectportlist.setItems(SerialConnection.getPortNames());
                                }

                                portsold[0] = SerialConnection.getPorts().length;

                                for (int i = 0; i < SerialConnection.Arduinos.size(); i++) {
                                     found = false;
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
                        }catch (Exception e ){
                            e.printStackTrace();
                        }
                    }
                }, 0, 200);





            }
        };




        updater.start();


    }
}
