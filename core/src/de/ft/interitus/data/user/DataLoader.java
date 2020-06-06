package de.ft.interitus.data.user;

import com.badlogic.gdx.files.FileHandle;
import de.ft.interitus.Block.BlockVar;
import de.ft.interitus.Var;
import de.ft.interitus.utils.ClearActOpenProgramm;
import org.json.JSONObject;

public class DataLoader {
    public static void load(final FileHandle handle) {


        Thread laden = new Thread() {
            public void run() {

                ClearActOpenProgramm.clear();






                Var.isloading = false;

            }

        };

        Var.isloading = true;
        laden.start();


    }
}
