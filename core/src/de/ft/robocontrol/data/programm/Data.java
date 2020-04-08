package de.ft.robocontrol.data.programm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import de.ft.robocontrol.ProgrammingSpace;
import de.ft.robocontrol.Settings;
import de.ft.robocontrol.data.user.experience.Counter;
import de.ft.robocontrol.data.user.experience.ExperienceVar;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Data {
    //ANMERKUNG Die Programmdaten sind je nach Benutzer unterschiedlich, deswegen liegen sie auch direkt im Bentzter Ordner
    public static ArrayList<String> path = new ArrayList<String>(); //Die Pfade der zuletzt geöffneten Projekten
    public static ArrayList<String> filename = new ArrayList<String>(); //Die Namen der Dateien die zuletzt geöffnet wurden
    private static File folder; //Der Ordner in dem alle Programm daten liegen

    public static void init() {

         folder = new File(System.getProperty("user.home") + "/.racd"); //Order der Programmdaten
        File recent = new File(System.getProperty("user.home") + "/.racd/recent.json"); //JSON file in dem die zuletzt geöffneten Projekte gespeichert werden
        File settings = new File(System.getProperty("user.home") + "/.racd/settings.json"); // JSON file in dem alle Einstellungen gespeichert werden
        File knowndevices = new File(System.getProperty("user.home") + "/.racd/devices.json"); //JSON file in dem alle konfigurierten Geräte gespeichert werden
        File userexperience = new File(System.getProperty("user.home") + "/.racd/experience.json"); //JSON file in dem User Analytics gespeichert werden
        Path path = folder.toPath();
        if (!folder.exists()) {//Wenn der Programm-Ordner noch nicht exsitiert
            ProgrammingSpace.logger.config("Create Programm Data Folder");

            folder.mkdir(); //der Ordner wird erstellt
            try {
                recent.createNewFile(); //Die datei für die letzten Projekte wird erstellt
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                settings.createNewFile();//Die datei für die Einstellungen wird erstellt
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                knowndevices.createNewFile();//Die datei für die bekannten Geröte wird erstellt
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                userexperience.createNewFile();//Die datei für die bekannten Geröte wird erstellt
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Files.setAttribute(path, "dos:hidden", true); //Auf Windows wird das Verzeichnis unsichtbar gemacht (Auf Linux reicht ja schon der punkt davor)
            } catch (IOException e) {
            }
        } else {


            if (!recent.exists()) { //Wenn die Datei der letzten Projekte noch nicht exsisiert

                try {
                    recent.createNewFile();
                    Gdx.files.absolute(recent.getAbsolutePath()).writeString("{}", false); //Wird in das Verzeichnis mit {} als JSON indikator geschrieben
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else { //Wenn es exsistiert
                try {

                    FileHandle re = Gdx.files.absolute(System.getProperty("user.home") + "/.racd/recent.json");

                    if (re.readString() == "") {
                        re.writeString("{}", false);
                        return;
                    }

                    JSONObject obj = new JSONObject(re.readString());

                    int i = 0;
                    while (obj.has("path" + i)) { //Es wird geschaut wie viele Einträge exsistieren (max 10)
                        i++;
                    }


                    for (int a = 0; a < i; a++) { //Wird durch alle pfade durchgegangen
                        Data.path.add(obj.getString("path" + a)); //Pfad wird zum Array hinzugefügt
                        Data.filename.add(obj.getString("filename" + a)); //Name wird zum Array hinzugefügt
                    }


                } catch (JSONException e) {

                }
            }


            if (!settings.exists()) { //siehe recent
                try {
                    settings.createNewFile();
                    Gdx.files.absolute(settings.getAbsolutePath()).writeString("{}", false); //siehe recent
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                try {
                    FileHandle se = Gdx.files.absolute(System.getProperty("user.home") + "/.racd/settings.json");  //Datei wird geladen
                    if (se.readString() == "") {
                        se.writeString("{}", false);
                        return;
                    }
                    JSONObject obj = new JSONObject(se.readString());

                    Settings.darkmode = obj.getBoolean("dark"); //Einstellungen werden je nach Daten Typ geladem
                    Settings.updateurl = obj.getString("updateurl");

                    //TODO weitere einstellugen Laden

                } catch (JSONException e) {

                }


            }


            if (!knowndevices.exists()) { //siehe recent
                try {
                    knowndevices.createNewFile();
                    Gdx.files.absolute(knowndevices.getAbsolutePath()).writeString("{}", false); //siehe recent
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

                    //Die Geräte werden geladen und für jedes eine Verbindungsspeicher Instanze erstellt

                   //////////// *.* = obj.getInt();/////////////

                    //TODO device laden mit attributen

                } catch (JSONException e) {

                }


            }


            if (!userexperience.exists()) { //siehe recent
                try {
                    userexperience.createNewFile();
                    Gdx.files.absolute(userexperience.getAbsolutePath()).writeString("{}", false); //siehe recent
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                try {
                    FileHandle se = Gdx.files.absolute(System.getProperty("user.home") + "/.racd/experience.json");
                    if (se.readString() == "") {
                        se.writeString("{}", false);
                        return;
                    }
                    JSONObject obj = new JSONObject(se.readString());

                    ExperienceVar.programmtimeinhoures = obj.getDouble("time");

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
        FileHandle recent = Gdx.files.absolute(System.getProperty("user.home") + "/.racd/recent.json"); //Lade datei
        JSONObject recent_obj = new JSONObject(recent);
        for (int i = 0; i < Data.path.size(); i++) { //Es wird durch alle Vorhanden einträge durch gegangen
            recent_obj.put("path" + i, Data.path.get(i)); //Und jedes Nacheinander abgespeichert
            recent_obj.put("filename" + i, Data.filename.get(i));
        }


        recent.writeString(recent_obj.toString(), false); //Datei wird geschrieben
        /////////////////////////////////////////////////////////////////////


        FileHandle settings = Gdx.files.absolute(System.getProperty("user.home") + "/.racd/settings.json"); //Lade Datei
        JSONObject settings_obj = new JSONObject(settings); //Einstellungen werden je nach Daten Typ abgespeichert
        settings_obj.put("dark", Settings.darkmode);
        settings_obj.put("updateurl", Settings.updateurl);

        //TODO weitere Einstellugen speichern
        settings.writeString(settings_obj.toString(), false); //Datei wird geschrieben

        ////////////////////////////////////////////////////////////////////

        FileHandle knowndevices = Gdx.files.absolute(System.getProperty("user.home") + "/.racd/devices.json");//Lade Datei
        JSONObject knowndevices_obj = new JSONObject(settings);

      ///  knowndevices_obj.put("",Variable);///

        //Jede VerbindungsSpeicher Instance wird ausgelsen und abgespeichert

        //TODO attribute laden
        knowndevices.writeString(knowndevices_obj.toString(), false); //Datei wird geschrieben

        ///////////////////////////////////////////////////////////////
        FileHandle userexperience = Gdx.files.absolute(System.getProperty("user.home") + "/.racd/experience.json"); //Lade Datei
        JSONObject userexperience_obj = new JSONObject(userexperience); //UserExperience wird je nach Daten Typ abgespeichert


        userexperience_obj.put("time", Counter.getthistime()+ExperienceVar.programmtimeinhoures);


        //TODO weitere Einstellugen speichern
        userexperience.writeString(userexperience_obj.toString(), false); //Datei wird geschrieben

///////////////////////////////////////////////////////////////////////
    }


    public static long getprogrammfoldersize() { //Die Ordner größe wird ausgelesen (benötigt für UI.settings.subitems.subitem10)

        long length = 0;
        for (int i =0;i<folder.listFiles().length;i++) { //Durch alle Dateien wird Durchgegeangen...
            if (folder.listFiles()[i].isFile())
                length += folder.listFiles()[i].length(); //Die größe der Datei wird ausgegeben
            else
                length += folder.length(); //Die eigenliche größe des Ordners wird ausgegeben
        }
        return length;

    }


    public static void delete() { //TODO Experimentel
        folder.delete(); //Der Ordner wird gelöscht

    }
}
