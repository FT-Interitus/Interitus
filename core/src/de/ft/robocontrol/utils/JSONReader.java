package de.ft.robocontrol.utils;
import com.badlogic.gdx.files.FileHandle;
import jdk.jfr.internal.LogLevel;
import jdk.jfr.internal.LogTag;
import jdk.jfr.internal.Logger;
import org.json.JSONObject;

/***
 * Outputmode:
 *  1: String
 *  2: int
 *  3: boolean
 *  4: float
 *
 */
public class JSONReader {
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

}
