package de.ft.robocontrol.roboconnection;
import com.fazecast.jSerialComm.*;
import de.ft.robocontrol.UI.setup.steps.ArduinoSteps.Step3;

import java.util.ArrayList;
import java.util.Scanner;


public class SerialConnection {
public static boolean isRunning=false;
    public static ArrayList<SerialPort> Arduinos = new ArrayList<SerialPort>();
   static boolean found;
   static int i = 1;
   static long save;

    public static SerialPort[] getPorts(){
        SerialPort ports[]=SerialPort.getCommPorts();


        return ports;
    }


    public static String[] getPortNames(){

        String[] items = new String[SerialConnection.getPorts().length];

        for(int i = 0; i<SerialConnection.getPorts().length;i++) {

            String ssv="";
            String arduinoerkannt="";

            for(int a=0;a<SerialConnection.Arduinos.size();a++){
                if(SerialConnection.Arduinos.get(a).getDescriptivePortName().equals(SerialConnection.getPorts()[i].getDescriptivePortName())){
                    System.out.println("software schon vorhanden");
                    ssv=" (Authentifiziert)";
                }
            }

            if(SerialConnection.getPorts()[i].getDescriptivePortName().contains("Arduino")  ||  SerialConnection.getPorts()[i].getDescriptivePortName().contains("arduino")){
                arduinoerkannt=" (Arduino)";
                if(SerialConnection.getPorts()[i].getDescriptivePortName().contains("Mega") || SerialConnection.getPorts()[i].getDescriptivePortName().contains("mega")){
                    arduinoerkannt=" (Arduino MEGA)";
                }else if(SerialConnection.getPorts()[i].getDescriptivePortName().contains("uno") || SerialConnection.getPorts()[i].getDescriptivePortName().contains("UNO") || SerialConnection.getPorts()[i].getDescriptivePortName().contains("Uno")){
                    arduinoerkannt=" (Arduino UNO)";
                }


            }

            items[i]=SerialConnection.getPorts()[i].getSystemPortName()+ssv+arduinoerkannt;


        }

        //ConnectionWindow.selectportlist.setItems(items);


        return items;
    }


    public static int empfangen(SerialPort sport){
        Scanner data = new Scanner(sport.getInputStream());
        //System.out.println(data.hasNextLine());
        if (data.hasNextLine()) {
            int number = 0;
            try {
                number = Integer.parseInt(data.nextLine());
            } catch (Exception e) {
            }
            //System.out.println("nubmer    "+number);
            return number;
        }else{
            return -1;
        }


    }


public static void searchArduino() {
    // determine which serial port to use

    Thread search = new Thread() {
        public void run() {
            isRunning=true;
            try {
//TODO hier System.out's rausschmeisen
                SerialPort ports[] = SerialPort.getCommPorts();

                i = 1;

                for (SerialPort port : ports) {
                    i++;

                    System.out.println("i     " + i);
                    try {
                        SerialPort testport = ports[i-2];

                        if (testport.openPort()) {
                            System.out.println("Successfully opened the port."+testport.getSystemPortName());
                            System.out.println("desprictiveportname:   " + testport.getDescriptivePortName());
                        } else {
                            System.out.println("Unable to open the port.");

                        }
                        System.out.println("1");

                        testport.setBaudRate(230400);
                        System.out.println("2");
                        testport.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 10, 0);
                        System.out.println("3");

                         save = System.currentTimeMillis() + 5000;
                      found = false;
                      System.out.println("checkstart");
                        while (System.currentTimeMillis() < save && found == false) {
                            if (empfangen(testport) == 1234) {
                                System.out.println("Arduino gefunden");
                                found = true;
                            }
                        }
                        System.out.println("checkend");
                System.out.println(found);
                        if (found == false) {
                            testport.closePort();
                            System.out.println("arduino nicht gefunden");
                        }
                        if (found == true) {

                            System.out.println("arduino gefunden");

                            if (Arduinos.size() == 0) {
                                Arduinos.add(testport);
                            } else {
                                for (int e = 0; e < Arduinos.size(); e++) {

                                    if (Arduinos.get(e).getSystemPortName().equals(testport.getSystemPortName())) {

                                    } else {
                                        Arduinos.add(testport);
                                    }


                                }
                            }


                        }


                        testport.closePort();

                    } catch (ArrayIndexOutOfBoundsException e) {

                        e.printStackTrace();
                    }


                }


            if(Step3.selectportlist!=null) {
                Step3.selectportlist.setItems(SerialConnection.getPortNames());
            }
            }catch (Exception e) {
                e.printStackTrace(); //for debug to find errors
            }

            isRunning=false;
        }
    };
if(!isRunning) {
    search.start();
}
}
}
