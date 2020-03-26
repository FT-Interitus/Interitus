package de.ft.robocontrol.data.user;

import com.badlogic.gdx.files.FileHandle;
import de.ft.robocontrol.Block.Block;
import de.ft.robocontrol.MainGame;
import de.ft.robocontrol.Var;
import org.json.JSONObject;

public class DataLoader {
    public static void load(final FileHandle handle) {

        final Thread clear =new Thread() {
            @Override
            public void run() {
                for(int i = 0; i< MainGame.blocks.size(); i=i+1){
                    MainGame.blocks.get(i-1).delete();
                }
            }
        };


        Thread laden = new Thread(){
            public void run(){


                clear.start();


                MainGame.blocks.clear();

                JSONObject obj = new JSONObject(handle.readString());
                boolean goout=false;
                int i = 0;
                while (!goout) {
                    if(!obj.getJSONObject("Blocks").has("Block"+i)) {
                        goout = true;
                    }else{

                        MainGame.blocks.add(null);
                        MainGame.blocks.set(i,new Block(obj.getJSONObject("Blocks").getJSONObject("Block"+i).getInt("index"),obj.getJSONObject("Blocks").getJSONObject("Block"+i).getInt("x"),obj.getJSONObject("Blocks").getJSONObject("Block"+i).getInt("y"),obj.getJSONObject("Blocks").getJSONObject("Block"+i).getInt("w"),obj.getJSONObject("Blocks").getJSONObject("Block"+i).getInt("h")));

                    }

                    i++;
                }

                for(int a=0;a<MainGame.blocks.size();a++){
                    if(obj.getJSONObject("Blocks").getJSONObject("Block"+a).getInt("nachbar_rechts")!=-1){
                        MainGame.blocks.get(a).setRight(MainGame.blocks.get(obj.getJSONObject("Blocks").getJSONObject("Block"+a).getInt("nachbar_rechts")));
                        //System.out.println("nachbar gesetzt rechts  "+MainGame.blocks.get(a).getRight().getIndex());
                    }
                    if(obj.getJSONObject("Blocks").getJSONObject("Block"+a).getInt("nachbar_links")!=-1){
                        MainGame.blocks.get(a).setLeft(MainGame.blocks.get(obj.getJSONObject("Blocks").getJSONObject("Block"+a).getInt("nachbar_links")));
                        //System.out.println("nachbar gesetzt links "+MainGame.blocks.get(a).getLeft().getIndex());
                    }
                }


                Var.isloading = false;

            }

        };

        Var.isloading=true;
        laden.start();


    }
}
