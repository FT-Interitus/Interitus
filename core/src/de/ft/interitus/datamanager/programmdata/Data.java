/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.datamanager.programmdata;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.Logging.LoggerInit;
import de.ft.interitus.Program;
import de.ft.interitus.Settings;
import de.ft.interitus.UI.CheckShortcuts;
import de.ft.interitus.UI.Theme.RegisteredThemes;
import de.ft.interitus.UI.shortcut.ShortCut;
import de.ft.interitus.Var;
import de.ft.interitus.datamanager.programmdata.experience.ExperienceManager;
import de.ft.interitus.datamanager.programmdata.experience.ExperienceVar;
import de.ft.interitus.utils.ArrayList;
import de.ft.interitus.utils.FolderUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Data {
    //ANMERKUNG Die Programmdaten sind je nach Benutzer unterschiedlich, deswegen liegen sie auch direkt im Bentzter Ordner
    public static ArrayList<String> path = new ArrayList<String>(); //Die Pfade der zuletzt geöffneten Projekten
    public static ArrayList<String> filename = new ArrayList<String>(); //Die Namen der Dateien die zuletzt geöffnet wurden
    public static File tempfolder; //Der Ordner in dem alle Programm daten liegen
    public static String foldername = ".itd";
    public static File folder; //Der Ordner in dem alle Programm daten liegen

    /**
     * load the Programm data from the UserFolder/"+foldername+"
     * <p>
     * On Linux the Folder with a dot is default invisible
     * On Windows we need to set the file attribute
     * <p>
     * In the recent file there are the projects which were opened recently
     * In the settings file the Settings from the user are Saved
     * <p>
     * In the devices file are the whole configurated devices saved
     * In the experienced file the user level, ... is saved
     * <p>
     * In the shortcut file are all shurtcuts
     * And booleans if a shortcut is disabled
     */
    public static void init() {

        folder = new File(System.getProperty("user.home") + "/" + foldername); //Order der Programmdaten
        tempfolder = new File(System.getProperty("user.home") + "/" + foldername + "/temp"); //Order der Programmdaten
        File recent = new File(System.getProperty("user.home") + "/" + foldername + "/recent.json"); //JSON file in dem die zuletzt geöffneten Projekte gespeichert werden
        File settings = new File(System.getProperty("user.home") + "/" + foldername + "/settings.json"); // JSON file in dem alle Einstellungen gespeichert werden
        File userexperience = new File(System.getProperty("user.home") + "/" + foldername + "/experience.json"); //JSON file in dem User Analytics gespeichert werden
        File tastenkombinationen = new File(System.getProperty("user.home") + "/" + foldername + "/tastenkombinationen.json"); //JSON file in dem tastenkombinationen gespeichert werden


        Path path = folder.toPath();
        if (!folder.exists()) {//Wenn der Programm-Ordner noch nicht exsitiert
            Program.logger.warning("Create Programm Data Folder");

            folder.mkdir(); //der Ordner wird erstellt
            tempfolder.mkdir();

            if (Var.savemode) {
                try {
                    new File(System.getProperty("user.home") + "/" + Data.foldername + "/save.mode").createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            try {
                recent.createNewFile(); //Die datei für die letzten Projekte wird erstellt

            } catch (IOException e) {
                e.printStackTrace();
                DisplayErrors.error = e;
            }

            try {
                settings.createNewFile();//Die datei für die Einstellungen wird erstellt
                Settings.theme = RegisteredThemes.themes.get(0);

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
                Program.logger.config("Cannot hidden file");
            }
        } else {
            Program.logger.config("Load Programm Instance: " + folder.getAbsolutePath());

            if (new File(System.getProperty("user.home") + "/" + Data.foldername + "/save.mode").exists()) {
                if (!Var.savemode) {
                    Program.logger.severe("Das Öffnen von Interits aus einer Abgesicherten Modus Instanz ist nicht erlaubt");
                    System.exit(-1);
                } else {
                    try {
                        FolderUtils.deleteFileOrFolder(Path.of(System.getProperty("user.home") + "/" + Data.foldername));

                        Data.init();
                        return;

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }

            if (!tempfolder.exists()) {
                tempfolder.mkdir();
            }

            if (!recent.exists()) { //Wenn die Datei der letzten Projekte noch nicht exsisiert
                Program.logger.config("Recent not found");

                try {
                    recent.createNewFile();
                    Gdx.files.absolute(recent.getAbsolutePath()).writeString("{}", false); //Wird in das Verzeichnis mit {} als JSON indikator geschrieben
                } catch (IOException e) {
                    DisplayErrors.error = e;
                    e.printStackTrace();
                }


            } else { //Wenn es exsistiert
                try {

                    FileHandle re = Gdx.files.absolute(System.getProperty("user.home") + "/" + foldername + "/recent.json");

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

                } catch (Exception e) {

                }
            }


            if (!settings.exists()) { //siehe recent
                Program.logger.config("Settings not found");
                try {
                    settings.createNewFile();
                    Gdx.files.absolute(settings.getAbsolutePath()).writeString("{}", false); //siehe recent
                } catch (IOException e) {
                    DisplayErrors.error = e;
                    e.printStackTrace();
                }
                Settings.theme = RegisteredThemes.themes.get(0);
            } else {

                try {
                    FileHandle se = Gdx.files.absolute(System.getProperty("user.home") + "/" + foldername + "/settings.json");  //Datei wird geladen
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
                    } catch (JSONException e) {
                        Settings.theme = RegisteredThemes.themes.get(0); //Load Dark Mode if Theme Attribut is unavailable
                    }

                    if (Settings.theme == null) {
                        Settings.theme = RegisteredThemes.themes.get(0); //Load Dark Mode if Theme is unavailable
                    }


                    Settings.defaultpfad = obj.getString("defaultpath");
                    Settings.Vsync = obj.getBoolean("vsync");
                    Settings.limitfps = obj.getInt("limitfps");
                    Settings.hints = obj.getBoolean("hints");
                    Settings.personalhits = obj.getBoolean("personalhints");
                    Settings.betaupdates = obj.getBoolean("betaupdates");
                    Settings.disableblockgrayout = obj.getBoolean("disableblockgrayout");
                    Settings.blockSnapping = obj.getBoolean("blocksnapping");
                    Settings.blockActiveSnapping = obj.getBoolean("blockactivesnapping");
                    // weitere einstellugen Laden

                } catch (Exception e) {

                }


            }





            if (!userexperience.exists()) { //siehe recent
                try {
                    Program.logger.config("userexperience not found");

                    userexperience.createNewFile();
                    Gdx.files.absolute(userexperience.getAbsolutePath()).writeString("{}", false); //siehe recent
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                try {
                    FileHandle se = Gdx.files.absolute(System.getProperty("user.home") + "/" + foldername + "/experience.json");
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




                } catch (JSONException e) {

                } catch (Exception e) {

                }


            }

            if (!tastenkombinationen.exists()) { //siehe recent
                try {
                    Program.logger.config("tastenkombinationen not found");
                    tastenkombinationen.createNewFile();
                    Gdx.files.absolute(tastenkombinationen.getAbsolutePath()).writeString("{}", false); //siehe recent
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                try {
                    FileHandle se = Gdx.files.absolute(System.getProperty("user.home") + "/" + foldername + "/tastenkombinationen.json");
                    if (se.readString() == "") {
                        se.writeString("{}", false);
                        return;
                    }
                    JSONObject obj = new JSONObject(se.readString());


                    ////////////////////////////////////////////////////////////////////////////////////////////////////
                    int i = 0;
                    while (obj.has("tastenkombination" + i)) { //Es wird geschaut wie viele Einträge exsistieren
                        i++;
                    }


                    for (int a = 0; a < i; a++) { //Wird durch alle tastenkombinationen durchgegangen

                        CheckShortcuts.shortCuts.get(a).setShortCut();
                        CheckShortcuts.shortCuts.get(a).setDisable((boolean) obj.getJSONArray("tastenkombination" + a).get(0));

                        for (int b = 0; b < obj.getJSONArray("tastenkombination" + a).length() - 1; b++) {
                            CheckShortcuts.shortCuts.get(a).addTaste(Integer.parseInt(obj.getJSONArray("tastenkombination" + a).get(b + 1).toString()));


                        }

                    }


                    ////////////////////////////////////////////////////////////////////////////////////////////////////


                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {

                }


            }


        }


        //for recent/////////////////////////////////////////////////////////


        //////////////////////////////////////////////////////////////////7

        Program.logger.config("Programm Data Loading fininsh");

    }


    public static void close(boolean closeprogramm) {
        Program.logger.config("Saved Programm Settings");
        //////Tastenkombinationen////////////////////////////////////
        FileHandle tastenkombinationen = Gdx.files.absolute(System.getProperty("user.home") + "/" + foldername + "/tastenkombinationen.json"); //Lade datei
        JSONObject tastenkombinationen_obj = new JSONObject(tastenkombinationen);

        for (int i = 0; i < CheckShortcuts.shortCuts.size(); i++) {//Es wird durch alle einträge durchgegangen
            ShortCut aktualshortcut = CheckShortcuts.shortCuts.get(i);
            JSONArray array = new JSONArray();
            array.put(aktualshortcut.isDisable());
            for (int j = 0; j < aktualshortcut.getCombination().size(); j++) {//Es wird durch alle tasten des shortcuts durchgegangen
                array.put(aktualshortcut.getCombination().get(j));
            }
            tastenkombinationen_obj.put("tastenkombination" + i, array); //Und jedes Nacheinander abgespeichert


        }


        tastenkombinationen.writeString(tastenkombinationen_obj.toString(), false); //Datei wird geschrieben

        ////////////////////////////////////////////////////////////////////


        //for recent////////////////////////////////
        FileHandle recent = Gdx.files.absolute(System.getProperty("user.home") + "/" + foldername + "/recent.json"); //Lade datei
        JSONObject recent_obj = new JSONObject(recent);
        for (int i = 0; i < Data.path.size(); i++) { //Es wird durch alle Vorhanden einträge durch gegangen
            recent_obj.put("path" + i, Data.path.get(i)); //Und jedes Nacheinander abgespeichert
            recent_obj.put("filename" + i, Data.filename.get(i));
        }


        recent.writeString(recent_obj.toString(), false); //Datei wird geschrieben
        /////////////////////////////////////////////////////////////////////


        FileHandle settings = Gdx.files.absolute(System.getProperty("user.home") + "/" + foldername + "/settings.json"); //Lade Datei
        JSONObject settings_obj = new JSONObject(settings); //Einstellungen werden je nach Daten Typ abgespeichert
        settings_obj.put("theme", Settings.theme.getName());
        settings_obj.put("defaultpath", Settings.defaultpfad);
        settings_obj.put("vsync", Settings.Vsync);
        settings_obj.put("limitfps", Settings.limitfps);
        settings_obj.put("hints", Settings.hints);
        settings_obj.put("personalhints", Settings.personalhits);
        settings_obj.put("betaupdates",Settings.betaupdates);
        settings_obj.put("disableblockgrayout",Settings.disableblockgrayout);
        settings_obj.put("blocksnapping",Settings.blockSnapping);
        settings_obj.put("blockactivesnapping", Settings.blockActiveSnapping);


        //hier weitere Einstellugen speichern
        settings.writeString(settings_obj.toString(), false); //Datei wird geschrieben





        ///////////////////////////////////////////////////////////////
        FileHandle userexperience = Gdx.files.absolute(System.getProperty("user.home") + "/" + foldername + "/experience.json"); //Lade Datei
        JSONObject userexperience_obj = new JSONObject(userexperience); //UserExperience wird je nach Daten Typ abgespeichert


        userexperience_obj.put("time", ExperienceManager.getthistime() + ExperienceVar.programmtimeinhoures);
        userexperience_obj.put("newprojects", ExperienceVar.newprojects);
        userexperience_obj.put("userlevel", ExperienceVar.userlevel);
        userexperience_obj.put("settingstime", ExperienceVar.settingstimeinhoures + ExperienceManager.settingsthistime);
        userexperience_obj.put("setuptime", ExperienceVar.setuptimeinhoures + ExperienceManager.setupthistime);
        userexperience_obj.put("starttimes", ExperienceVar.starttimes + 1);

        // weitere User Experience speichern
        userexperience.writeString(userexperience_obj.toString(), false); //Datei wird geschrieben

///////////////////////////////////////////////////////////////////////

        if(closeprogramm) {
            new File(System.getProperty("user.home") + "/" + foldername + "/it.lock").delete();
            if (!Var.keeplog) {
                LoggerInit.fh.close();
                new File(Var.logname).delete();
            } else {
                LoggerInit.fh.close();
                new File(Var.logname).renameTo(new File(System.getProperty("user.home") + "/KeepLog_" + Var.lognamefile));
            }

        }

        try {
            if(closeprogramm) {
                FolderUtils.deleteFileOrFolder(Path.of(System.getProperty("user.home") + "/" + foldername + "/temp/"));
            }
        } catch (Exception e) {
            //i.e. File already deleted, no permission...
        }

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




    public static void init(String s) {
        foldername = s;

        init();


    }
}
