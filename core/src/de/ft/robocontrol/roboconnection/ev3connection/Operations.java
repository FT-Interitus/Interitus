package de.ft.robocontrol.roboconnection.ev3connection;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Operations {


    public static ArrayList<Byte> led(int Color,boolean flash,boolean flash_two_times)  { //GREEN =0 RED = 1; ORANGE =2

        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opUI_Write);
        b.add(ev3.LED);

        if(flash) {

            if(flash_two_times) {
                if(Color==0) {
                    b.add(ev3.GREEN_FLASH_TWO_TIMES);
                }
                if(Color==1) {
                    b.add(ev3.RED_FLASH_TWO_TIMES);
                }
                if(Color==2) {
                    b.add(ev3.ORANGE_FLASH_TWO_TIMES);
                }
            }else{
                if(Color==0) {
                    b.add(ev3.GREEN_FLASH);
                }
                if(Color==1) {
                    b.add(ev3.RED_FLASH);
                }
                if(Color==2) {
                    b.add(ev3.ORANGE_FLASH);
                }
            }




        }else{
            if(Color==0) {
                b.add(ev3.GREEN);
            }
            if(Color==1) {
                b.add(ev3.RED);
            }
            if(Color==2) {
                b.add(ev3.ORANGE);
            }

        }


        return b;

    }

    public static ArrayList<Byte> playSound(String soundfile, int Volume,boolean repeat) {
        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opSound);

        if(!repeat) {
            b.add(ev3.PLAY);
        }else{
            b.add(ev3.REPEAT);
        }
        byte[] temp2 = ev3.LCX(Volume);

        for(int i =0;i<temp2.length;i++) {
            b.add(temp2[i]);
        }


        byte[] temp = ev3.LCS(soundfile);
        for(int i =0;i<temp.length;i++) {
            b.add(temp[i]);
        }
        return b;
    }

    public static ArrayList<Byte> stopsound() {
        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opSound);
        b.add(ev3.BREAK);

        return b;
    }

    public static ArrayList<Byte> waitforTone() {
        ArrayList<Byte> temp = new ArrayList<>();
        temp.add(ev3.opSound_Ready);
        return temp;
    }

    public static ArrayList<Byte> playTone(int Volume, int Frequency,int duration) {
        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opSound);
        b.add(ev3.TONE);
        byte[] temp = ev3.LCX(Volume);
        for(int i = 0;i<temp.length;i++) {
            b.add(temp[i]);
        }

        byte[] temp2 = ev3.LCX(Frequency);
        for(int i=0;i<temp2.length;i++) {
            b.add(temp2[i]);
        }

        byte[] temp3 = ev3.LCX(duration); //IN MS
        for(int i =0;i<temp3.length;i++) {
            b.add(temp3[i]);
        }

        return b;
    }


    public static ArrayList<Byte> setbrickname(String name) {

       ArrayList<Byte> operations = new ArrayList<>();


        operations.add(ev3.opCom_Set);
        operations.add(ev3.SET_BRICKNAME);
        byte[] temp = ev3.LCS(name);

        for(int i=0;i<temp.length;i++) {
            operations.add(temp[i]);
        }

        return operations;


    }



}
