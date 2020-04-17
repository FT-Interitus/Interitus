package de.ft.interitus.utils;

import com.badlogic.gdx.files.FileHandle;
import org.json.JSONObject;

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

        if (Outputmode == 1) {
            return obj.getJSONObject(object).getString(getAttribute);
        } else if (Outputmode == 2) {
            return obj.getJSONObject(object).getInt(getAttribute);
        } else if (Outputmode == 3) {
            return obj.getJSONObject(object).getBoolean(getAttribute);
        } else if (Outputmode == 4) {
            return obj.getJSONObject(object).getFloat(getAttribute);
        } else {

            return null;

        }


    }


    static public void writer(String object, String setAttribute, FileHandle handle) {
        JSONObject obj = new JSONObject(handle.readString());


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
