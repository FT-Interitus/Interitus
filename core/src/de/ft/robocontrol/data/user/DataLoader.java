package de.ft.robocontrol.data.user;

import com.badlogic.gdx.files.FileHandle;
import de.ft.robocontrol.Block.Block;
import de.ft.robocontrol.Block.BlockVar;
import de.ft.robocontrol.Var;
import de.ft.robocontrol.utils.ClearActOpenProgramm;
import org.json.JSONObject;

public class DataLoader {
    public static void load(final FileHandle handle) {


        Thread laden = new Thread() {
            public void run() {

                ClearActOpenProgramm.clear();

                JSONObject obj = new JSONObject(handle.readString());
                boolean goout = false;
                int i = 0;
                while (!goout) {
                    if (!obj.getJSONObject("Blocks").has("Block" + i)) { //Durch alle gefunden Blöcke durchgehen
                        goout = true;
                    } else {

                        BlockVar.blocks.add(null);
                        BlockVar.blocks.set(i, new Block(obj.getJSONObject("Blocks").getJSONObject("Block" + i).getInt("index"), obj.getJSONObject("Blocks").getJSONObject("Block" + i).getInt("x"), obj.getJSONObject("Blocks").getJSONObject("Block" + i).getInt("y"), obj.getJSONObject("Blocks").getJSONObject("Block" + i).getInt("w"), obj.getJSONObject("Blocks").getJSONObject("Block" + i).getInt("h")));
                    //Und Paramenter wieder laden
                    }

                    i++;
                }

                for (int a = 0; a < BlockVar.blocks.size(); a++) {
                    if (obj.getJSONObject("Blocks").getJSONObject("Block" + a).getInt("nachbar_rechts") != -1) { //Nachbaren wieder anhand der IDs verknüpfen
                       // BlockVar.blocks.get(a).setRight(BlockVar.blocks.get(obj.getJSONObject("Blocks").getJSONObject("Block" + a).getInt("nachbar_rechts")));
                        //System.out.println("nachbar gesetzt rechts  "+BlockVar.blocks.get(a).getRight().getIndex());
                    }
                    if (obj.getJSONObject("Blocks").getJSONObject("Block" + a).getInt("nachbar_links") != -1) {
                     //   BlockVar.blocks.get(a).setLeft(BlockVar.blocks.get(obj.getJSONObject("Blocks").getJSONObject("Block" + a).getInt("nachbar_links")));
                        //System.out.println("nachbar gesetzt links "+BlockVar.blocks.get(a).getLeft().getIndex());
                    }
                }


                Var.isloading = false;

            }

        };

        Var.isloading = true;
        laden.start();


    }
}
