package de.ft.robocontrol.utils;
import com.badlogic.gdx.files.FileHandle;
import de.ft.robocontrol.MainGame;
import jdk.jfr.internal.LogLevel;
import jdk.jfr.internal.LogTag;
import jdk.jfr.internal.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

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

    static public void writerarray(FileHandle handle) {
        JSONObject obj = new JSONObject(handle.readString());
        JSONObject test = new JSONObject(obj);

        for(int i=0; i<MainGame.blocks.size(); i++) {

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



            test.put("Block"+i, temp);




        }




        obj.put("Blocks", MainGame.blocks);





        obj.put("blocknachbar", test);
        System.out.println(obj);
        System.out.println(MainGame.blocks.get(1).getLeft());


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


}
