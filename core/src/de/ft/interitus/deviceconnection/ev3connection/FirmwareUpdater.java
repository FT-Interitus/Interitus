/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.ev3connection;

import de.ft.interitus.deviceconnection.ev3connection.Exception.Ev3ErrorAnalyser;
import de.ft.interitus.deviceconnection.ev3connection.usb.USBConnectionHandle;
import org.hid4java.HidDevice;
import org.hid4java.HidManager;
import org.hid4java.jna.HidApi;
import org.hid4java.jna.HidApiLibrary;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirmwareUpdater {
    private static final int updatewaittimeout = 20;
  private static int pointer;
   private static int length;
    public static void update(File file,Device device) throws IOException {

        FileInputStream fileInputStream = null;
        try {
             fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

       byte[] data =  fileInputStream.readAllBytes();

        reboottofirmwareupdate(device);

        HidDevice hidDevice = searchforFWDevice();
        System.out.println("Start Erasing");
        eraseDevice(hidDevice);
        System.out.println("End Erasing");
        System.out.println("Tell Ev3 Start Download");
        startFirmwareUpdate(data.length,hidDevice);
        System.out.println("Finished Telling");
        System.out.println("Start Sending Firmware");
        sendFirmware(data,hidDevice);
        System.out.println("End Sending");

        restartToLinux(hidDevice);


    }

    public static void update(File file) throws IOException {
System.out.println("Update");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] data =  fileInputStream.readAllBytes();


        HidDevice hidDevice = searchforFWDevice();
      //  System.out.println("Start Erasing");
      //  eraseDevice(hidDevice);
       // System.out.println("End Erasing");
       // System.out.println("Tell Ev3 Start Download");
      //  startFirmwareUpdate(data.length,hidDevice);
      //  System.out.println("Finished Telling");
      //  System.out.println("Start Sending Firmware");
      //  new Thread(() -> sendFirmware(data,hidDevice)).start();
       // while(getFirmwareUpdateProgress()!=100);

        System.out.println("End Sending");

        restartToLinux(hidDevice);


    }

    public static HidDevice searchforFWDevice() {
        HidDevice updatedevice =null;
        int counter = 0;

        while(updatedevice==null) {

            if(counter>=updatewaittimeout) {
                //ERROR
                System.out.println("error");
                return null;
            }
            //USBConnectionHandle.hidServices.scan();
            updatedevice = USBConnectionHandle.hidServices.getHidDevice(ev3.ID_VENDOR_LEGO,ev3.ID_PRODUCT_EV3_UPDATE,null);


            if(updatedevice==null) {
                System.out.println("Searching Device...");
            }

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;

        }

        System.out.println("Get Device");
        return updatedevice;

    }

    public static void reboottofirmwareupdate(Device device) {

        device.getConnectionHandle().sendData(ev3.makeSystemCommand(SystemOperations.firmwareUpdate()),device);

    }

    public static void restartToLinux(HidDevice device) {

        byte[] bytes = new byte[1];
        bytes[0] = (byte)0xF4; //Restart to Linux

        byte[] sendingbytes = ev3.makeSystemCommand(bytes);
        device.write(sendingbytes,sendingbytes.length,(byte)0x00);
        device.close();

    }

    public static void eraseDevice(HidDevice device) {

        byte[] bytes = new byte[1];
        bytes[0] = (byte)0xF3;
        byte[] sendingbytes = ev3.makeSystemCommand(bytes);
        device.write(sendingbytes,sendingbytes.length,(byte)0x00);
        Byte[] recv = null;
        long time = System.currentTimeMillis()/1000;
        while(recv==null) {

            System.out.println("Waiting for finish took: "+(System.currentTimeMillis()/1000-time));

            recv =  device.read(1000,2000);
            ev3.printHex("recv",recv);

            if(recv.length<=0) {

                recv=null;
            }

            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }

    public static void startFirmwareUpdate(long lenght,HidDevice device) {


        System.out.println(lenght);
        byte[] bytes = new byte[1+8+8];
        bytes[0] = (byte) 0xF1;
        bytes[1] = (byte) 0x00;
        bytes[2] = (byte) 0x00;
        bytes[3] = (byte) 0x00;
        bytes[4] = (byte) 0x00;
        bytes[5] = (byte) lenght;
        bytes[6] = (byte) (lenght>>8);
        bytes[7] = (byte) (lenght>>16);
        bytes[8] = (byte) (lenght>>24);
        System.out.println(lenght);
        byte[] sendingbytes = ev3.makeSystemCommand(bytes);
        device.write(sendingbytes,sendingbytes.length,(byte)0x00);
        Byte[] returnbytes = null;
while(returnbytes==null) {
    returnbytes  =device.read(500, 2000);

    if(returnbytes!=null&&returnbytes.length<=0) {

        returnbytes= null;

    }

}

        ev3.printHex("recv from StartUpdate ",returnbytes);

    }

    public static void sendFirmware(byte[] data,HidDevice device) {

        int maxpaylod = 1000;

        pointer = 0;

        length = data.length;


    ArrayList<byte[]> bytearrays = new ArrayList<>();

   // int




        long time1;

        System.gc();

        device.setNonBlocking(true);

        while(length>pointer) {




            System.out.println("Writing: "+(((float) ((float) pointer / (float) length)*100))+"%");
           // System.out.println("TOOK: "+(System.currentTimeMillis()-time1));
        }

            System.out.println("Write Finish");


    }


    public static float getFirmwareUpdateProgress() {
        return ((((float) pointer / (float) length) *100));

    }





}
