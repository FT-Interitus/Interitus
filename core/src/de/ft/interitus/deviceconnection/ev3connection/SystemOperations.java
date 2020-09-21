/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.ev3connection;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class SystemOperations {

    // system commands
    public static byte BEGIN_DOWNLOAD 				 = (byte) 0x92;
    public static byte CONTINUE_DOWNLOAD			 = (byte) 0x93;
    public static byte BEGIN_UPLOAD				  	 = (byte) 0x94;
    public static byte CONTINUE_UPLOAD				 = (byte) 0x95;
    public static byte BEGIN_GETFILE 				 = (byte) 0x96;
    public static byte CONTINUE_GETFILE				 = (byte) 0x97;
    public static byte CLOSE_FILEHANDLE 			 = (byte) 0x98;
    public static byte LIST_FILES 					 = (byte) 0x99;
    public static byte CONTINUE_LIST_FILES			 = (byte) 0x9A;
    public static byte CREATE_DIR					 = (byte) 0x9B;
    public static byte DELETE_FILE					 = (byte) 0x9C;
    public static byte LIST_OPEN_HANDLES			 = (byte) 0x9D;
    public static byte WRITEMAILBOX					 = (byte) 0x9E;
    public static byte BLUETOOTHPIN					 = (byte) 0x9F;
    public static byte ENTERFWUPDATE				 = (byte) 0xA0;

    public static byte[] firmwareUpdate() {

        byte[] bytearray = new byte[1];
        bytearray[0] =ENTERFWUPDATE;

        return bytearray;

    }
    public static ArrayList<Byte> beginDownload(long length, String name) {



        ArrayList<Byte> bytes = new ArrayList<>();

        bytes.add(BEGIN_DOWNLOAD);



        bytes.add((byte)0x83);
        bytes.add((byte)length);
        bytes.add((byte) (length >> 8));
        bytes.add((byte) (length >> 16));





        byte[] tempbytes2 = name.getBytes();

        for(int i=0;i<tempbytes2.length;i++) {

            bytes.add(tempbytes2[i]);
        }


        bytes.add((byte)0x00);

        return bytes;

    }

    public static byte[] continueDownload(byte FileHandle,byte[] data) {

       byte[] bytes = new byte[2+data.length];
        bytes[0] =(CONTINUE_DOWNLOAD);
        bytes[1] =(FileHandle);

        for(int i=0;i<data.length;i++) {

            bytes[i+2] = data[i];

        }


        return bytes;

    }

    public static byte[] Begin_Upload(int maxbytetoread, byte[] path){
        byte[] bytes = new byte[4+path.length];

        bytes[0]=BEGIN_UPLOAD;
        //bytes[1]=(byte)0x82;
        bytes[1]=(byte)(maxbytetoread);
        bytes[2]=(byte)(maxbytetoread>>8);

        for(int i=0;i<path.length;i++){
            bytes[i+3]=path[i];
        }

        return bytes;
    }

    public static byte[] Continue_Upload(byte filehandle, int maxbytetoread){
        byte[] bytes = new byte[4];

        bytes[0]=CONTINUE_UPLOAD;
        bytes[1]=filehandle;
        bytes[2]=(byte)maxbytetoread;
        bytes[3]=(byte)(maxbytetoread >> 8);

        return bytes;
    }

    public static byte[] Close_FileHandle(byte handle){
        byte[] bytes = new byte[3];
        bytes[0]=CLOSE_FILEHANDLE;
        bytes[1]=handle;
        bytes[2]=0x00;

        return bytes;
    }








}
