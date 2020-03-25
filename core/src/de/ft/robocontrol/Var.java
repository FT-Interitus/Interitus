package de.ft.robocontrol;

import com.badlogic.gdx.math.Vector2;

public class Var {

    public static double GAME_VERSION = 1.0;
    public static boolean indraganddrop=false;
    public static Block markedblock = null;
    public static Vector2 mousepressedold=new Vector2(1,1);

    public static Vector2 unterschiedsave;

    public static boolean ismoving=false;

    public static boolean marked=false;

    public static boolean isloading=false;


    public static volatile String path = "";
}
