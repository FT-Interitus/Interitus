/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI_old;

import com.badlogic.gdx.math.Vector3;

public class UIVar {
    public static final int rechtseinraste = 300;
    public static final int untenkante = 125;
    public static final int BlockHeight = 70;
    public static int abstandvonRand = 5;
    public static final int parameter_height = 30;
    public static final int parameter_width = 30;
    public static int untenhohe = 125;
    public static int unteneinteilung = 300;
    public static int radius = 3;
    public static int BlockBarX = 0;
    public static int BlockBarY = 0;
    public static int BlockBarW = 0;
    public static int BlockBarH = 0;
    public static int programmflaeche_y;
    public static int programmflaeche_h = 0;


    public static int buttonbarzeile_h = 20;

    public static int blockeinstellungen_x = 0;
    public static int blockeinstellungen_y = 0;
    public static int blockeinstellungen_w = 0;
    public static int blockeinstellungen_h = 0;
    public static int abstandzwischenparametern = 10;
    public static int abstandText = 5;
    public static boolean isBlockSettingsopen = false;


    public static boolean isdialogeopend = false;
    public static boolean uilocked = false;
    public static boolean moveprogrammlock = false; //To not move the Programm when you Move the Cursor in a Textfield

    //////DataWires///
    public static final int first_curve_margin=30;
    public static final int thickness=3;
    public static int DataWire_horizontal_Y=0;
    public static int DataWire_InputVertikale_X=0;
    public static int DataWire_OutputVertikale_X=0;
    public static int DataWire_InputHorizontale_Y=0;
    public static int DataWire_OutputHorizontale_Y=0;
    public static int[][] DataWire = new int[8][2];
    public static final int DataWireMouseKollisionsFeld=3;
    public static Vector3 merkpos=new Vector3();
    public static boolean doonce=true;
}
