package de.ft.robocontrol.data.user;

import com.badlogic.gdx.files.FileHandle;
import de.ft.robocontrol.Block.BlockVar;
import de.ft.robocontrol.MainGame;
import org.json.JSONObject;

public class DataSaver {
    static public void save(final FileHandle handle) {

        Thread speichern = new Thread(){
            public void run(){

                JSONObject obj = new JSONObject();
                JSONObject blocks = new JSONObject(obj);
                JSONObject[] attributes = new JSONObject[BlockVar.blocks.size()];



                for(int i=0; i<BlockVar.blocks.size(); i++) {


                    attributes[i] = new JSONObject();

                    attributes[i].put("x", BlockVar.blocks.get(i).getX());
                    attributes[i].put("y", BlockVar.blocks.get(i).getY());
                    attributes[i].put("w", BlockVar.blocks.get(i).getW());
                    attributes[i].put("h", BlockVar.blocks.get(i).getH());
                    attributes[i].put("index", BlockVar.blocks.get(i).getIndex());


                    int[] temp;
                    temp = new int[]{-1, -1};
                    try {
                        temp[0] = BlockVar.blocks.get(i).getLeft().getIndex();
                    }catch (NullPointerException e) {

                    }

                    try {
                        temp[1] = BlockVar.blocks.get(i).getRight().getIndex();
                    }catch (NullPointerException e) {


                    }

                    attributes[i].put("nachbar_rechts", temp[1]);
                    attributes[i].put("nachbar_links", temp[0]);
                    blocks.put("Block"+i, attributes[i]);






                }




                //obj.put("Blocks", BlockVar.blocks);





                obj.put("Blocks", blocks);
              //  System.out.println(obj);


                handle.writeString(obj.toString(),false);
                //System.out.println(BlockVar.blocks.get(1).getLeft());

            }
        };



        speichern.start();

    }
}
