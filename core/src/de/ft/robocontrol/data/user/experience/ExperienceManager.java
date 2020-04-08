package de.ft.robocontrol.data.user.experience;

public class ExperienceManager {
    public static double startthistime;
    public static double settingsthistime = 0;
    public static double settingstimetemp =0;

    public static void init() {

        startthistime = System.currentTimeMillis();

        Level.checkLevel();


    }


    public static double getthistime() {
        return (System.currentTimeMillis()-startthistime)/3600000;
    }



}
