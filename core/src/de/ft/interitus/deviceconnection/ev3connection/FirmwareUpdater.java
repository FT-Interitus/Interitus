/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.ev3connection;

import de.ft.interitus.Program;
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

        Program.logger.config("Start Erasing");
        eraseDevice(hidDevice);
        Program.logger.config("End Erasing");
        Program.logger.config("Tell Ev3 Start Download");
        startFirmwareUpdate(data.length,hidDevice);
        Program.logger.config("Finished Telling");
        Program.logger.config("Start Sending Firmware");
        sendFirmware(data,hidDevice);
        Program.logger.config("End Sending");



        restartToLinux(hidDevice);


    }

    public static void update(File file) throws IOException {
Program.logger.config("Update");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] data =  fileInputStream.readAllBytes();


        HidDevice hidDevice = searchforFWDevice();
      //  Program.logger.config("Start Erasing");
      //  eraseDevice(hidDevice);
       // Program.logger.config("End Erasing");
       // Program.logger.config("Tell Ev3 Start Download");
      //  startFirmwareUpdate(data.length,hidDevice);
      //  Program.logger.config("Finished Telling");
      //  Program.logger.config("Start Sending Firmware");
      //  new Thread(() -> sendFirmware(data,hidDevice)).start();
       // while(getFirmwareUpdateProgress()!=100);

        Program.logger.config("End Sending");

        restartToLinux(hidDevice);


    }

    public static HidDevice searchforFWDevice() {
        HidDevice updatedevice =null;
        int counter = 0;

        while(updatedevice==null) {

            if(counter>=updatewaittimeout) {
                //ERROR
                Program.logger.config("error");
                return null;
            }
            //USBConnectionHandle.hidServices.scan();
            updatedevice = USBConnectionHandle.hidServices.getHidDevice(ev3.ID_VENDOR_LEGO,ev3.ID_PRODUCT_EV3_UPDATE,null);


            if(updatedevice==null) {
                Program.logger.config("Searching Device...");
            }

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;

        }

        Program.logger.config("Get Device");
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

            Program.logger.config("Waiting for finish took: "+(System.currentTimeMillis()/1000-time));

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


        Program.logger.config(""+lenght);
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
        Program.logger.config(lenght+"");
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




            Program.logger.config("Writing: "+(((float) ((float) pointer / (float) length)*100))+"%");
           // Program.logger.config("TOOK: "+(System.currentTimeMillis()-time1));
        }

            Program.logger.config("Write Finish");


    }


    public static float getFirmwareUpdateProgress() {
        return ((((float) pointer / (float) length) *100));

    }





}
