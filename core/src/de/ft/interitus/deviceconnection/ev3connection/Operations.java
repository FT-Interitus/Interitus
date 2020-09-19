/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.ev3connection;

import de.ft.interitus.utils.ArrayList;

import java.util.Arrays;

public class Operations {

    public static ArrayList<Byte> stopProgramm(int Slot) {
        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opProgram_Stop);
        Byte[] temp1 = ev3.LCX(Slot);

        for (int i = 0; i < temp1.length; i++) {
            b.add(temp1[i]);
        }
        return b;
    }

    public static ArrayList<Byte> startProgramm(int Slot, int size, int ProgrammReferenzNumber, boolean debug) {

        ArrayList<Byte> b = new ArrayList<>();

        b.add(ev3.opProgram_Start);

        Byte[] temp1 = ev3.LCX(Slot);

        for (int i = 0; i < temp1.length; i++) {
            b.add(temp1[i]);
        }

        Byte[] temp2 = ev3.LVX(size);
        for (int i = 0; i < temp2.length; i++) {
            b.add(temp2[i]);
        }

        Byte[] temp3 = ev3.LVX(ProgrammReferenzNumber);
        for (int i = 0; i < temp3.length; i++) {
            b.add(temp3[i]);
        }

        Byte[] temp4 = null;
        if (debug) {
            temp4 = ev3.LCX(1);
        } else {
            temp4 = ev3.LCX(0);
        }

        for (int i = 0; i < temp4.length; i++) {
            b.add(temp4[i]);
        }

        return b;


    }

    public static ArrayList<Byte> loadProgrammFiles(int Slot, String path, int size, int ProgrammReferenzNumber) {

        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opFile);
        b.add(ev3.LOAD_IMAGE);

        Byte[] temp1 = ev3.LCX(Slot);

        for (int i = 0; i < temp1.length; i++) {
            b.add(temp1[i]);
        }

        Byte[] temp2 = ev3.LCS(path);
        for (int i = 0; i < temp2.length; i++) {
            b.add(temp2[i]);
        }

        Byte[] temp3 = ev3.LVX(size);
        for (int i = 0; i < temp3.length; i++) {
            b.add(temp3[i]);
        }

        Byte[] temp4 = ev3.LVX(ProgrammReferenzNumber);
        for (int i = 0; i < temp4.length; i++) {
            b.add(temp4[i]);
        }

        return b;


    }

    public static ArrayList<Byte> newWaitTimer(int TimetoWait, int TimerReferezNumber) {
        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opTimer_Wait);

        Byte[] temp1 = ev3.LCX(TimetoWait);

        for (int i = 0; i < temp1.length; i++) {
            b.add(temp1[i]);
        }

        Byte[] temp2 = ev3.LVX(TimerReferezNumber);

        for (int i = 0; i < temp2.length; i++) {
            b.add(temp2[i]);
        }


        return b;
    }

    public static ArrayList<Byte> waitforTimerFinish(int TimerReferezNumber) {
        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opTimer_Ready);


        Byte[] temp2 = ev3.LVX(TimerReferezNumber);

        for (int i = 0; i < temp2.length; i++) {
            b.add(temp2[i]);
        }


        return b;
    }

    public static ArrayList<Byte> blockscreen(boolean block) {
        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opUI_Write);
        b.add(ev3.SCREEN_BLOCK);
        Byte[] temp;
        if (block) {
            temp = ev3.LCX(1);

        } else {
            temp = ev3.LCX(0);
        }
        for (int i = 0; i < temp.length; i++) {
            b.add(temp[i]);
        }

        return b;
    }

    public static ArrayList<Byte> blockbackbutton(boolean disable) {
        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opUI_Button);
        b.add(ev3.SET_BACK_BLOCK);
        Byte[] temp;
        if (disable) {
            temp = ev3.LCX(1);

        } else {
            temp = ev3.LCX(0);
        }
        for (int i = 0; i < temp.length; i++) {
            b.add(temp[i]);
        }

        return b;
    }


