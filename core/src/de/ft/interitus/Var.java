package de.ft.interitus;

import com.badlogic.gdx.math.Vector2;
import de.ft.interitus.Block.BlockVar;
import de.ft.interitus.plugin.PluginManagerHandler;
import de.ft.interitus.projecttypes.ProjectTypes;

import java.util.ArrayList;

public class Var {

    public static PluginManagerHandler pluginManager;

    public static double PROGRAMM_VERSION_ID = 1.0;
    public static double PROGRAMM_VERSION = 1.0;
    public static double API_VERSION = 1.0;
    public static boolean isloading = false;
    public static boolean isclearing = false;
    public static boolean isdialogeopend = false;

    public static boolean nointernetconnection = false;


    public static ArrayList<String> programmarguments = new ArrayList<>();

    public static boolean verboseoutput = false;

    public static int w = 1300;
    public static int h = 800;

    public static boolean disableshortcuts=false;
    public static String username = "";
    public static boolean disablePluginSubSystem = false;

    public static ArrayList<BlockVar> openprojects = new ArrayList<>();

    public static int openprojectindex = 0;


    public static Vector2 mousepressedold = new Vector2(1, 1); //Wo war die Maus als das letzte mal eine Taste gedrückt wurde
}
