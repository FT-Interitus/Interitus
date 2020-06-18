package de.ft.interitus.plugin;

import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.UI.Theme.RegisteredThemes;
import de.ft.interitus.UI.Theme.Theme;
import de.ft.interitus.UI.shortcut.ShortCut;
import de.ft.interitus.UI.shortcut.ShortCutChecker;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;
import org.usb4java.LoaderException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class PluginManagerHandler {

    public static List<PluginRegister> registeredplugins = new ArrayList<>();

    public static List<Plugin> loadedplugins = new ArrayList<>();

    Exception error = null;
    private int Plugincounter = 0;

    public static void register(PluginRegister pluginRegister) { //Wird von Plugins aufgerufen

        registeredplugins.add(pluginRegister);


        if (registeredplugins.size() == Var.pluginManager.Plugincounter) { //The last Plugin start the loop for all Plugins
            String[] names = new String[registeredplugins.size()];
            for (int i = 0; i < names.length; i++) {
                names[i] = registeredplugins.get(i).getName();
            }

            int counter = 0;
            boolean doppelt = false;

            for (int i = 0; i < names.length; i++) {
                for (int b = 0; i < registeredplugins.size(); i++) {

                    if (names[i] == registeredplugins.get(b).getName()) {
                        counter++;
                    }

                    if (counter >= 2) { //Falls es duplcate gibt
                        doppelt = true;
                        break;
                    }
                }
                if (doppelt) {
                    break;
                }
            }


            if (doppelt) {

                throw new LoaderException("Es gibt mehrere Plugins mit dem geleichen Namen");

            }


            for (int i = 0; i < Var.pluginManager.loadedplugins.size(); i++) {


                if (registeredplugins.get(i).getName() != "" && registeredplugins.get(i).getName() != " " && registeredplugins.get(i).getName().length() > 2 && !registeredplugins.get(i).getName().endsWith(" ") && !registeredplugins.get(i).getName().startsWith(" ")) {
                    if (registeredplugins.get(i).getVersion() > 0.0) {
                        System.out.println(registeredplugins.get(i).getName() + " geladen. In der Version " + registeredplugins.get(i).getVersion());

                    }else{
                        System.out.println(registeredplugins.get(i).getName() + " hasn't a valid Version");
                        registeredplugins.remove(i);
                    }
                }else{
                    System.out.println(registeredplugins.get(i).getName() + " hasn't a valid Configuration");
                    registeredplugins.remove(i);

                }
            }

            Timer plugintimer = new Timer();
            final ArrayList<Thread> pluginthread = new ArrayList<>();
            final ArrayList<Long> starttime = new ArrayList<>();
            final ArrayList<Boolean> finish = new ArrayList<>();
            final boolean[] firtstime = {true};
            plugintimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {

                    try {
                        pluginthread.clear();

                        //TODO erkennen ob ein Plugin zu langsam ist (starttime>19)

                        finish.clear();
                        starttime.clear();


                        for (int i = 0; i < registeredplugins.size(); i++) {

                            final int finalI = i;

                            final int finalI1 = i;
                            pluginthread.add(new Thread() {
                                @Override
                                public void run() {


                                    starttime.add(System.currentTimeMillis());
                                    try {
                                        finish.add(Var.pluginManager.loadedplugins.get(finalI).run());
                                    }catch (Exception e) {
                                        DisplayErrors.customErrorstring = "Fehler in einem Plugin. Es wurde deaktiviert!";
                                        DisplayErrors.error = e;
                                        registeredplugins.remove(Var.pluginManager.loadedplugins.get(finalI));
                                    }catch (NoClassDefFoundError e){

                                        registeredplugins.remove(Var.pluginManager.loadedplugins.get(finalI));
                                    }


                                }
                            });


                            pluginthread.get(i).start();

                        }
                        firtstime[0] = false;


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 0, 16);
        }

    }


    /**
     * @deprecated use PluginGateway
     *
     */
    public static void addsettings(VisTable settingsclass) {
        PluginGateway.plugisettings.add(settingsclass);
    }
    /**
     * @deprecated use PluginGateway

     */
    public static void addMenuEntry(Menu menuentry) {
        PluginGateway.pluginMenubar.add(menuentry);
    }
    /**
     * @deprecated use PluginGateway

     */
    public static void addProjectType(ProjectTypes PT) {
        PluginGateway.pluginprojekttypes.add(PT);
    }
    /**
     * @deprecated use PluginGateway

     */
    public static void addShortcut(ShortCut... shortCut) {
        PluginGateway.pluginshortCuts.addAll(Arrays.asList(shortCut));
    }
    /**
     * @deprecated use PluginGateway

     */
    public static void addShortcutChecker(ShortCutChecker shortCutChecker) {
        PluginGateway.pluginshortCutsChecker.add(shortCutChecker);
    }
    /**
     * @deprecated use PluginGateway

     */
    public static void registerTheme(Theme theme) {
        RegisteredThemes.themes.add(theme);
    }




    public Exception init() {


        File[] files = new File("plugins").listFiles(); //Aus dem Ordner Plugins werden alle Files aufgelistet
        for (File f : files) {
            if (f.getName().split("\\.")[1].contains("itp") && f.getName().split("\\.")[1].endsWith("itp")) {


                try {
                    loadPlugin(f); //jedes gefundene Plugin bekommt den Befehel zu laden
                    Plugincounter++;
                } catch (Exception e) {
                    System.out.println("Plugin lade Fehler");
                    DisplayErrors.customErrorstring = "Plugin Lade-Fehler";
                    DisplayErrors.error = e;
                    e.printStackTrace();
                } catch (UnsupportedClassVersionError a) {
                    System.out.println("Das Plugin " + f.getName() + " wurde in der falschen Version geschrieben");

                }

            }
        }
        for (Plugin pi : loadedplugins) {

            try {
                pi.register();  //jedes gefundene Plugin bekommt den Befehl sich zu registrieren
            }catch (NoSuchMethodError e) {
                try {
                    throw new PluginSDKDeprecatedException("A Plugin dosn't use the newest Interitus-SDK version!");
                } catch (PluginSDKDeprecatedException pluginSDKDeprecatedException) {
                    pluginSDKDeprecatedException.printStackTrace();
                }

            }
        }

        return error;
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

        }catch (NullPointerException e) {

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
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    error = e;
                    e.printStackTrace();
                }
                System.out.println("Loaded " + filetest.getName());
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

    public static void unload(PluginRegister pluginRegister) {

        registeredplugins.remove(pluginRegister);


    }

    public Plugin getsettings(Plugin pl) {
        return loadedplugins.get(loadedplugins.indexOf(pl));

    }


}
