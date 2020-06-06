package de.ft.interitus.data.user;

import com.badlogic.gdx.files.FileHandle;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockVar;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DataSaver {
    static public void save(final FileHandle handle) {

        Thread speichern = new Thread() {
            public void run() {
                Block zuspeichernderblock = BlockVar.blocks.get(0);
                try (FileOutputStream fos = new FileOutputStream (System.getProperty("user.home")+"/dummy.ser");
                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject (BlockVar.blocks);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        speichern.start();

    }
}
