package de.ft.interitus.roboconnection.ev3connection;

import de.ft.interitus.displayErrors;

import java.io.UnsupportedEncodingException;

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

   static final byte SET_BRICKNAME =(byte) 0x08;

    static final byte TONE = (byte) 0x01;
    static final byte PLAY =(byte) 0x02;
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
    static final byte FILLWINOW = (byte) 0x13;
    static final byte BMPFile = (byte) 0x1C;
    //TODO Hier weitermachen

    public static byte[] LCS(String string) {
        try {

            byte[] b = string.getBytes("UTF-8");
            byte[] a = new byte[string.getBytes("UTF-8").length+2];

             a[0] = (byte) 0x84;
             for(int i = 0;i<string.getBytes("UTF-8").length;i++) {
                 a[i+1] = string.getBytes("UTF-8")[i];
             }

             a[string.getBytes("UTF-8").length+1] = (byte) 0x00;

            return a;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            displayErrors.error = e;
        }

        return null;
    }


    public static byte[] LCX(int value) { //Experimentell

        byte[] returnarray = new byte[2];

        returnarray[0] = (byte) 0x81;
        returnarray[1] = (byte) Integer.parseInt(Integer.toHexString(100));

       return returnarray;


    }




}
