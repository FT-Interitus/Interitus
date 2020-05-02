package de.ft.interitus.roboconnection.ev3connection;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class USBConnection {


    public static void main(String[] args) {

        ev3.openev3sesseion();

        ArrayList<Byte> command = new ArrayList<>();
/*
        for(int i=0;i<128;i++) {
            command.clear();


            command.addAll(Operations.ev3statusline(false));
            command.addAll(Operations.fillwindow(false, 1, 1));
            command.addAll(Operations.fillwindow(true, 128, 2));
            //  command.addAll(Operations.displayBMPFile(false,0,15,"../apps/Motor Control/MotorCtlAD.rgf"));
            command.addAll(Operations.updateev3screen());
            System.out.println(i);


            try {
                TimeUnit.MILLISECONDS.sleep(75);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

 */

            command.clear();
            //command.addAll(Operations.playTone(100, i, 100-i/5));
        command.addAll(Operations.drawline(false,2,125,88,2));
        command.addAll(Operations.updateev3screen());
            ev3.sendcommand(command);

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        //command.addAll(Operations.playSound("./ui/DownloadSucces",100,false));
       // command.addAll(Operations.setbrickname("MyEv3"));




        ev3.closeev3session();


    }


}
