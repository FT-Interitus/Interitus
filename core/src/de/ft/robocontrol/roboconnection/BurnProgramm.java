package de.ft.robocontrol.roboconnection;

import de.ft.robocontrol.Block.Arduino;

import java.io.IOException;

public class BurnProgramm {
   static String platform;
private static String OS = System.getProperty("os.name").toLowerCase();
    protected static void burn(int arduino, String port) {
        if(arduino==Arduino.UNO) { //TODO add platforms
            platform = "";

        }

            if (arduino == Arduino.MEGA) {
                platform = "atmega2560";
            }




        if (isWindows()) {

        System.out.println("---------------------------------------------");
            System.out.println("Noch kein Windows support");
            System.out.println("Noch kein Windows support");
            System.out.println("Noch kein Windows support");
            System.out.println("Noch kein Windows support");
            System.out.println("Noch kein Windows support");
            System.out.println("Noch kein Windows support");
            System.out.println("Noch kein Windows support");
            System.out.println("Noch kein Windows support");
            System.out.println("Noch kein Windows support");
            System.out.println("Noch kein Windows support");

            System.out.println("---------------------------------------------");



        } else if (isMac()) {
            System.out.println("This is Mac");
        } else if (isUnix()) {
            burnLinux(platform,port);
            System.out.println("Linux");
        } else if (isSolaris()) {
            System.out.println("This is Solaris");
        } else {
            System.out.println("Your OS is not support!!");
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

        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );

    }

    private static boolean isSolaris() {

        return (OS.indexOf("sunos") >= 0);

    }


    //Burn start


    private static void burnLinux(String arduino, String port) {

        Runtime rt = Runtime.getRuntime();


        try {
            Process pr = rt.exec("./libs/avrdude -C libs/avrdude.conf -v -p "+ arduino + " -cwiring -P"+port+" -b115200 -D Uflash:w:libs/sketch_mar31a.ino.hex"); //TODO Progress
            System.out.println(pr.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
