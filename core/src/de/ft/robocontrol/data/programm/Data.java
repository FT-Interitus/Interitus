package de.ft.robocontrol.data.programm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import de.ft.robocontrol.Block.Block;
import de.ft.robocontrol.Settings;
import de.ft.robocontrol.UI.UI;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.FileHandler;

public class Data {
    public static ArrayList<String> path = new ArrayList<String>();
    public static ArrayList<String> filename = new ArrayList<String>();



    public static void init() {

       File folder = new File(System.getProperty("user.home")+"/.racd");
        File recent = new File(System.getProperty("user.home")+"/.racd/recent.json");
        File settings = new File(System.getProperty("user.home")+"/.racd/settings.json");
       Path path = folder.toPath();
       if(!folder.exists()) {

           folder.mkdir();
           try {
               recent.createNewFile();
           } catch (IOException e) {
               e.printStackTrace();
           }
           try { Files.setAttribute(path,"dos:hidden", true ); }
           catch (IOException e) { e.printStackTrace();}
       }else{


           if(!recent.exists()) {

           try {
               recent.createNewFile();
               Gdx.files.absolute(recent.getAbsolutePath()).writeString("{}", false);
           } catch (IOException e) {
               e.printStackTrace();
           }


       }else {
               try {

                   FileHandle re = Gdx.files.absolute(System.getProperty("user.home") + "/.racd/recent.json");

                   if (re.readString() == "") {
                       re.writeString("{}", false);
                       return;
                   }

                   JSONObject obj = new JSONObject(re.readString());

                   int i = 0;
                   while (obj.has("path" + i)) {
                       i++;
                   }


                   for (int a = 0; a < i; a++) {
                       Data.path.add(obj.getString("path" + a));
                       Data.filename.add(obj.getString("filename" + a));
                   }


                   //     System.out.println(obj.get("path"));


               }catch (JSONException e) {

               }
       }


           if(!settings.exists()) {
               try {
                   settings.createNewFile();
                   Gdx.files.absolute(settings.getAbsolutePath()).writeString("{}",false);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }else {

               try{
                   FileHandle se = Gdx.files.absolute(System.getProperty("user.home") + "/.racd/settings.json");
                   if (se.readString() == "") {
                       se.writeString("{}", false);
                       return;
                   }
                   JSONObject obj = new JSONObject(se);

                    Settings.darkmode = obj.getBoolean("dark");
                    //TODO weitere einstellugen Laden

               }catch (JSONException e) {

               }


           }
       }


           //for recent/////////////////////////////////////////////////////////





            //////////////////////////////////////////////////////////////////7



       }



    public static void close() {

        //for recent////////////////////////////////
        FileHandle recent = Gdx.files.absolute(System.getProperty("user.home")+"/.racd/recent.json");
       JSONObject recent_obj = new JSONObject( recent);
       for(int i=0;i<Data.path.size();i++) {
           recent_obj.put("path"+i,Data.path.get(i));
           recent_obj.put("filename"+i,Data.filename.get(i));
       }


       recent.writeString(recent.toString(),false);
       /////////////////////////////////////////////////////////////////////


        FileHandle settings = Gdx.files.absolute(System.getProperty("user.home")+"/.racd/settings.json");
        JSONObject settings_obj = new JSONObject(settings);
        settings_obj.put("dark", Settings.darkmode);

        //TODO weitere Einstellugen speichern
        settings.writeString(settings_obj.toString(), false);

    }




}
