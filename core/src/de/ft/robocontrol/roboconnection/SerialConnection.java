package de.ft.robocontrol.roboconnection;
import com.fazecast.jSerialComm.*;
import de.ft.robocontrol.MainGame;
import de.ft.robocontrol.UI.ConnectionWindow;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class SerialConnection {

    public static ArrayList<SerialPort> Arduinos = new ArrayList<SerialPort>();



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
                    ssv=" (Authenifiziert)";
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
           // System.out.println("nubmer    "+number);
            return number;
        }else{
            return -1;
        }


    }


public static void searchArduino() {
    // determine which serial port to use

    Thread search = new Thread() {
        public void run() {

            SerialPort ports[] = SerialPort.getCommPorts();
            System.out.println("Select a port:");
            int i = 1;
            ConnectionWindow.devicemanagebutton.setDisabled(true);
            ConnectionWindow.devicemanagebutton.setText("Bitte Warten..");
            for (SerialPort port : ports) {
                System.out.println(i++ + ". " + port.getSystemPortName());
                System.out.println(port.getDescriptivePortName() + "   deks");
                System.out.println("i     " + i);
                try {
                    SerialPort testport = ports[i-2]; //TODO Hier noch ein Fehler

                    if (testport.openPort()) {
                        System.out.println("Successfully opened the port.");
                        System.out.println("baudrate:   " + testport.getDescriptivePortName());
                    } else {
                       System.out.println("Unable to open the port.");
                        return;
                    }

                    testport.setBaudRate(230400);
                    testport.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 10, 0);

                    long save = System.currentTimeMillis() + 5000;
                    boolean found = false;
                    while (System.currentTimeMillis() < save && found == false) {
                        if (empfangen(testport) == 1234) {
                            System.out.println("Arduino gefunden");
                            found = true;
                        }
                    }

                    if (found == false) {
                        testport.closePort();
                    }
                    if (found == true) {


                        if(Arduinos.size()==0){
                            Arduinos.add(testport);
                        }else {
                            for (int e = 0; e < Arduinos.size(); e++) {

                                    if(Arduinos.get(e).getSystemPortName().equals(testport.getSystemPortName())){

                                    }else{
                                        Arduinos.add(testport);
                                    }


                            }
                        }

                    }


                    testport.closePort();

                } catch (ArrayIndexOutOfBoundsException e) {

                }


            }

            ConnectionWindow.devicemanagebutton.setDisabled(false);
            ConnectionWindow.devicemanagebutton.setText("Software brennen");

            ConnectionWindow.selectportlist.setItems(SerialConnection.getPortNames());
        }
    };

  search.start();
}
}
