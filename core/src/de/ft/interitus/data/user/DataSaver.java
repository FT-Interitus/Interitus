package de.ft.interitus.data.user;

import com.badlogic.gdx.files.FileHandle;
import de.ft.interitus.Block.BlockVar;
import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.Var;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataSaver {
    static public void save(final FileHandle handle) {

        Thread speichern = new Thread() {
            public void run() {


                ArrayList<SaveBlock> saveBlocks = new ArrayList<>();

                for(int i=0;i< BlockVar.blocks.size();i++) {
                saveBlocks.add(    BlockVar.blocks.get(i).getBlocktoSaveGenerator().generate(BlockVar.blocks.get(i)));
                }


                try (FileOutputStream fos = new FileOutputStream (handle.file());
                     ObjectOutputStream oos = new ObjectOutputStream (fos)) {
                    oos.writeObject (saveBlocks);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        };


        speichern.start();

    }
}
