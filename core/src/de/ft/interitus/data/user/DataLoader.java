package de.ft.interitus.data.user;

import com.badlogic.gdx.files.FileHandle;
import de.ft.interitus.Block.BlockVar;
import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.device.BlockTypes.Ev3.Wait;
import de.ft.interitus.utils.ClearActOpenProgramm;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class DataLoader {
    public static void load(final FileHandle handle) {


        Thread laden = new Thread() {
            public void run() {

                ClearActOpenProgramm.clear();


                try {
                    FileInputStream fis = new FileInputStream(handle.file());
                    ObjectInputStream ois = new ObjectInputStream(fis);

                ArrayList<SaveBlock> readedblocks =    ((ArrayList<SaveBlock>) ois.readObject());

                for(int i=0;i<readedblocks.size();i++) {
                    BlockVar.blocks.add(Var.actProjekt.getBlockGenerator().generateBlock(i,readedblocks.get(i).getX(),readedblocks.get(i).getY(),150,70,new Wait(),Var.actProjekt.getBlockUpdateGenerator(),Var.actProjekt.getBlocktoSaveGenerator()));
                }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                Var.isloading = false;

            }

        };

        Var.isloading = true;
        laden.start();


    }
}
