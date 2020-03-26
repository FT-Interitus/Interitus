package de.ft.robocontrol.data.user;

import com.badlogic.gdx.Gdx;
import de.ft.robocontrol.MainGame;

public class DataManager {
    public static boolean changes = false;
    public static String filename = "";
    public static volatile String path = "";
    public static void change() {
        if(!changes) {
            changes = true;
            //Gdx.graphics.setTitle(filename+ "*");
        }
    }

    public static void saved() {
        changes = false;
       // Gdx.graphics.setTitle(filename);
    }
}
