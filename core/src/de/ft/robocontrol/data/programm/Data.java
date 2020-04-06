package de.ft.robocontrol.data.programm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import de.ft.robocontrol.MainGame;
import de.ft.robocontrol.Settings;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;

public class Data {
    public static ArrayList<String> path = new ArrayList<String>();
    public static ArrayList<String> filename = new ArrayList<String>();


    public static void init() {

        File folder = new File(System.getProperty("user.home") + "/.racd");
        File recent = new File(System.getProperty("user.home") + "/.racd/recent.json");
        File settings = new File(System.getProperty("user.home") + "/.racd/settings.json");
        File knowndevices = new File(System.getProperty("user.home") + "/.racd/devices.json");
        Path path = folder.toPath();
        if (!folder.exists()) {
            MainGame.logger.config("Create Programm Data Folder");

            folder.mkdir();
            try {
                recent.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                settings.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                knowndevices.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Files.setAttribute(path, "dos:hidden", true);
            } catch (IOException e) {
            }
        } else {


            if (!recent.exists()) {

                try {
                    recent.createNewFile();
                    Gdx.files.absolute(recent.getAbsolutePath()).writeString("{}", false);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else {
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


                } catch (JSONException e) {

                }
            }


            if (!settings.exists()) {
                try {
                    settings.createNewFile();
                    Gdx.files.absolute(settings.getAbsolutePath()).writeString("{}", false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                try {
                    FileHandle se = Gdx.files.absolute(System.getProperty("user.home") + "/.racd/settings.json");
                    if (se.readString() == "") {
                        se.writeString("{}", false);
                        return;
                    }
                    JSONObject obj = new JSONObject(se.readString());

                    Settings.darkmode = obj.getBoolean("dark");
                    Settings.updateurl = obj.getString("updateurl");

                    //TODO weitere einstellugen Laden

                } catch (JSONException e) {

                }


            }


            if (!knowndevices.exists()) {
                try {
                    knowndevices.createNewFile();
                    Gdx.files.absolute(settings.getAbsolutePath()).writeString("{}", false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                try {
                    FileHandle se = Gdx.files.absolute(System.getProperty("user.home") + "/.racd/devices.json");
                    if (se.readString() == "") {
                        se.writeString("{}", false);
                        return;
                    }
                    JSONObject obj = new JSONObject(se.readString());

                   //////////// *.* = obj.getInt();/////////////

                    //TODO device laden mit attributen

                } catch (JSONException e) {

                }


            }



        }


        //for recent/////////////////////////////////////////////////////////


        //////////////////////////////////////////////////////////////////7


    }


    public static void close() {

        //for recent////////////////////////////////
        FileHandle recent = Gdx.files.absolute(System.getProperty("user.home") + "/.racd/recent.json");
        JSONObject recent_obj = new JSONObject(recent);
        for (int i = 0; i < Data.path.size(); i++) {
            recent_obj.put("path" + i, Data.path.get(i));
            recent_obj.put("filename" + i, Data.filename.get(i));
        }


        recent.writeString(recent.toString(), false);
        /////////////////////////////////////////////////////////////////////


        FileHandle settings = Gdx.files.absolute(System.getProperty("user.home") + "/.racd/settings.json");
        JSONObject settings_obj = new JSONObject(settings);
        settings_obj.put("dark", Settings.darkmode);
        settings_obj.put("updateurl", Settings.updateurl);

        //TODO weitere Einstellugen speichern
        settings.writeString(settings_obj.toString(), false);

        ////////////////////////////////////////////////////////////////////

        FileHandle knowndevices = Gdx.files.absolute(System.getProperty("user.home") + "/.racd/devices.json");
        JSONObject knowndevices_obj = new JSONObject(settings);

      ///  knowndevices_obj.put("",Variable);///

        //TODO attribute laden
        knowndevices.writeString(knowndevices_obj.toString(), false);

    }


}
