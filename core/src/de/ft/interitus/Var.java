package de.ft.interitus;

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
    public static ProjectTypes actProjekt = null;
    public static boolean disableshortcuts=false;




}
