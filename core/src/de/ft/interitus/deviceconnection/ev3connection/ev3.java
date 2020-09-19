/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.ev3connection;

import de.ft.interitus.utils.ArrayList;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/***
 *
 * todo if this code is finish it has to be moved to Ev3-Funktions
 *
 */


public class ev3 {



    static final byte opCom_Set = (byte) 0xD4;
    static final byte opCom_Get = (byte) 0xD3;


    static final byte opSound = (byte) 0x94;
    static final byte opSound_Ready = (byte) 0x96;

    static final byte opUI_Write = (byte) 0x82;
    static final byte opUI_draw = (byte) 0x84;
    static final byte opUI_Button = (byte) 0x83;

    static final byte opTimer_Wait = (byte) 0x85;
    static final byte opTimer_Ready = (byte) 0x86;

    static final byte opFile = (byte) 0xC0;

    static final byte opProgram_Start = (byte) 0x03;
    static final byte opProgram_Stop = (byte) 0x02;


    static final byte SET_BRICKNAME = (byte) 0x08;
    static final byte GET_BRICKNAME = (byte) 0x0D;

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

    static final byte SET_BACK_BLOCK = (byte) 0x0A;
    static final byte TEXTBOX = (byte) 0x20;
    static final byte TERMINAL = (byte) 0x1F;
    static final byte GET_BACK_BLOCK = (byte) 0x0B;
    static final byte SCREEN_BLOCK = (byte) 0x10;

    static final byte UPDATE = (byte) 0x00;
    static final byte TOPLINE = (byte) 0x12;
    static final byte FILLWINDOW = (byte) 0x13;
    static final byte BMPFile = (byte) 0x1C;
    static final byte LINE = (byte) 0x03;

    static final byte LOAD_IMAGE = (byte) 0x08;


    static final byte PRESS = (byte) 0x05;
    static final byte RELEASE = (byte) 0x06;
    static final byte WAIT_FOR_PRESS = (byte) 0x03;

    static final byte NO_BUTTON = (byte) 0x00;
    static final byte UP_BUTTON = (byte) 0x01;
    static final byte ENTER_BUTTON = (byte) 0x02;
    static final byte DOWN_BUTTON = (byte) 0x03;
    static final byte RIGHT_BUTTON = (byte) 0x04;
    static final byte LEFT_BUTTON = (byte) 0x05;
    static final byte BACK_BUTTON = (byte) 0x06;
    static final byte ANY_BUTTON = (byte) 0x07;
    /**
     * EV3 Connection
     */

   public static final short ID_VENDOR_LEGO = (short) 0x0694;
    public static final short ID_PRODUCT_EV3 = (short) 0x0005;


    static final byte DIRECT_COMMAND_REPLY = (byte) 0x00;

    static final byte SYSTEM_COMMAND_REPLY = (byte) 0x01;
    static final byte SYSTEM_COMMAND_NO_REPLY = (byte) 0x81;
   // static DeviceHandle handle;

    public static Byte[] LCS(String string) {

        Byte[] a = new Byte[string.getBytes(StandardCharsets.UTF_8).length + 2];

        a[0] = (byte) 0x84;
        for (int i = 0; i < string.getBytes(StandardCharsets.UTF_8).length; i++) {
            a[i + 1] = string.getBytes(StandardCharsets.UTF_8)[i];
        }

        a[string.getBytes(StandardCharsets.UTF_8).length + 1] = (byte) 0x00;

        return a;

    }

    public static Byte[] LCX(int value) {


        byte count1 = (byte) 0x01;
        byte count2 = (byte) 0x81;
        byte count3 = (byte) 0x82;
        byte count4 = (byte) 0x83;
        byte count5 = (byte) 0x84;

        Byte[] returnarray = null;
        Integer integer = value;

        if (value >= -32 && value <= 31) {

            returnarray = new Byte[1];

            returnarray[0] = integer.byteValue();
            return returnarray;
        } else {


            if (value >= -127 && value <= 127) {
                returnarray = new Byte[2];
                returnarray[0] = count2;
                returnarray[1] = integer.byteValue();
                return returnarray;

            } else {

                if (value >= -32767 && value <= 32767) {
                    returnarray = new Byte[3];
                    returnarray[0] = (byte) 0x82;
                    returnarray[1] = (byte) value;
                    returnarray[2] = (byte) (value >> 8);
                } else {
                    returnarray = new Byte[5];
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

    public static Byte[] LVX(int value) {
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
        Byte[] returnarray = new Byte[ops.size()];
        for (int i = 0; i < ops.size(); i++) {
            returnarray[i] = ops.get(i);
        }

        return returnarray;

    }

    // Global Variable 1,2,4 bytes follow
    public static Byte[] GVX(int value) {
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
        Byte[] returnarray = new Byte[ops.size()];
        for (int i = 0; i < ops.size(); i++) {
            returnarray[i] = ops.get(i);
        }

        return returnarray;

    }



    public static byte[] makeSystemCommand(byte[] Command) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(6);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putShort((short) ((short) 4+Command.length-1));   // length
        buffer.putShort((short) 42);                            // counter
        buffer.put(SYSTEM_COMMAND_REPLY);                       // legth of creare
        buffer.put(Command[0]);                       // legth of creare



        for(int i=1;i<Command.length;i++) {
            buffer.put(Command[i]);
        }

        printHex("send",buffer);


        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes, 0, bytes.length);
        buffer.clear();
        bytes = new byte[buffer.capacity()];
        buffer.get(bytes, 0, bytes.length);



       


        return bytes;
    }

    public static byte[] makeDirectCmd(ArrayList<Byte> operations,
                                            int local_mem, int global_mem) {

        ByteBuffer buffer = ByteBuffer.allocateDirect(operations.size() + 7);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putShort((short) (operations.size() + 5));   // length
        buffer.putShort((short) 42);                            // counter
        buffer.put(DIRECT_COMMAND_REPLY);                       // type
        buffer.putShort((short) (local_mem * 1024 + global_mem)); // header

        for (int i = 0; i < operations.size(); i++) {         // operations
            buffer.put(operations.get(i));
        }

        printHex("send",buffer);


        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes, 0, bytes.length);
        buffer.clear();
        bytes = new byte[buffer.capacity()];
        buffer.get(bytes, 0, bytes.length);


        return bytes;



    }

    public static void printHex(String desc, ByteBuffer buffer) {
        System.out.print(desc + " 0x|");
        for (int i = 0; i < buffer.position() - 1; i++) {
            System.out.printf("%02X:", buffer.get(i));
        }
        System.out.printf("%02X|", buffer.get(buffer.position() - 1));
        System.out.println();
    }

    public static void printHex(String desc, Byte[] buffer) {
        System.out.print(desc + " 0x|");
        for (int i = 0; i < buffer.length - 1; i++) {
            System.out.printf("%02X:", buffer[i]);
        }
       // System.out.printf("%02X|", buffer.get(buffer.position() - 1));
        System.out.println();
    }








}
