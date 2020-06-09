package de.ft.interitus;

import de.ft.interitus.UI.Theme.Theme;
import de.ft.interitus.UI.Theme.interitus.WhiteMode;

public class Settings {
    public static Theme theme = null;
    public static String updateurl = "https://raw.githubusercontent.com/Coder246/roboupdater/master/version"; //TODO final die UpdateURL
    public static String defaultpfad = System.getProperty("user.home") + "/Interitus-Projects/";
    public static boolean Vsync = true;
    public static int limitfps = 0;


}
