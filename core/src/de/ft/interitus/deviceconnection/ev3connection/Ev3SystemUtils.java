/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.ev3connection;

import de.ft.interitus.deviceconnection.ev3connection.Exception.Ev3EndOfFileException;
import de.ft.interitus.deviceconnection.ev3connection.Exception.Ev3ErrorAnalyser;

import de.ft.interitus.utils.ArrayList;

public class Ev3SystemUtils {
    static Byte[] returnvalue;
   static byte[] sendArray;
    public static void downloadFile(String name,String content,Device device) throws Exception {

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


            while(counter<contentbytes.length) {

                sendArray = new byte[Math.min(chucksize, contentbytes.length - counter)];
                for (int i = 0; i < sendArray.length; i++) {

                    sendArray[i] = contentbytes[counter++];

                }


                returnvalue = device.getConnectionHandle().sendData(ev3.makeSystemCommand(SystemOperations.continueDownload(filehandle[7], sendArray)), device);

                System.out.println(filehandle[7]);
                ev3.printHex("recv", returnvalue);

                if (returnvalue[6] != (byte) 0x00) {
                    Ev3ErrorAnalyser.analyze(returnvalue[6]);


                }

            }




        }else {
            Ev3ErrorAnalyser.analyze(filehandle[6]);
        }

        System.out.println("Filehandle: "+filehandle[7]);
        close_FileHandle(filehandle[7],device);
    }

    public static String uploadFile(String path, Device device) throws Exception {
        String file="";
        final int  maxbytetoread=1000;
        long size=0;
        //Byte[] bytes=new Byte[1000];

        Byte[] payload =  device.getConnectionHandle().sendData(ev3.makeSystemCommand(SystemOperations.Begin_Upload(maxbytetoread, path.getBytes())),device);

        Ev3ErrorAnalyser.analyze(payload[6]);

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

                Ev3ErrorAnalyser.analyze(additionalcontent[6]);

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
    public static void close_FileHandle(byte handle, Device device) throws Exception {
        Byte[] payload =  device.getConnectionHandle().sendData(ev3.makeSystemCommand(SystemOperations.Close_FileHandle(handle)),device);
        ev3.printHex("recv",payload);
        Ev3ErrorAnalyser.analyze(payload[6]);

    }
    public static void close_all_FileHandle(int filehandleanzahl, Device device) throws Exception {
        for(byte i=0;i<filehandleanzahl;i++){
            Byte[] payload =  device.getConnectionHandle().sendData(ev3.makeSystemCommand(SystemOperations.Close_FileHandle(i)),device);
            ev3.printHex("recv",payload);
            Ev3ErrorAnalyser.analyze(payload[6]);
        }
    }
    public static void Delete_File(String path, Device device) throws Exception {
        Byte[] payload =  device.getConnectionHandle().sendData(ev3.makeSystemCommand(SystemOperations.Delete_File(path.getBytes())),device);
        ev3.printHex("recv",payload);
        Ev3ErrorAnalyser.analyze(payload[6]);

    }

    /**
     * Create a Dir relative to lms2012
     * @param path path to the new dir
     * @param device device to be send to
     */
    public static void create_Dir(String path, Device device) throws Exception {
        if(!path.startsWith("/")) {
            path = "/home/root/lms2012/" + path;
        }
        Byte[] payload =  device.getConnectionHandle().sendData(ev3.makeSystemCommand(SystemOperations.Create_Dir(path.getBytes())),device);
        ev3.printHex("recv",payload);

        Ev3ErrorAnalyser.analyze(payload[6]);

    }

    public static ArrayList<ArrayList<Byte>> ListFilesinPath(String path, Device device) throws Exception {
        if(!path.startsWith("/")) {
            path = "/home/root/lms2012/" + path;
        }
        final int  maxbytetoread=1000;

        Byte[] payload = device.getConnectionHandle().sendData(ev3.makeSystemCommand(SystemOperations.List_Files(path.getBytes(),maxbytetoread)),device);


        try {
            Ev3ErrorAnalyser.analyze(payload[6]);
        }catch (Ev3EndOfFileException ignored) {

        }

        long sizeofcontent =
                ((payload[7] & 0xFF) <<  0) |
                        ((payload[8] & 0xFF) <<  8) |
                        ((payload[9] & 0xFF) << 16) |
                        ((payload[10] & 0xFF) << 24);
        byte filehandle = payload[11];


        byte[] listfiles = new byte[(int)sizeofcontent];

        for(int i=0;i<Math.min(sizeofcontent,maxbytetoread);i++) {

            listfiles[i] = payload[i+12];

        }



        if(Math.min(sizeofcontent,maxbytetoread)<sizeofcontent) {
            int counter = maxbytetoread;
            while(sizeofcontent>counter) {


                Byte[] getmorefiles = device.getConnectionHandle().sendData(ev3.makeSystemCommand(SystemOperations.continueListFile(filehandle, (int) Math.min(sizeofcontent-counter,maxbytetoread))),device);



                Ev3ErrorAnalyser.analyze(getmorefiles[6]);

                int temppointer = counter;
                for(int i=0;i<Math.min(sizeofcontent-temppointer,maxbytetoread);i++) {
                    counter++;
                    listfiles[counter+i] = getmorefiles[8+i];



                }


            }


        }



        ev3.printHex("recv",listfiles);



        ArrayList<ArrayList<Byte>> bytesarray = new ArrayList<>();

        for(int i=0;i<listfiles.length;i++) {

            if(listfiles[i]==(byte)0x0A) {

                bytesarray.add(new ArrayList<>());

            }
        }

        int jumper =0;
        for(int i=0;i<listfiles.length;i++) {
            if(listfiles[i]==(byte)0x0A) {
                jumper++;
                continue;
            }


            bytesarray.get(jumper).add(listfiles[i]);

        }




        return bytesarray;


    }


    public static ArrayList<String> listedfilestoStrings(ArrayList<ArrayList<Byte>> bytes) {

        ArrayList<String> strings = new ArrayList<>();

        String tempstring="";

        for(ArrayList<Byte> files:bytes)  {

            tempstring="";

            if(!files.contains(" ".getBytes()[0])) {

                for(Byte byte1:files) {
                    tempstring+=(char)byte1.byteValue();

                }
            }else{
                int first = 0;
                for(Byte byte1:files) {
                    if(first!=2) {
                        if((char)byte1.byteValue()==' ') {
                            first++;
                        }
                    }else {
                        tempstring += (char) byte1.byteValue();
                    }
                }

            }



            strings.add(tempstring);


        }

        return strings;


    }







}