//TODO doens't work
    public static ArrayList<Byte> showTextBox(int x,int y,int w,int h,String string,Byte delimiter) {
        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opUI_draw);
        b.add(ev3.TEXTBOX);
        b.addAll(Arrays.asList(ev3.LCX(x)));
        b.addAll(Arrays.asList(ev3.LCX(y)));
        b.addAll(Arrays.asList(ev3.LCX(x+w)));
        b.addAll(Arrays.asList(ev3.LCX(y+h)));
        b.addAll(Arrays.asList(ev3.LCS(string+new String(new byte[]{0x00}))));
        b.addAll(Arrays.asList(ev3.LCX(string.length()+1)));
        b.add(delimiter);



        return b;
    }






    public static ArrayList<Byte> releaseKey(Byte Key) {
        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opUI_Button);
        b.add(ev3.RELEASE);
        b.add(Key);
        return b;
    }

    public static ArrayList<Byte> waitforbuttonpress() {
        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opUI_Button);
        b.add(ev3.WAIT_FOR_PRESS);

        return b;
    }

    public static ArrayList<Byte> pressKey(Byte Key) {
        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opUI_Button);
        b.add(ev3.PRESS);
        b.add(Key);
        return b;
    }


    public static ArrayList<Byte> getblockbackbutton() {
        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opUI_Write);
        b.add(ev3.GET_BACK_BLOCK);

        return b;
    }

    public static ArrayList<Byte> drawline(boolean white, int xstart, int ystart, int xend, int yend) {

        ArrayList<Byte> b = new ArrayList<>();
        b.clear();

        if (ystart > 127) {
            return null;
        }

        b.add(ev3.opUI_draw);
        b.add(ev3.LINE);
        Byte[] temp;

        if (white) {
            temp = ev3.LCX(0);
        } else {

            temp = ev3.LCX(1);
        }

        for (int i = 0; i < temp.length; i++) {
            b.add(temp[i]);
        }

        Byte[] temp2 = ev3.LCX(xstart);

        for (int i = 0; i < temp2.length; i++) {
            b.add(temp2[i]);
        }

        Byte[] temp3 = ev3.LCX(ystart);

        for (int i = 0; i < temp3.length; i++) {
            b.add(temp3[i]);
        }

        Byte[] temp4 = ev3.LCX(xend);

        for (int i = 0; i < temp4.length; i++) {
            b.add(temp4[i]);
        }

        Byte[] temp5 = ev3.LCX(yend);

        for (int i = 0; i < temp5.length; i++) {
            b.add(temp5[i]);
        }

        return b;


    }

    /**
     * @param white
     * @param ystart must be smaller than 127
     * @param ysize
     * @return
     */
    public static ArrayList<Byte> fillwindow(boolean white, int ystart, int ysize) {

        ArrayList<Byte> b = new ArrayList<>();
        b.clear();

        if (ystart > 127) {
            return null;
        }

        b.add(ev3.opUI_draw);
        b.add(ev3.FILLWINDOW);
        Byte[] temp;

        if (white) {
            temp = ev3.LCX(0);
        } else {

            temp = ev3.LCX(1);
        }

        for (int i = 0; i < temp.length; i++) {
            b.add(temp[i]);
        }

        Byte[] temp2 = ev3.LCX(ystart);

        for (int i = 0; i < temp2.length; i++) {
            b.add(temp2[i]);
        }

        Byte[] temp3 = ev3.LCX(ysize);

        for (int i = 0; i < temp3.length; i++) {
            b.add(temp3[i]);
        }

        System.out.println("Test" + b);

        return b;


    }

    public static ArrayList<Byte> updateev3screen() {
        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opUI_draw);
        b.add(ev3.UPDATE);

        return b;
    }


    public static ArrayList<Byte> ev3statusline(boolean enable) {
        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opUI_draw);
        b.add(ev3.TOPLINE);
        Byte[] temp;
        if (enable) {
            temp = ev3.LCX(1);

        } else {
            temp = ev3.LCX(0);
        }
        for (int i = 0; i < temp.length; i++) {
            b.add(temp[i]);
        }

        return b;
    }

    public static ArrayList<Byte> displayBMPFile(boolean white, int X, int Y, String name) {
        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opUI_draw);

        b.add(ev3.BMPFile);

        Byte[] temp;


        if (white) { //Setzen der Farbe
            temp = ev3.LCX(0); //Da EV3 White 0 bedeutet

        } else {
            temp = ev3.LCX(1);
        }

        for (int i = 0; i < temp.length; i++) {
            b.add(temp[i]);
        }

        /////////////////////////X/////////
        Byte[] temp2;
        temp2 = ev3.LCX(X);


        for (int i = 0; i < temp2.length; i++) {
            b.add(temp2[i]);
        }

