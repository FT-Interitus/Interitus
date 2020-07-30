package de.ft.interitus.plugin;

import de.ft.interitus.DisplayErrors;
import de.ft.interitus.Programm;
import de.ft.interitus.Var;
import de.ft.interitus.datamanager.programmdata.Data;
import org.usb4java.LoaderException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import de.ft.interitus.utils.ArrayList;

import java.nio.file.DirectoryStream;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class PluginManagerHandler {



    public static List<Plugin> loadedplugins = new ArrayList<>();

    Exception error = null;
    private int Plugincounter = 0;







    public Exception init() {

        if (!new File(System.getProperty("user.home") + "/"+ Data.foldername+"/plugins").exists()) {
            new File(System.getProperty("user.home") + "/"+Data.foldername+"/plugins").mkdir();
        }

        File[] files = new File(System.getProperty("user.home") + "/"+Data.foldername+"/plugins").listFiles(); //Aus dem Ordner Plugins werden alle Files aufgelistet
        Programm.logger.config("Found "+files.length+" Plugins in Plugin Folder");
        for (File f : files) {
            if (f.getName().split("\\.")[1].contains("itpl") && f.getName().split("\\.")[1].endsWith("itpl")) {


                try {
                    loadPlugin(f); //jedes gefundene Plugin bekommt den Befehel zu laden
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

    private void startPluginLifeCycle() {
        ArrayList<Thread> threads = new ArrayList<>();

        for(Plugin plugin : loadedplugins) {
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
            }catch (Throwable e) {
                loadedplugins.remove(plugin);
            }

            if(!loadedplugins.contains(plugin)) {
                Programm.logger.warning("Das Plugin "+ plugin.getName()+ " konnte nicht geladen werden.");
            }else{
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

                for(int i=0;i<loadedplugins.size();i++) {
                    before = System.currentTimeMillis();
                    try {
                        loadedplugins.get(i).run();
                    }catch (Throwable e) {
                        DisplayErrors.customErrorstring = "Im Plugin " + loadedplugins.get(i).getName() +" ist ein Fehler aufgetreten und es wurde deaktiviert";
                        DisplayErrors.error = e;

                        PluginGateway.removeplugin(loadedplugins.get(i));
                        loadedplugins.remove(loadedplugins.get(i));

                    }
                    after = System.currentTimeMillis();


                    if(after-before>20) {
                        Programm.logger.warning("Das Plugin " + loadedplugins.get(i).getName()+ " ist zu langsam und wurde deshalb deaktiviert");
                        PluginGateway.removeplugin(loadedplugins.get(i));
                        loadedplugins.remove(loadedplugins.get(i));
                    }

                }


            }
        },0,17);






    }

    public void stop() {
        for (Plugin pi : loadedplugins)
            pi.stop();
    }

    public void loadPlugin(File filetest) {

        //Erzeugen des JAR-Objekts

        JarFile file = null;

        try {
            file = new JarFile(filetest);
        } catch (IOException e) {
            error = e;

        }


//Laden der MANIFEST.MF
        Manifest manifest = null;
        try {
            manifest = file.getManifest();
        } catch (IOException e) {
            error = e;

        } catch (NullPointerException e) {

        }


// auslesen der Attribute aus der Manifest

        Attributes attrib = manifest.getMainAttributes();

// holen der Mainclass aus den Attributen
        String main = attrib.getValue(Attributes.Name.MAIN_CLASS);
// laden der Klasse mit dem File als URL und der Mainclass
        Class cl = null;
        try {
            cl = new URLClassLoader(new URL[]{new File(filetest.getAbsolutePath()).toURI().toURL()}).loadClass(main);
        } catch (ClassNotFoundException e) {
            error = e;
            e.printStackTrace();
        } catch (MalformedURLException e) {
            error = e;
            e.printStackTrace();
        }


// holen der Interfaces die die Klasse impementiert
        Class[] interfaces = cl.getInterfaces();
// Durchlaufen durch die Interfaces der Klasse und nachsehn ob es das passende Plugin implementiert
        boolean isplugin = false;
        for (int y = 0; y < interfaces.length && !isplugin; y++)
            if (interfaces[y].getName().equals("de.ft.interitus.plugin.Plugin"))
                isplugin = true;
        if (isplugin) {
            Plugin plugin = null;
            try {
                try {
                    plugin = (Plugin) cl.getDeclaredConstructor().newInstance();
                } catch (InvocationTargetException e) {
                    error = e;
                   return;
                } catch (NoSuchMethodException e) {
                    error = e;
                    return;
                }
                Programm.logger.config("Loaded " + filetest.getName());
                loadedplugins.add(plugin);
            } catch (InstantiationException e) {
                error = e;
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                error = e;
                e.printStackTrace();
            }

        } else {
            System.out.println("Fehlerhaftes Plugin");
        }


    }



}
