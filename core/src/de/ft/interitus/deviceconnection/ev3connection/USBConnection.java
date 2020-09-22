/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.ev3connection;

import de.ft.interitus.deviceconnection.ev3connection.Exception.Ev3ErrorAnalyser;
import de.ft.interitus.deviceconnection.ev3connection.usb.USBConnectionHandle;
import de.ft.interitus.deviceconnection.ev3connection.usb.USBDevice;

import java.util.ArrayList;


/**
 * Only for testing purpose
 */
public class USBConnection {


    public static void main(String[] args) {





        USBConnectionHandle usbConnectionHandle = new USBConnectionHandle();



        ArrayList<Byte> command = new ArrayList<>();

/*
        for(int i=0;i<128;i++) {
            command.clear();


            command.addAll(Operations.ev3statusline(false));
            command.addAll(Operations.fillwindow(false, 1, 1));


            System.out.println(i);


            try {
                TimeUnit.MILLISECONDS.sleep(75);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

 */

       // command.addAll(Operations.fillwindow(true, 0, 10));
       // command.clear();
        // command.addAll(Operations.playTone(100, 600, 100));

//command.addAll(Operations.blockbackbutton(true));
        //command.addAll(Operations.updateev3screen());

        //command.addAll(Operations.stopProgramm(0));
        // command.addAll(Operations.updateev3screen());
        //   while(true) {

        //command.addAll(Operations.setbrickname("Felix"));
        //command.addAll(Operations.setbrickname("Tim"));
//command.addAll(Operations.setbrickname("Hallo"));

       // command.addAll(Operations.setbrickname("Hallo"));
        USBDevice device = new USBDevice(USBConnectionHandle.hidServices.getHidDevice(ev3.ID_VENDOR_LEGO, ev3.ID_PRODUCT_EV3, null),usbConnectionHandle,"");

//String a1000 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//a1000 += "\nHallo das ist ein Test";



        //Utils.Close_all_FileHandle(10, device);
        //Utils.downloadFile("../apps/data123.txt", a1000,device);
        try {
           ArrayList<ArrayList<Byte>> bytes = Ev3SystemUtils.ListFilesinPath("/home/root/lms2012/prjs/",device);
           for(String string: Ev3SystemUtils.listedfilestoStrings(bytes)) {

               System.out.println(string);

           }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Utils.Delete_File("../apps/data123.txt", device);
        //Utils.Close_FileHandle((byte)0x01,device);
       //System.out.println("data: "+Utils.uploadFile("../apps/data123.txt",device));

      //  System.out.println(device.getDevice().getPath());

      // command.addAll(Operations.fillwindow(true,0,40));
      // command.addAll(Operations.updateev3screen());
        //command.addAll(Operations.showTextBox(10,10,100,100,"Hallo",(byte)0x00));
//command.addAll(Operations.led(2,true,true));

      //  command.addAll(Operations.updateev3screen());
       // command.addAll(Operations.playSound("./ui/DownloadSucces",100,false));

        //Byte[] returnbytes = usbConnectionHandle.sendData(ev3.makeDirectCmd(command,16,16),device);
        //Byte[] returnbytes = usbConnectionHandle.sendData(ev3.makeSystemCommand(SystemOperations.firmwareUpdate()),device);

        //ev3.printHex("recv",returnbytes);


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

        // command.addAll(Operations.setbrickname("MyEv3"));
        device.getDevice().close();
        USBConnectionHandle.hidServices.shutdown();




    }


}
