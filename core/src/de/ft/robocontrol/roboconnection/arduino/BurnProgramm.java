package de.ft.robocontrol.roboconnection.arduino;

import de.ft.robocontrol.Block.Devices;
import de.ft.robocontrol.ProgrammingSpace;
import de.ft.robocontrol.displayErrors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BurnProgramm {
    static String platform;
    public static String ausgabe="";
    private static String OS = System.getProperty("os.name").toLowerCase();


    public static void burn(int arduino, String port, String file) {

        String methode = null;
        if (arduino == Devices.ARDUINO_UNO) { //TODO add platforms
            platform = "atmega328p";
            methode = "arduino";
        }

        if (arduino == Devices.ARDUINO_MEGA) {
            platform = "atmega2560";
            methode = "wiring";
        }


        if (isWindows()) {
            burnWindows(platform, port, file, methode);
        } else if (isMac()) {
            brunApple(platform, port, file, methode);
        } else if (isUnix()) {
            burnLinux(platform, port, file, methode);

        } else {
            ProgrammingSpace.logger.severe("You OS is not supported");
        }

    }

    //OS tesster
    private static boolean isWindows() {

        return (OS.indexOf("win") >= 0);

    }

    private static boolean isMac() {

        return (OS.indexOf("mac") >= 0);

    }

    private static boolean isUnix() {

        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

    }


    //Burn start


    private static void burnLinux(String platform, String port, String file, String methode) {

        Runtime rt = Runtime.getRuntime();


        port = "/dev/" + port;
        try {
            Process pr = rt.exec("./libs/avrdude -Clibs/avrdude.conf -v -p" + platform + " -c " + methode + " -P" + port + " -b115200 -D -Uflash:w:libs/" + file + ":i"); //TODO Progress



            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
            String line = null;
            String output = null;

            while ((line = input.readLine()) != null) {
               ProgrammingSpace.logger.finer( line);
                output = output + line;

            }


            postproduktion(output, port);


        } catch (IOException e) {
            e.printStackTrace();
            displayErrors.error = e;
        }

    }


    private static void burnWindows(String platform, String port, String file, String methode) {

        Runtime rt = Runtime.getRuntime();


        port = "" + port;
        try {
            Process pr = rt.exec("libs\\avrdude.exe -C libs\\avrdude.conf -v -p " + platform + " -c" + methode + " -P" + port + " -b115200 -D -Uflash:w:libs\\" + file + ":i"); //TODO Progress


            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
            String line = null;
            String output = null;

            while ((line = input.readLine()) != null) {
                ProgrammingSpace.logger.finer( line);
                output = output + line;
                postproduktion(output, port);
            }





        } catch (IOException e) {
            displayErrors.error = e;
            e.printStackTrace();
        }

    }

    private static void brunApple(String platform, String port, String file, String methode) {

        Runtime rt = Runtime.getRuntime();


        port = "/dev/" + port;
        try {
            Process pr = rt.exec("libs/avrdudeapple -C libs/avrdude.conf -v -p " + platform + " -c" + methode + " -P" + port + " -b115200 -D -Uflash:w:libs/" + file + ":i"); //TODO Progress



            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
            String line = null;
            String output = null;

            while ((line = input.readLine()) != null) {
                ProgrammingSpace.logger.finer( line);
                output = output + line;

            }



            postproduktion(output, port);


        } catch (IOException e) {
            displayErrors.error = e;
            e.printStackTrace();
        }

    }



    public static void postproduktion(String output, String port) {

        if (output.contains("AVR device initialized")) {
            ausgabe=("Brennen hat funktioniert, nun kannst du dein Gerät konfigurieren");



        } else {
            ausgabe=("Unbekannter Fehler");
        }

        if (output.contains("can't open device")) {
            ausgabe=("Brennen nicht erfolgreich\nhast du den richtigen Port und das richtige Board ausgewählt?\nwenn das dein 2. versuch des Brennens war dann trenne und stecke den \n Arduino neu an");
        }

        if (output.contains("Expected signature")) {
            ausgabe=("Brennen nicht erfolgreich du hast den falschen Board-Type ausgewählt");
        }

        if (output.contains("not found")) {
            ausgabe=("Brennen nicht erfolgreich du hast den falschen Board-Type ausgewählt");
        }

        if (output.contains("programmer is not responding")) {
            ausgabe=("Brennen nicht erfolgreich hast du den richtigen Port und das richtige Board ausgewählt?");
        }

        if (output.contains("Permission denied")) {
            if (isUnix()) {
                ausgabe=("Keine Berechtigung! Führe den Befehl: \"sudo chmod a+rw " + port + "\" aus.");
            } else {
                ausgabe=("Keine Berechtigung starte den PC neu.");
            }
        }


    }


}
