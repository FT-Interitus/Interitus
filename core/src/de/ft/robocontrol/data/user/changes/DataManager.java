package de.ft.robocontrol.data.user.changes;

import com.badlogic.gdx.Gdx;
import de.ft.robocontrol.Block.Block;
import de.ft.robocontrol.MainGame;

public class DataManager {
    public static boolean changes = false;
    public static String filename = "";
    public static volatile String path = "";
    public static void change(Block block) {
        if(!changes) {
            changes = true;
            //Gdx.graphics.setTitle(filename+ "*");





        }
        SaveChanges.changedValue(block);
    }

    public static void saved() {
        changes = false;
       // Gdx.graphics.setTitle(filename);
    }
}
