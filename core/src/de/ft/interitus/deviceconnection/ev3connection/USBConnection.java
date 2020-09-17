/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.ev3connection;

import de.ft.interitus.Programm;
import de.ft.interitus.utils.ArrayList;
import org.assertj.core.internal.bytebuddy.description.modifier.SyntheticState;
import org.hid4java.HidDevice;
import org.hid4java.HidManager;
import org.hid4java.HidServices;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

public class USBConnection {


    public static void main(String[] args) {

        HidServices hidServices = HidManager.getHidServices();
        HidDevice legodevice = null;

        while(legodevice==null) {
            hidServices = HidManager.getHidServices();
            legodevice =  hidServices.getHidDevice(ev3.ID_VENDOR_LEGO,ev3.ID_PRODUCT_EV3,null);
            legodevice.open();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(legodevice==null)
                System.out.println("Not found");



        }

        System.out.println("Found Device!");


        byte[] message = new byte[2];
        message[0] = (byte) 1;
        message[1] = (byte) 160;

       System.out.println( legodevice.open());
       int val = legodevice.write(message,message.length,(byte)0);
       if(val!=-1) {
           System.out.println("Success!");

       }
       hidServices.shutdown();

        System.exit(0);

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
