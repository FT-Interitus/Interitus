package de.ft.interitus.deviceconnection.ev3connection;

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
        // command.addAll(Operations.playTone(100, 600, 100));

//command.addAll(Operations.blockbackbutton(true));
        //command.addAll(Operations.updateev3screen());

        //command.addAll(Operations.stopProgramm(0));
        // command.addAll(Operations.updateev3screen());
        //   while(true) {
        //command.addAll(Operations.releaseKey(ev3.LEFT_BUTTON));
        //command.addAll(Operations.setbrickname("Tim"));
//command.addAll(Operations.setbrickname("Hallo"));

        command.addAll(Operations.setbrickname("Hallo"));


        ev3.sendcommand(command, 0, 0);
        command.clear();
        try {
            TimeUnit.MILLISECONDS.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //  command.addAll(Operations.loadProgrammFiles(4,"../prjs/newUI/test.rbf",0,4));
        // command.addAll(Operations.startProgramm(4,0,4,false));

        //  ev3.sendcommand(command, 10, 10);
        // command.clear();
        // try {
        //      TimeUnit.MILLISECONDS.sleep(7000);
        //  } catch (InterruptedException e) {
        ///       e.printStackTrace();
        ///   }
        ///   command.addAll(Operations.stopProgramm(0));
        //   ev3.sendcommand(command, 10, 10);
        // command.clear();
        // }

        //command.addAll(Operations.playSound("./ui/DownloadSucces",100,false));
        // command.addAll(Operations.setbrickname("MyEv3"));


        ev3.closeev3session();


    }


}
