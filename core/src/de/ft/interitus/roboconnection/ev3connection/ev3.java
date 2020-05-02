package de.ft.interitus.roboconnection.ev3connection;

import de.ft.interitus.DisplayErrors;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/***
 *          !Vermutung!
 *
 * Bei Lego gibt es Definition von String, Int,...
 *
 * Bei String ist es 0x84 am Anfang
 * Bei Int ist es 0x81 am Anfang
 *
 *
 */

public class ev3 {
    static final byte opCom_Set = (byte) 0xD4;

    static final byte opSound = (byte) 0x94;
    static final byte opSound_Ready = (byte) 0x96;

    static final byte opUI_Write = (byte) 0x82;
    static final byte opUI_draw = (byte) 0x84;

    static final byte SET_BRICKNAME = (byte) 0x08;

    static final byte TONE = (byte) 0x01;
    static final byte PLAY = (byte) 0x02;
    static final byte REPEAT = (byte) 0x03;
    static final byte BREAK = (byte) 0x00;

    static final byte LED = (byte) 0x1B;
    static final byte GREEN = (byte) 0x01;
    static final byte RED = (byte) 0x02;
    static final byte ORANGE = (byte) 0x03;
    static final byte GREEN_FLASH = (byte) 0x04;
    static final byte RED_FLASH = (byte) 0x05;
    static final byte ORANGE_FLASH = (byte) 0x06;
    static final byte GREEN_FLASH_TWO_TIMES = (byte) 0x07;
    static final byte RED_FLASH_TWO_TIMES = (byte) 0x08;
    static final byte ORANGE_FLASH_TWO_TIMES = (byte) 0x09;

    static final byte UPDATE = (byte) 0x00;
    static final byte TOPLINE = (byte) 0x12;
    static final byte FILLWINDOW = (byte) 0x13;
    static final byte BMPFile = (byte) 0x1C;
    static final byte LINE = (byte) 0x03;


    public static byte[] LCS(String string) {
        try {

            byte[] b = string.getBytes("UTF-8");
            byte[] a = new byte[string.getBytes("UTF-8").length + 2];

            a[0] = (byte) 0x84;
            for (int i = 0; i < string.getBytes("UTF-8").length; i++) {
                a[i + 1] = string.getBytes("UTF-8")[i];
            }

            a[string.getBytes("UTF-8").length + 1] = (byte) 0x00;

            return a;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            DisplayErrors.error = e;
        }

        return null;
    }


    public static byte[] LCX(int value) { //TODO eventuell ist hier das = in den if s falsch


        byte count1 = (byte) 0x01;
        byte count2 = (byte) 0x81;
        byte count3 = (byte) 0x82;
        byte count4 = (byte) 0x83;
        byte count5 = (byte) 0x84;

        byte[] returnarray = null;
        Integer integer = value;

        if (value >= -32 && value <= 31) {

            returnarray = new byte[1];

            returnarray[0] = integer.byteValue();
            return returnarray;
        } else {


            if (value >= -127 && value <= 127) {
                returnarray = new byte[2];
                returnarray[0] = count2;
                returnarray[1] = integer.byteValue();
                return returnarray;

            } else { //TODO bis hir hin geht alles

                if (value >= -32767 && value <= 32767) {
                    returnarray = new byte[3];
                    returnarray[0] = (byte) 0x82;
                    returnarray[1] = (byte) value;
                    returnarray[2] = (byte) (value >> 8);
                } else {
                    returnarray = new byte[5];
                    returnarray[0] = (byte) 0x83;
                    returnarray[1] = (byte) value;
                    returnarray[2] = (byte) (value >> 8);
                    returnarray[3] = (byte) (value >> 16);
                    returnarray[4] = (byte) (value >> 24);
                }

            }

        }


        return returnarray;


    }


    public byte[] addLVX(int value) {
        List<Byte> ops = new ArrayList<>();
        if (value < 0) {
            throw new IllegalArgumentException("Parameter must be positive " + value);
        } else if (value < 32) {
            ops.add((byte) (0x40 | value));
        } else if (value < 256) {
            ops.add((byte) 0xc1);
            ops.add((byte) value);
        } else if (value < 65536) {
            ops.add((byte) 0xc2);
            ops.add((byte) value);
            ops.add((byte) (value >> 8));
        } else {
            ops.add((byte) 0xc3);
            ops.add((byte) value);
            ops.add((byte) (value >> 8));
            ops.add((byte) (value >> 16));
            ops.add((byte) (value >> 24));
        }
        byte[] returnarray = new byte[ops.size()];
       for(int i=0;i<ops.size();i++) {
           returnarray[i] = ops.get(i);
       }

       return returnarray;

    }

    // Global Variable 1,2,4 bytes follow
    public byte[] addGVX(int value) {
        List<Byte> ops = new ArrayList<>();
        if (value < 0) {
            throw new IllegalArgumentException("Parameter must be positive " + value);
        } else if (value < 32) {
            ops.add((byte) (0x60 | value));
        } else if (value < 256) {
            ops.add((byte) 0xe1);
            ops.add((byte) value);
        } else if (value < 65536) {
            ops.add((byte) 0xe2);
            ops.add((byte) value);
            ops.add((byte) (value >> 8));
        } else {
            ops.add((byte) 0xe3);
            ops.add((byte) value);
            ops.add((byte) (value >> 8));
            ops.add((byte) (value >> 16));
            ops.add((byte) (value >> 24));
        }
        byte[] returnarray = new byte[ops.size()];
        for(int i=0;i<ops.size();i++) {
            returnarray[i] = ops.get(i);
        }

        return returnarray;

    }

}