/////////////////////////Y/////////

        Byte[] temp3;
        temp3 = ev3.LCX(Y);


        for (int i = 0; i < temp3.length; i++) {
            b.add(temp3[i]);
        }


        Byte[] temp4;
        temp4 = ev3.LCS(name);

        for (int i = 0; i < temp4.length; i++) {
            b.add(temp4[i]);
        }


        return b;
    }


    public static ArrayList<Byte> led(int Color, boolean flash, boolean flash_two_times) { //GREEN =0 RED = 1; ORANGE =2

        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opUI_Write);
        b.add(ev3.LED);

        if (flash) {

            if (flash_two_times) {
                if (Color == 0) {
                    b.add(ev3.GREEN_FLASH_TWO_TIMES);
                }
                if (Color == 1) {
                    b.add(ev3.RED_FLASH_TWO_TIMES);
                }
                if (Color == 2) {
                    b.add(ev3.ORANGE_FLASH_TWO_TIMES);
                }
            } else {
                if (Color == 0) {
                    b.add(ev3.GREEN_FLASH);
                }
                if (Color == 1) {
                    b.add(ev3.RED_FLASH);
                }
                if (Color == 2) {
                    b.add(ev3.ORANGE_FLASH);
                }
            }


        } else {
            if (Color == 0) {
                b.add(ev3.GREEN);
            }
            if (Color == 1) {
                b.add(ev3.RED);
            }
            if (Color == 2) {
                b.add(ev3.ORANGE);
            }

        }


        return b;

    }

    public static ArrayList<Byte> playSound(String soundfile, int Volume, boolean repeat) {
        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opSound);

        if (!repeat) {
            b.add(ev3.PLAY);
        } else {
            b.add(ev3.REPEAT);
        }
        Byte[] temp2 = ev3.LCX(Volume);

        for (int i = 0; i < temp2.length; i++) {
            b.add(temp2[i]);
        }


        Byte[] temp = ev3.LCS(soundfile);
        for (int i = 0; i < temp.length; i++) {
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

    public static ArrayList<Byte> playTone(int Volume, int Frequency, int duration) {
        ArrayList<Byte> b = new ArrayList<>();
        b.add(ev3.opSound);
        b.add(ev3.TONE);
        Byte[] temp = ev3.LCX(Volume);
        for (int i = 0; i < temp.length; i++) {
            b.add(temp[i]);
        }

        Byte[] temp2 = ev3.LCX(Frequency);
        for (int i = 0; i < temp2.length; i++) {
            b.add(temp2[i]);
        }

        Byte[] temp3 = ev3.LCX(duration); //IN MS
        for (int i = 0; i < temp3.length; i++) {
            b.add(temp3[i]);
        }

        return b;
    }


    public static ArrayList<Byte> setbrickname(String name) {

        ArrayList<Byte> operations = new ArrayList<>();


        operations.add(ev3.opCom_Set);
        operations.add(ev3.SET_BRICKNAME);
        Byte[] temp = ev3.LCS(name);

        for (int i = 0; i < temp.length; i++) {
            operations.add(temp[i]);
        }

        return operations;


    }

 public static ArrayList<Byte> getBrickname() {

        ArrayList<Byte> operations = new ArrayList<>();


        operations.add(ev3.opCom_Get);
        operations.add(ev3.GET_BRICKNAME);
        Byte[] temp = ev3.LCX(1);

        for (int i = 0; i < temp.length; i++) {
           operations.add(temp[i]);
        }

        return operations;


    }


}
