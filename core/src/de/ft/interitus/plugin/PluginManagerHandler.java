/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin;

import de.ft.interitus.DisplayErrors;
import de.ft.interitus.Programm;
import de.ft.interitus.Var;
import de.ft.interitus.datamanager.programmdata.Data;
import de.ft.interitus.utils.ArrayList;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PluginManagerHandler {


    public static List<Plugin> loadedplugins = new ArrayList<>();
    private static final ProgramRegistry registry = new ProgramRegistry();
    private static final PluginAssetManager assetManager = new PluginAssetManager();

    static Exception error = null;
    private static int Plugincounter = 0;


    public static Exception register() {

        if (!new File(System.getProperty("user.home") + "/" + Data.foldername + "/plugins").exists()) {
            new File(System.getProperty("user.home") + "/" + Data.foldername + "/plugins").mkdir();
        }

        File[] files = new File(System.getProperty("user.home") + "/" + Data.foldername + "/plugins").listFiles(); //Aus dem Ordner Plugins werden alle Files aufgelistet
        Programm.logger.config("Found " + files.length + " Plugins in Plugin Folder");
        for (File f : files) {
            if (f.getName().split("\\.")[1].contains("itpl") && f.getName().split("\\.")[1].endsWith("itpl")) {


                try {
                    PluginLoader.loadPlugin(f); //jedes gefundene Plugin bekommt den Befehel zu laden
                    Plugincounter++;
                } catch (Exception e) {
                    Programm.logger.severe("Plugin lade Fehler");
                    DisplayErrors.customErrorstring = "Plugin Lade-Fehler";
                    DisplayErrors.error = e;
                    e.printStackTrace();
                } catch (UnsupportedClassVersionError a) {
                    Programm.logger.severe("Das Plugin " + f.getName() + " wurde in der falschen Version geschrieben");

                }

            }
        }
        registerPlugins();
        return error;
    }

    public static void init() {

        for (Plugin plugin : loadedplugins) {
            plugin.init(assetManager);
        }


        startPluginLifeCycle();

    }

    private static void startPluginLifeCycle() {


        Timer time = new Timer();
        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long before;
                long after;

                for (int i = 0; i < loadedplugins.size(); i++) {
                    before = System.currentTimeMillis();
                    try {
                        loadedplugins.get(i).run();
                    } catch (Throwable e) {
                        DisplayErrors.customErrorstring = "Im Plugin " + getPluginArgs(loadedplugins.get(i),"name") + " ist ein Fehler aufgetreten und es wurde deaktiviert";
                        DisplayErrors.error = e;

                        //ProgramRegistry.removepluginregisters(loadedplugins.get(i));
                        loadedplugins.remove(loadedplugins.get(i));

                    }
                    after = System.currentTimeMillis();


                    //Disable Plugin if it is too slow
                    if (after - before > 80) {
                        Programm.logger.warning((after - before) + "");
                        Programm.logger.warning("Das Plugin " + getPluginArgs(loadedplugins.get(i),"name") + " ist zu langsam und wurde deshalb deaktiviert");

                        //ProgramRegistry.removepluginregisters(loadedplugins.get(i));
                        loadedplugins.remove(loadedplugins.get(i));

                    }

                }


            }
        }, 0, 17);


    }

    private static void registerPlugins() {
        for (int i = 0; i < loadedplugins.size(); i++) {


            try {


                int finalI = i;
                Thread thread = new Thread(() -> loadedplugins.get(finalI).register(registry));
                thread.start();


            } catch (Throwable e) {
                e.printStackTrace();
                loadedplugins.remove(loadedplugins.get(i));
            }

        }
    }

    protected static boolean pluginvalidator(String json) {
        JSONObject jsonObject = new JSONObject(json);
        try {
            if (jsonObject.getString("name").length() <= 3 || jsonObject.getString("name").startsWith(" ") || jsonObject.getString("name").endsWith(" ") || jsonObject.getString("name").length() > 50) {
                return false;

            }

            if (jsonObject.getDouble("version") <= 0.0) {
                return false;
            }

            if (jsonObject.getString("author").length() <= 3 || jsonObject.getString("author").startsWith(" ") || jsonObject.getString("author").endsWith(" ")) {
                return false;
            }

            if (jsonObject.getString("description").contentEquals("")) {
                return false;
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public static Object getPluginArgs(Plugin plugin,String attribute)  {


        if(plugin.getClass().getSimpleName().contentEquals("Native")) {
            if(attribute.contentEquals("name")) {
                return "Native";
            }else if(attribute.contentEquals("version")) {
                return Var.PROGRAMM_VERSION_ID;
            }
        }

        try {
           return new JSONObject(new String(plugin.getClass().getResourceAsStream("plugin.json").readAllBytes())).get(attribute);
        }catch (IOException e) {
            return null;
        }
    }

}
