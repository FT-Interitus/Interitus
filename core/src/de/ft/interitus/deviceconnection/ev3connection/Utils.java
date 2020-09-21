/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.ev3connection;

import java.util.ArrayList;

public class Utils {
    static Byte[] returnvalue;
   static byte[] sendArray;
    public static void downloadFile(String name,String content,Device device) {

        returnvalue = null;
        sendArray = null;
        int counter =0;
        int chucksize = 1000;
        byte[] contentbytes =content.getBytes();

        ArrayList<Byte> beginningbytes = SystemOperations.beginDownload(content.length(),name);

        byte[] bytes = new byte[beginningbytes.size()];

        for(int i=0;i<bytes.length;i++) {

            bytes[i] = beginningbytes.get(i);

        }


      Byte[] filehandle =  device.getConnectionHandle().sendData(ev3.makeSystemCommand(bytes),device);
        ev3.printHex("recv",filehandle);
        System.out.println("SIZE: "+contentbytes.length);
        if(filehandle[6]==(byte)0x00) {


            while(counter<contentbytes.length)  {

                sendArray = new byte[Math.min(chucksize,contentbytes.length-counter)];
                for(int i=0;i<sendArray.length;i++) {

                    sendArray[i] = contentbytes[counter++];

                }


                returnvalue = device.getConnectionHandle().sendData(ev3.makeSystemCommand(SystemOperations.continueDownload(filehandle[7],sendArray)),device);

                System.out.println(filehandle[7]);
                ev3.printHex("recv",returnvalue);

                if(returnvalue[6]!=(byte)0x00) {
                    throw new RuntimeException("Error while Uploading File");


                }


            }




        }else{
            throw new RuntimeException("Error while getting FileHandle");
        }

        System.out.println("Filehandle: "+filehandle[7]);
        Close_FileHandle(filehandle[7],device);
    }

    public static String uploadFile(String path, Device device){
        String file="";
        final int  maxbytetoread=1000;
        long size=0;
        //Byte[] bytes=new Byte[1000];

        Byte[] payload =  device.getConnectionHandle().sendData(ev3.makeSystemCommand(SystemOperations.Begin_Upload(maxbytetoread, path.getBytes())),device);

        byte filehandle = payload[11];
        ev3.printHex("recv",payload);
        //size=size|(payload[7]);
        //size=size|(payload[8]<<8);
        //size=size|(payload[9]<<16);
        //size=size|(payload[10]<<24);
         size =
                ((payload[7] & 0xFF) <<  0) |
                        ((payload[8] & 0xFF) <<  8) |
                        ((payload[9] & 0xFF) << 16) |
                        ((payload[10] & 0xFF) << 24);

        int counter=0;
        System.out.println("filesize: "+size);
        if(maxbytetoread>=size){
            byte[] data = new byte[(int) size];
            for(int i=0;i<size; i++){
                data[i]=payload[12+i];
            }
            return new String(data);

        }else{
            counter=maxbytetoread;
            String content="";

            byte[] data = new byte[(int) size];
            for(int i=0;i<maxbytetoread; i++){
                data[i]=payload[12+i];
            }
            content+= new String(data);


            while(size > counter){

                Byte[] additionalcontent =  device.getConnectionHandle().sendData(ev3.makeSystemCommand(SystemOperations.Continue_Upload(filehandle,maxbytetoread )),device);

                ev3.printHex("recv",additionalcontent);

                int tempcounter = counter;
                for(int i=0;i<Math.min(maxbytetoread,size-tempcounter);i++) {


                   content+= ((char) additionalcontent[i + 8].byteValue());
                    counter++;
                }


            }
            return content;
        }


    }
    public static void Close_FileHandle(byte handle, Device device){
        Byte[] payload =  device.getConnectionHandle().sendData(ev3.makeSystemCommand(SystemOperations.Close_FileHandle(handle)),device);
        ev3.printHex("recv",payload);

    }
    public static void Close_all_FileHandle(int filehandleanzahl, Device device){
        for(byte i=0;i<filehandleanzahl;i++){
            Byte[] payload =  device.getConnectionHandle().sendData(ev3.makeSystemCommand(SystemOperations.Close_FileHandle(i)),device);
            ev3.printHex("recv",payload);
        }
    }
    public static void Delete_File(String path, Device device){
        Byte[] payload =  device.getConnectionHandle().sendData(ev3.makeSystemCommand(SystemOperations.Delete_File(path.getBytes())),device);
        ev3.printHex("recv",payload);
    }

}
