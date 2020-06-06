package de.ft.interitus.data.user;

import com.badlogic.gdx.files.FileHandle;
import de.ft.interitus.Block.BlockVar;
import org.json.JSONObject;

public class DataSaver {
    static public void save(final FileHandle handle) {

        Thread speichern = new Thread() {
            public void run() {
                try {

                                       //System.out.println(BlockVar.blocks.get(1).getLeft());

                } catch (Exception e) {
                    e.printStackTrace(); //for debug to find errors
                }
            }
        };


        speichern.start();

    }
}
