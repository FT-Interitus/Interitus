package de.ft.robocontrol.Block;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class BlockVar {

    public static ArrayList<Block> blocks = new ArrayList<Block>();



    //TODO Für multi select hier eventuel ArrayList
    public static Block markedblock = null;

    public static Vector2 mousepressedold=new Vector2(1,1);

    public static Vector2 unterschiedsave;

    public static boolean ismoving=false;

    public static boolean marked=false;

    public static ArrayList<Block> uberlapptmitmarkedblock = new ArrayList<Block>();

    public static ArrayList<Block> showduplicat = new ArrayList<Block>();

    public static Block biggestblock=null;

    public static Block blockmitdergrostenuberlappungmitmarkiertemblock=null;




}
