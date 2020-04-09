package de.ft.robocontrol.roboconnection.arduino;
import com.fazecast.jSerialComm.*;
import de.ft.robocontrol.UI.setup.steps.ArduinoSteps.Step3;

import java.io.IOException;
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
            System.out.println("nubmer    "+number);
            return number;
        }else{
            return -1;
        }


    }


    public static void searchArduino(){
        SerialConnection.Authentifikation.searchArduino();
    }




























    public static class Authentifikation {
        private static String output;


        public static String getOutput(){
            return output;
        }


        private static void checkAut(SerialPort checkport) throws IOException {

            checkport.setBaudRate(230400);
            checkport.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 10, 0);


            save = System.currentTimeMillis() + 5000;
            found = false;
            while (System.currentTimeMillis() < save && found == false) {
                if(checkport.getInputStream()!=null) {
                    if (empfangen(checkport) == 1234) {
                        found = true;
                    }
                }
            }

            if (found == false) {
                output="Nicht gefunden";
                checkport.closePort();
            }
            if (found == true) {
                output="Arduino gefunden";

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
                    isRunning=true;
                    try {
                        SerialPort ports[] = SerialPort.getCommPorts();

                        i = 1;

                        for (SerialPort port : ports) {
                            i++;

                            System.out.println("i     " + i);
                            try {
                                SerialPort checkport = ports[i-2];

                                if (checkport.openPort()) {
                                    System.out.println("Successfully opened the port."+checkport.getSystemPortName());
                                    System.out.println("desprictiveportname:   " + checkport.getDescriptivePortName());
                                    output="versuche "+checkport.getSystemPortName()+" zu autentifizieren";
                                    checkAut(checkport);
                                } else {
                                    System.out.println("Unable to open the port.");
                                    output="Konnte Port nicht öffnen";
                                }



                            } catch (ArrayIndexOutOfBoundsException e) {

                                e.printStackTrace();
                            }


                        }

                        output="Es wurden "+Arduinos.size()+" Arduinos autentiviziert.";
                        if(Step3.selectportlist!=null) {
                            Step3.selectportlist.setItems(SerialConnection.getPortNames());
                        }
                    }catch (Exception e) {
                        e.printStackTrace(); //for debug to find errors
                        output="es ist ein Fehler aufgetreten Bitte versuche es erneut\nwenn der Fehler weiterhin auftritt wende dich an den Support";
                    }

                    isRunning=false;
                }
            };
            if(!isRunning) {
                search.start();
            }
        }
    }













}
