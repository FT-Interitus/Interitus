package de.ft.interitus.data.programm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.Settings;
import de.ft.interitus.UI.CheckShortcuts;
import de.ft.interitus.UI.settings.subitems.subitem6;
import de.ft.interitus.UI.Theme.RegisteredThemes;
import de.ft.interitus.UI.shortcut.ShortCut;
import de.ft.interitus.data.user.experience.ExperienceManager;
import de.ft.interitus.data.user.experience.ExperienceVar;
import org.json.JSONArray;
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


    /**
     * load the Programm data from the UserFolder/.itd
     * <p>
     * On Linux the Folder with a dot is default invisible
     * On Windows we need to set the file attributes
     * <p>
     * In the recent file there are the projects which were opened recently
     * In the settings file the Settings from the user are Saved
     * <p>
     * In the devices file are the whole configurated devices saved
     * In the experienced file the user level, ... is saved
     */
    public static void init() {

        folder = new File(System.getProperty("user.home") + "/.itd"); //Order der Programmdaten
        File recent = new File(System.getProperty("user.home") + "/.itd/recent.json"); //JSON file in dem die zuletzt geöffneten Projekte gespeichert werden
        File settings = new File(System.getProperty("user.home") + "/.itd/settings.json"); // JSON file in dem alle Einstellungen gespeichert werden
        File knowndevices = new File(System.getProperty("user.home") + "/.itd/devices.json"); //JSON file in dem alle konfigurierten Geräte gespeichert werden
        File userexperience = new File(System.getProperty("user.home") + "/.itd/experience.json"); //JSON file in dem User Analytics gespeichert werden
        File tastenkombinationen = new File(System.getProperty("user.home") + "/.itd/tastenkombinationen.json"); //JSON file in dem tastenkombinationen gespeichert werden
        //File defaulttastenkombinationen = new File(System.getProperty("user.home") + "/.itd/defaulttastenkombinationen.json"); //JSON file in dem defaulttastenkombinationen gespeichert werden (von user nicht veränderbar)


        Path path = folder.toPath();
        if (!folder.exists()) {//Wenn der Programm-Ordner noch nicht exsitiert
            System.out.println("Create Programm Data Folder");

            folder.mkdir(); //der Ordner wird erstellt
            try {
                recent.createNewFile(); //Die datei für die letzten Projekte wird erstellt
            } catch (IOException e) {
                e.printStackTrace();
                DisplayErrors.error = e;
            }

            try {
                settings.createNewFile();//Die datei für die Einstellungen wird erstellt
            } catch (IOException e) {
                e.printStackTrace();
                DisplayErrors.error = e;
            }

            try {
                knowndevices.createNewFile();//Die datei für die bekannten Geräte wird erstellt
            } catch (IOException e) {
                e.printStackTrace();
                DisplayErrors.error = e;
            }

            try {
                userexperience.createNewFile();//Die datei für die bekannten Geräte wird erstellt
            } catch (IOException e) {
                e.printStackTrace();
                DisplayErrors.error = e;
            }

            try {
                tastenkombinationen.createNewFile();//Die datei für die tastenkombinationen wird erstellt
            } catch (IOException e) {
                e.printStackTrace();
                DisplayErrors.error = e;
            }

            try {
                Files.setAttribute(path, "dos:hidden", true); //Auf Windows wird das Verzeichnis unsichtbar gemacht (Auf Linux reicht ja schon der punkt davor)
            } catch (UnsupportedOperationException | IOException e) {
            }
        } else {


            if (!recent.exists()) { //Wenn die Datei der letzten Projekte noch nicht exsisiert

                try {
                    recent.createNewFile();
                    Gdx.files.absolute(recent.getAbsolutePath()).writeString("{}", false); //Wird in das Verzeichnis mit {} als JSON indikator geschrieben
                } catch (IOException e) {
                    DisplayErrors.error = e;
                    e.printStackTrace();
                }


            } else { //Wenn es exsistiert
                try {

                    FileHandle re = Gdx.files.absolute(System.getProperty("user.home") + "/.itd/recent.json");

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
                    DisplayErrors.error = e;
                    e.printStackTrace();
                }
            } else {

                try {
                    FileHandle se = Gdx.files.absolute(System.getProperty("user.home") + "/.itd/settings.json");  //Datei wird geladen
                    if (se.readString() == "") {
                        se.writeString("{}", false);
                        return;
                    }
                    JSONObject obj = new JSONObject(se.readString());


                    try {
                        for (int i = 0; i < RegisteredThemes.themes.size(); i++) {


                            if (RegisteredThemes.themes.get(i).getName().contains(obj.getString("theme"))) {
                                Settings.theme = RegisteredThemes.themes.get(i);
                                break;
                            }
                        }
                    }catch (JSONException e) {
                        Settings.theme = RegisteredThemes.themes.get(0); //Load White Mode if Theme Attribut is unavailable
                    }

                    if(Settings.theme==null)  {
                        Settings.theme = RegisteredThemes.themes.get(0); //Load White Mode if Theme is unavailable
                    }

                    Settings.updateurl = obj.getString("updateurl");
                    Settings.defaultpfad = obj.getString("defaultpath");

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
                    FileHandle se = Gdx.files.absolute(System.getProperty("user.home") + "/.itd/devices.json");
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
                    FileHandle se = Gdx.files.absolute(System.getProperty("user.home") + "/.itd/experience.json");
                    if (se.readString() == "") {
                        se.writeString("{}", false);
                        return;
                    }
                    JSONObject obj = new JSONObject(se.readString());

                    ExperienceVar.programmtimeinhoures = obj.getDouble("time");
                    ExperienceVar.newprojects = obj.getInt("newprojects");
                    ExperienceVar.userlevel = obj.getInt("userlevel");
                    ExperienceVar.settingstimeinhoures = obj.getDouble("settingstime");
                    ExperienceVar.setuptimeinhoures = obj.getDouble("setuptime");
                    ExperienceVar.starttimes = obj.getInt("starttimes");
                    //////////// *.* = obj.getInt();/////////////

                    //TODO device laden mit attributen

                } catch (JSONException e) {

                }


            }

            if (!tastenkombinationen.exists()) { //siehe recent
                try {
                    tastenkombinationen.createNewFile();
                    Gdx.files.absolute(tastenkombinationen.getAbsolutePath()).writeString("{}", false); //siehe recent
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                try {
                    FileHandle se = Gdx.files.absolute(System.getProperty("user.home") + "/.itd/tastenkombinationen.json");
                    if (se.readString() == "") {
                        se.writeString("{}", false);
                        return;
                    }
                    JSONObject obj = new JSONObject(se.readString());

                    //TODO Tastenkombinations Datei Lesen:
                    ////////////////////////////////////////////////////////////////////////////////////////////////////
                    int i = 0;
                    while (obj.has("tastenkombination" + i)) { //Es wird geschaut wie viele Einträge exsistieren
                        i++;
                    }

                    System.out.println("Tastenkombinationen"+i);
                    for (int a = 0; a < i; a++) { //Wird durch alle tastenkombinationen durchgegangen
                    System.out.println(obj.getJSONArray("tastenkombination"+a));
                    //CheckShortcuts.shortCuts.clear();
                    //CheckShortcuts.shortCuts.add()

                    }
                    ////////////////////////////////////////////////////////////////////////////////////////////////////



                } catch (JSONException e) {

                }


            }


        }


        //for recent/////////////////////////////////////////////////////////


        //////////////////////////////////////////////////////////////////7


    }


    public static void close() {
        //////Tastenkombinationen////////////////////////////////////
        FileHandle tastenkombinationen = Gdx.files.absolute(System.getProperty("user.home") + "/.itd/tastenkombinationen.json"); //Lade datei
        JSONObject tastenkombinationen_obj = new JSONObject(tastenkombinationen);

        for(int i=0;i< CheckShortcuts.shortCuts.size();i++){//Es wird durch alle einträge durchgegangen
            ShortCut aktualshortcut=CheckShortcuts.shortCuts.get(i);
            JSONArray array=new JSONArray();
            for(int j=0;j<aktualshortcut.getCombination().size();j++){//Es wird durch alle tasten des shortcuts durchgegangen
                array.put(aktualshortcut.getCombination().get(j));
            }
            tastenkombinationen_obj.put("tastenkombination"+i,array); //Und jedes Nacheinander abgespeichert


        }




        tastenkombinationen.writeString(tastenkombinationen_obj.toString(), false); //Datei wird geschrieben

        ////////////////////////////////////////////////////////////////////


        //for recent////////////////////////////////
        FileHandle recent = Gdx.files.absolute(System.getProperty("user.home") + "/.itd/recent.json"); //Lade datei
        JSONObject recent_obj = new JSONObject(recent);
        for (int i = 0; i < Data.path.size(); i++) { //Es wird durch alle Vorhanden einträge durch gegangen
            recent_obj.put("path" + i, Data.path.get(i)); //Und jedes Nacheinander abgespeichert
            recent_obj.put("filename" + i, Data.filename.get(i));
        }


        recent.writeString(recent_obj.toString(), false); //Datei wird geschrieben
        /////////////////////////////////////////////////////////////////////


        FileHandle settings = Gdx.files.absolute(System.getProperty("user.home") + "/.itd/settings.json"); //Lade Datei
        JSONObject settings_obj = new JSONObject(settings); //Einstellungen werden je nach Daten Typ abgespeichert
        settings_obj.put("theme", Settings.theme.getName());
        settings_obj.put("updateurl", Settings.updateurl);
        settings_obj.put("defaultpath", Settings.defaultpfad);

        //TODO weitere Einstellugen speichern
        settings.writeString(settings_obj.toString(), false); //Datei wird geschrieben

        ////////////////////////////////////////////////////////////////////

        FileHandle knowndevices = Gdx.files.absolute(System.getProperty("user.home") + "/.itd/devices.json");//Lade Datei
        JSONObject knowndevices_obj = new JSONObject(settings);

        ///  knowndevices_obj.put("",Variable);///

        //Jede VerbindungsSpeicher Instance wird ausgelsen und abgespeichert

        //TODO attribute laden
        knowndevices.writeString(knowndevices_obj.toString(), false); //Datei wird geschrieben

        ///////////////////////////////////////////////////////////////
        FileHandle userexperience = Gdx.files.absolute(System.getProperty("user.home") + "/.itd/experience.json"); //Lade Datei
        JSONObject userexperience_obj = new JSONObject(userexperience); //UserExperience wird je nach Daten Typ abgespeichert


        userexperience_obj.put("time", ExperienceManager.getthistime() + ExperienceVar.programmtimeinhoures);
        userexperience_obj.put("newprojects", ExperienceVar.newprojects);
        userexperience_obj.put("userlevel", ExperienceVar.userlevel);
        userexperience_obj.put("settingstime", ExperienceVar.settingstimeinhoures + ExperienceManager.settingsthistime);
        userexperience_obj.put("setuptime", ExperienceVar.setuptimeinhoures + ExperienceManager.setupthistime);
        userexperience_obj.put("starttimes", ExperienceVar.starttimes + 1);

        //TODO weitere Einstellugen speichern
        userexperience.writeString(userexperience_obj.toString(), false); //Datei wird geschrieben

///////////////////////////////////////////////////////////////////////
    }


    public static long getprogrammfoldersize() { //Die Ordner größe wird ausgelesen (benötigt für UI.settings.subitems.subitem10)

        long length = 0;
        for (int i = 0; i < folder.listFiles().length; i++) { //Durch alle Dateien wird Durchgegeangen...
            if (folder.listFiles()[i].isFile())
                length += folder.listFiles()[i].length(); //Die größe der Datei wird ausgegeben
            else
                length += folder.length(); //Die eigenliche größe des Ordners wird ausgegeben
        }
        return length;

    }


    public static void delete() { //TODO Experimentell
        folder.delete(); //Der Ordner wird gelöscht

    }
}
