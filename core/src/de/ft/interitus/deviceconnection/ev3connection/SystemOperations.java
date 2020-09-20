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
    public static ArrayList<Byte> beginDownload(int length, String name) {



        ArrayList<Byte> bytes = new ArrayList<>();

        bytes.add(BEGIN_DOWNLOAD);
        Byte[] tempbytes = ev3.LCX(length);


        for(int i=0;i<tempbytes.length;i++) {

            bytes.add(tempbytes[i]);
        }

        Byte[] tempbytes2 = ev3.LCS(name);

        for(int i=0;i<tempbytes2.length;i++) {

            bytes.add(tempbytes2[i]);
        }




        return bytes;

    }

    public static ArrayList<Byte> continueDownload(byte FileHandle,byte[] data) {

        ArrayList<Byte> bytes = new ArrayList<>();
        bytes.add(CONTINUE_DOWNLOAD);
        bytes.add(FileHandle);
        for(int i=0;i<data.length;i++) {

            bytes.add(data[i]);

        }


        return bytes;

    }







}
