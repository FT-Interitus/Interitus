package de.ft.robocontrol.roboconnection.ev3connection;

import java.io.UnsupportedEncodingException;

public class ev3 {
    static final byte opCom_Set = (byte) 0xD4;
    static final byte opSound = (byte) 0x94;

   static final byte SET_BRICKNAME =(byte) 0x08;
    static final byte PLAY =(byte) 0x02;

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
        }

        return null;
    }


    public static byte[] LCX(int value) { //Experimentell


     String number = Integer.toString(value);

// or:
      //  String[] digits2 = number.split("(?<=.)");



return number.getBytes();
    }




}
