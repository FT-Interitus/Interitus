/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window;
import com.badlogic.gdx.math.Vector2;
import de.ft.interitus.UI.window.Window;
import de.ft.interitus.projecttypes.ProjectVar;
import de.ft.interitus.utils.ArrayList;

public class Var {


    public static final double PROGRAMM_VERSION_ID = 1;
    public static final int PLUGIN_SUBSYSTEM_VERSION = 1;


    /**
     * Erklärung
     * Änderung an der letzen Zahl bei kleineren Änderungen (Bugfixes, Typos, ...)
     * an der zweiten Zahl bei neuen Featurs,..
     * an der Erste Zahl bei größern Update Packeten
     */
    public static final String PROGRAMM_VERSION = "0.0.1";


    public static boolean isloading = false;
    public static boolean isclearing = false;
    public static boolean nointernetconnection = false;

    public static boolean showreleasenotes = false;


    public static final ArrayList<String> programmarguments = new ArrayList<>();


    public static boolean debug = false;

    public static int w = 1300;
    public static int h = 800;
    public static String logname = "";
    public static String lognamefile = "";

    public static boolean disableshortcuts = false;
    public static String username = "";
    public static boolean disablePluginSubSystem = false;
    public static boolean savemode = false;
    public static boolean disableprogrammnotclosedwarniung = false;

    public static ArrayList<ProjectVar> openprojects = new ArrayList<>();

    public static int openprojectindex = 0;
    public static boolean keeplog = false;


    public static final Vector2 mousepressedold = new Vector2(1, 1); //Wo war die Maus als das letzte mal eine Taste gedrückt wurde

    public static final Vector2 mousepressedoldwihoutunproject = new Vector2(1, 1); //Wo war die Maus als das letzte mal eine Taste gedrückt wurde


    public static ArrayList<Window> extendsWindows = new ArrayList<>();
    public static Window splashscreen = null;

    public static boolean updatepermissionlock = false;
    public static Lwjgl3Window window = null;
}
