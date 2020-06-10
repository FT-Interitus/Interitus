package de.ft.interitus.deviceconnection.arduino;


import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import de.ft.interitus.UI.setup.steps.ArduinoSteps.Step3;
import jssc.SerialPortList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class SerialConnection {
    public static boolean isRunning = false;
    public static ArrayList<SerialPort> Arduinos = new ArrayList<SerialPort>();
    static boolean found;
    static int i = 1;
    static long save;
    static BufferedReader is;

    public static SerialPort[] getPorts() {
        SerialPort[] ports = SerialPort.getCommPorts();


        return ports;
    }


    public static String[] getPortNames() {

        String[] items = new String[SerialConnection.getPorts().length];

        for (int i = 0; i < SerialPortList.getPortNames().length; i++) {

            String ssv = "";
            String arduinoerkannt = "";

            for (int a = 0; a < SerialConnection.Arduinos.size(); a++) {
                if (SerialConnection.Arduinos.get(a).getDescriptivePortName().equals(SerialConnection.getPorts()[i].getDescriptivePortName())) {
                    ssv = " (keine ID)";
                }
            }

            if (SerialConnection.getPorts()[i].getDescriptivePortName().contains("Arduino") || SerialConnection.getPorts()[i].getDescriptivePortName().contains("arduino")) {
                arduinoerkannt = " (Arduino)";
                if (SerialConnection.getPorts()[i].getDescriptivePortName().contains("Mega") || SerialConnection.getPorts()[i].getDescriptivePortName().contains("mega")) {
                    arduinoerkannt = " (Arduino MEGA)";
                } else if (SerialConnection.getPorts()[i].getDescriptivePortName().contains("uno") || SerialConnection.getPorts()[i].getDescriptivePortName().contains("UNO") || SerialConnection.getPorts()[i].getDescriptivePortName().contains("Uno")) {
                    arduinoerkannt = " (Arduino UNO)";
                }


            }

            items[i] = SerialConnection.getPorts()[i].getSystemPortName() + ssv + arduinoerkannt;


        }

        //ConnectionWindow.selectportlist.setItems(items);


        return items;
    }


    public static String empfangenScanner(SerialPort sport) {
        Scanner data = new Scanner(sport.getInputStream());

        //System.out.println(data.hasNextLine());


        if (data.hasNextLine()) {
            String number = "";
            try {
                number = (data.nextLine());
            } catch (Exception e) {
            }

            System.out.println("nubmer    " + number);
            return number;
        } else {
            return "";
        }


    }

    public static String empfangen(SerialPort port) {
        String empfangen = "";

        BufferedReader b = new BufferedReader(new InputStreamReader(port.getInputStream()));
        try {


            //while (empfangen != "\n") {


            if (port.bytesAvailable() > 0) {
                empfangen =  Character.toString((char)b.read()); //fehler
            }


            //}


        } catch (Exception e) {
        }

        return empfangen;
    }


    static void empfangenListener(SerialPort port) {
        final BufferedReader b = new BufferedReader(new InputStreamReader(port.getInputStream()));

        port.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;
                try {

                    int input = b.read();
                    System.out.println("asdf  "+(char)input);

                   /////// Authentifikation.checkvalidaten(input);

                    //    Authentifikation.getPart()


                    //  System.out.println(input);
                } catch (IOException e) {
                }
            }
        });
    }


    public static void searchArduino() {
        SerialConnection.Authentifikation.searchArduino();
    }


    public static class Authentifikation {
        private static final int abtastzeit = 10000;
        private static final int arduinoneustartzeit = 0;
        private static String output;

        public static String getOutput() {
            return output;
        }

/*


        public static void checkvalidaten(String input) {

            try {
                String[] split = input.split("∑");


                for (int i = 0; i < split.length; i++) {
                    System.out.println(split[i]);

                }


            } catch (Exception e) {

                e.printStackTrace();

            }

        }


 */

        public static long getPart(String identify, String Input) {

            return 0;
        }

        private static void checkAut(final SerialPort checkport) throws IOException {

            checkport.setBaudRate(112500);
            //checkport.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 5, 5);


            //checkport.setBaudRate(9600);
            checkport.setNumDataBits(8);
            checkport.setNumStopBits(1);
            checkport.setParity(0);
            checkport.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 0, 0);


            save = System.currentTimeMillis() + abtastzeit;
            found = false;

            //is= new BufferedReader(new InputStreamReader(checkport.getInputStream()));

            empfangenListener(checkport);
            while (System.currentTimeMillis() < save) {

                //if (empfangen(checkport).contains("defaultID")) {  //fehler
                ///////////////////////////////////////////TODO hier muss dem arduino noch eine neue ID zugewiesen werden
                //    found = true;
                // }
                /////////////////////////////////////////////TODO hier muss noch überprüft werden ob der Arduino schon bekannt ist (schon eine richtige ID hat)


            }

            if (found == false) {
                output = "Nicht gefunden";
                checkport.closePort();
            }
            if (found == true) {
                output = "Arduino gefunden";

                if (Arduinos.size() == 0) {
                    Arduinos.add(checkport);
                } else {
                    for (int e = 0; e < Arduinos.size(); e++) {

                        if (Arduinos.get(e).getSystemPortName().equals(checkport.getSystemPortName())) {

                        } else {
                            Arduinos.add(checkport);
                        }


                    }
                }


            }


            checkport.closePort();
        }


        public static void searchArduino() {
            // determine which serial port to use

            Thread search = new Thread() {
                public void run() {
                    isRunning = true;
                    try {
                        SerialPort[] ports = SerialPort.getCommPorts();

                        i = 1;

                        for (SerialPort port : ports) {   //hier werden alle verfügbaren Ports durchtariert
                            i++;

                            System.out.println("i     " + i);
                            try {
                                SerialPort checkport = ports[i - 2];

                                if (checkport.openPort()) {        //versuche port zu öffnen
                                    System.out.println("Successfully opened the port." + checkport.getSystemPortName());     //port geöffnet
                                    System.out.println("desprictiveportname:   " + checkport.getDescriptivePortName());
                                    output = "versuche " + checkport.getSystemPortName() + " zu autentifizieren";

                                    Thread.sleep(arduinoneustartzeit);
                                    checkAut(checkport);                                                                    //port wird versucht zu autentivizieren


                                } else {
                                    System.out.println("Unable to open the port.");
                                    output = "Konnte Port nicht öffnen";                                  //port nicht geöffnet
                                }


                            } catch (ArrayIndexOutOfBoundsException e) {

                                //      e.printStackTrace();
                            }


                        }

                        output = "Es wurden " + Arduinos.size() + " Arduinos autentiviziert.";

                        if (Step3.selectportlist != null) {
                            Step3.selectportlist.setItems(SerialConnection.getPortNames());
                        }


                    } catch (Exception e) {
                        //    e.printStackTrace(); //for debug to find errors
                        output = "es ist ein Fehler aufgetreten Bitte versuche es erneut\nwenn der Fehler weiterhin auftritt wende dich an den Support";
                        System.out.println("es ist ein Fehler in SerialConnection aufgetreten | ^^^^ hier oben ist die exeption ^^^^ viel spaß damit ^^^^");
                    }

                    isRunning = false;
                }
            };
            if (!isRunning) {
                search.start();
            }
        }
    }


}
