package de.ft.robocontrol.Block;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class GlobalBlockVar {
    public static Block markedblock = null;
    public static Vector2 mousepressedold=new Vector2(1,1);

    public static Vector2 unterschiedsave;

    public static boolean ismoving=false;

    public static boolean marked=false;



    public static ArrayList<Block> showduplicat = new ArrayList<Block>();

    public static Block biggestblock=null;


}
