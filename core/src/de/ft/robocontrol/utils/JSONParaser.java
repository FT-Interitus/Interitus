package de.ft.robocontrol.utils;
import com.badlogic.gdx.files.FileHandle;
import de.ft.robocontrol.Block;
import de.ft.robocontrol.MainGame;
import jdk.jfr.internal.LogLevel;
import jdk.jfr.internal.LogTag;
import jdk.jfr.internal.Logger;
import jdk.tools.jaotc.Main;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.util.Map;

import static java.sql.Types.NULL;

/***
 * Outputmode:
 *  1: String
 *  2: int
 *  3: boolean
 *  4: float
 *
 */
public class JSONParaser {
static public Object reader(String object, String getAttribute, int Outputmode, FileHandle handle) {

    JSONObject obj = new JSONObject(handle.readString());

    if(Outputmode==1) {
       return obj.getJSONObject(object).getString(getAttribute);
    }else if(Outputmode==2) {
        return obj.getJSONObject(object).getInt(getAttribute);
    }else if(Outputmode==3) {
        return obj.getJSONObject(object).getBoolean(getAttribute);
    }else if(Outputmode==4) {
        return obj.getJSONObject(object).getFloat(getAttribute);
    }else{
        Logger.log(LogTag.JFR, LogLevel.ERROR,"Fehler bei der Output angabe");
        return null;

    }



}


static public void writer(String object, String setAttribute, FileHandle handle) {
    JSONObject obj = new JSONObject(handle.readString());



    System.out.println(obj);


}

    static public void writerarray(final FileHandle handle) {

        Thread speichern = new Thread(){
            public void run(){

                JSONObject obj = new JSONObject();
                JSONObject blocks = new JSONObject(obj);
                JSONObject[] attributes = new JSONObject[MainGame.blocks.size()];



                for(int i=0; i<MainGame.blocks.size(); i++) {


                    attributes[i] = new JSONObject();

                    attributes[i].put("x", MainGame.blocks.get(i).getX());
                    attributes[i].put("y", MainGame.blocks.get(i).getY());
                    attributes[i].put("w", MainGame.blocks.get(i).getW());
                    attributes[i].put("h", MainGame.blocks.get(i).getH());
                    attributes[i].put("index", MainGame.blocks.get(i).getIndex());


                    int[] temp;
                    temp = new int[]{-1, -1};
                    try {
                        temp[0] = MainGame.blocks.get(i).getLeft().getIndex();
                    }catch (NullPointerException e) {

                    }

                    try {
                        temp[1] = MainGame.blocks.get(i).getRight().getIndex();
                    }catch (NullPointerException e) {


                    }

                    attributes[i].put("nachbar_rechts", temp[1]);
                    attributes[i].put("nachbar_links", temp[0]);
                    blocks.put("Block"+i, attributes[i]);






                }




                //obj.put("Blocks", MainGame.blocks);





                obj.put("Blocks", blocks);
                System.out.println(obj);


                handle.writeString(obj.toString(),false);
                System.out.println(MainGame.blocks.get(1).getLeft());

            }
        };



speichern.start();

    }


    static public void writer(String object, int setAttribute, FileHandle handle) {
        JSONObject obj = new JSONObject(handle.readString());


    }


    static public void writer(String object, float setAttribute, FileHandle handle) {
        JSONObject obj = new JSONObject(handle.readString());


    }

    static public void writer(String object, boolean setAttribute, FileHandle handle) {
        JSONObject obj = new JSONObject(handle.readString());


    }


    public static void load(final FileHandle handle) {



        Thread laden = new Thread(){
            public void run(){




                for(int i=0;i<MainGame.blocks.size();i=i+1){
                    MainGame.blocks.get(i).delete();
                }
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
                        System.out.println("nachbar gesetzt rechts  "+MainGame.blocks.get(a).getRight().getIndex());
                    }
                    if(obj.getJSONObject("Blocks").getJSONObject("Block"+a).getInt("nachbar_links")!=-1){
                        MainGame.blocks.get(a).setLeft(MainGame.blocks.get(obj.getJSONObject("Blocks").getJSONObject("Block"+a).getInt("nachbar_links")));
                        System.out.println("nachbar gesetzt links "+MainGame.blocks.get(a).getLeft().getIndex());
                    }
                }




            }
        };


laden.start();


    }
}
