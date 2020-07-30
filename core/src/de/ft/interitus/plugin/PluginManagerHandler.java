package de.ft.interitus.plugin;

import de.ft.interitus.DisplayErrors;
import de.ft.interitus.Programm;
import de.ft.interitus.datamanager.programmdata.Data;
import de.ft.interitus.utils.ArrayList;

import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PluginManagerHandler {


    public static List<Plugin> loadedplugins = new ArrayList<>();

    static Exception error = null;
    private static int Plugincounter = 0;


    public static Exception init() {

        if (!new File(System.getProperty("user.home") + "/" + Data.foldername + "/plugins").exists()) {
            new File(System.getProperty("user.home") + "/" + Data.foldername + "/plugins").mkdir();
        }

        File[] files = new File(System.getProperty("user.home") + "/" + Data.foldername + "/plugins").listFiles(); //Aus dem Ordner Plugins werden alle Files aufgelistet
        Programm.logger.config("Found " + files.length + " Plugins in Plugin Folder");
        for (File f : files) {
            if (f.getName().split("\\.")[1].contains("itpl") && f.getName().split("\\.")[1].endsWith("itpl")) {


                try {
                    PluginClassLoader.loadPlugin(f); //jedes gefundene Plugin bekommt den Befehel zu laden
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


        startPluginLifeCycle();


        return error;
    }

    private static void startPluginLifeCycle() {


        for (Plugin plugin : loadedplugins) {
            try {


                if (plugin.getName().length() <= 3 || plugin.getName().startsWith(" ") || plugin.getName().endsWith(" ") || plugin.getName().length() > 50) {
                    loadedplugins.remove(plugin);

                }

                if (plugin.getVersion() <= 0.0) {
                    loadedplugins.remove(plugin);
                }

                if (plugin.getAuthor().length() <= 3 || plugin.getAuthor().startsWith(" ") || plugin.getAuthor().endsWith(" ")) {
                    loadedplugins.remove(plugin);
                }

                if (plugin.getDescription() == "") {
                    loadedplugins.remove(plugin);
                }
            } catch (Throwable e) {
                loadedplugins.remove(plugin);
            }

            if (!loadedplugins.contains(plugin)) {
                Programm.logger.warning("Das Plugin " + plugin.getName() + " konnte nicht geladen werden.");
            } else {
                Thread thread = new Thread(() -> plugin.register());
                thread.start();


            }

        }


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
                        DisplayErrors.customErrorstring = "Im Plugin " + loadedplugins.get(i).getName() + " ist ein Fehler aufgetreten und es wurde deaktiviert";
                        DisplayErrors.error = e;

                        PluginGateway.removeplugin(loadedplugins.get(i));
                        loadedplugins.remove(loadedplugins.get(i));

                    }
                    after = System.currentTimeMillis();


                    if (after - before > 20) {
                        Programm.logger.warning("Das Plugin " + loadedplugins.get(i).getName() + " ist zu langsam und wurde deshalb deaktiviert");
                        loadedplugins.get(i).stop();
                        PluginGateway.removeplugin(loadedplugins.get(i));
                        loadedplugins.remove(loadedplugins.get(i));

                    }

                }


            }
        }, 0, 17);


    }


}
