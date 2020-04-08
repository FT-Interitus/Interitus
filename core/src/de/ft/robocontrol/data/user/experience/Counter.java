package de.ft.robocontrol.data.user.experience;

public class Counter {
    public static double startthistime;

    public static void init() {

        startthistime = System.currentTimeMillis();

    }


    public static double getthistime() {
        return (System.currentTimeMillis()-startthistime)/3600000;
    }
}
