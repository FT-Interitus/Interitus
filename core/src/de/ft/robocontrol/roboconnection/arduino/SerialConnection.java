package de.ft.robocontrol.roboconnection.arduino;
import com.fazecast.jSerialComm.*;
import de.ft.robocontrol.UI.setup.steps.ArduinoSteps.Step3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
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
                    ssv=" (keine ID)";
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


    public static String empfangenScanner(SerialPort sport){
        Scanner data = new Scanner(sport.getInputStream());

        //System.out.println(data.hasNextLine());


        if (data.hasNextLine()) {
            String number = "";
            try {
                number = (data.nextLine());
            } catch (Exception e) {
            }

            System.out.println("nubmer    "+number);
            return number;
        }else{
            return "";
        }


    }

    public static String empfangen(SerialPort port)  {
        String empfangen="";
        BufferedReader is = new BufferedReader(new InputStreamReader(port.getInputStream()));

        try {

//while (empfangen!="\n") {

        empfangen = is.readLine(); //fehler


//}





System.out.println();

        }catch (Exception e){}
if(empfangen!="") {



    System.out.print(empfangen);
}
        return empfangen;
    }



    public static void searchArduino(){
        SerialConnection.Authentifikation.searchArduino();
    }




























    public static class Authentifikation {
        private static String output;
        private static int abtastzeit=10000;


        public static String getOutput(){
            return output;
        }


        private static void checkAut(SerialPort checkport) throws IOException {

            checkport.setBaudRate(112500);
            //checkport.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 5, 0);

/*
        checkport.setBaudRate(9600);
        checkport.setNumDataBits(8);
        checkport.setNumStopBits(1);
        checkport.setParity(0);
        checkport.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING,0,0);
*/

            save = System.currentTimeMillis() + abtastzeit;
            found = false;
            while (System.currentTimeMillis() < save) {                                                           //für jeden port werden 5 sekunden lang überprüft ob er eine ID sendet

                    if (empfangen(checkport).contains("defaultID")) {  //fehler
                         ///////////////////////////////////////////TODO hier muss dem arduino noch eine neue ID zugewiesen werden
                        found = true;
                    }
                    /////////////////////////////////////////////TODO hier muss noch überprüft werden ob der Arduino schon bekannt ist (schon eine richtige ID hat)


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

                        for (SerialPort port : ports) {   //hier werden alle verfügbaren Ports durchtariert
                            i++;

                            System.out.println("i     " + i);
                            try {
                                SerialPort checkport = ports[i-2];

                                if (checkport.openPort()) {        //versuche port zu öffnen
                                    System.out.println("Successfully opened the port."+checkport.getSystemPortName());     //port geöffnet
                                    System.out.println("desprictiveportname:   " + checkport.getDescriptivePortName());
                                    output="versuche "+checkport.getSystemPortName()+" zu autentifizieren";


                                    checkAut(checkport);                                                                    //port wird versucht zu autentivizieren


                                } else {
                                    System.out.println("Unable to open the port.");
                                    output="Konnte Port nicht öffnen";                                  //port nicht geöffnet
                                }



                            } catch (ArrayIndexOutOfBoundsException e) {

                          //      e.printStackTrace();
                            }


                        }

                        output="Es wurden "+Arduinos.size()+" Arduinos autentiviziert.";

                        if(Step3.selectportlist!=null) {
                            Step3.selectportlist.setItems(SerialConnection.getPortNames());
                        }


                    }catch (Exception e) {
                    //    e.printStackTrace(); //for debug to find errors
                        output="es ist ein Fehler aufgetreten Bitte versuche es erneut\nwenn der Fehler weiterhin auftritt wende dich an den Support";
                        System.out.println("es ist ein Fehler in SerialConnection aufgetreten | ^^^^ hier oben ist die exeption ^^^^ viel spaß damit ^^^^");
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
