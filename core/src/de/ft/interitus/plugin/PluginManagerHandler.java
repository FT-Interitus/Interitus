package de.ft.interitus.plugin;

import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.Var;
import org.usb4java.LoaderException;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class PluginManagerHandler {
    Exception error = null;
    public List<Plugin> loadedplugins = new ArrayList<>();
    public static List<PluginRegister> registeredplugins = new ArrayList<>();
    public static List<VisTable> plugisettings = new ArrayList<>();
    public static List<Menu> pluginMenubar = new ArrayList<>();
    private int Plugincounter = 0;
    public Exception init(){


        File[] files = new File("plugins").listFiles(); //Aus dem Ordner Plugins werden alle Files aufgelistet
        for(File f : files) {
            Plugincounter++;
            loadPlugin(f); //jedes gefundene Plugin bekommt den Befehel zu laden
        }
        for(Plugin pi : loadedplugins) {

            pi.register();  //jedes gefundene Plugin bekommt den Befehl sich zu registrieren

        }

       return error;
    }
    public void stop(){
        for(Plugin pi : loadedplugins)
            pi.stop();
    }

    public void loadPlugin(File filetest){

        //Erzeugen des JAR-Objekts

        JarFile file = null;
        try {
            file = new JarFile(filetest);
        } catch (IOException e) {
            error = e;
            e.printStackTrace();
        }


//Laden der MANIFEST.MF
        Manifest manifest = null;
        try {
            manifest = file.getManifest();
        } catch (IOException e) {
            error = e;
            e.printStackTrace();
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
        for(int y = 0; y < interfaces.length && !isplugin; y++)
            if(interfaces[y].getName().equals("de.ft.interitus.plugin.Plugin"))
                isplugin = true;
        if(isplugin){
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
            } catch (InstantiationException e) {
                error = e;
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                error = e;
                e.printStackTrace();
            }
            loadedplugins.add(plugin);
        }else{
            System.out.println("Fehlerhaftes Plugin");
        }


    }
    public void openWindow(String msg){

    }

    public Plugin getsettings(Plugin pl) {


      return loadedplugins.get(loadedplugins.indexOf(pl));




    }

public static void register(PluginRegister pluginRegister) { //Wird von Plugins aufgerufen

        registeredplugins.add(pluginRegister);


        if(registeredplugins.size()== Var.pluginManager.Plugincounter) {
          String[] names = new String[registeredplugins.size()];
            for(int i=0;i<names.length;i++) {
                names[i] = registeredplugins.get(i).getName();
            }

            int counter = 0;
            boolean doppelt = false;

            for(int i =0;i<names.length;i++) {
                for(int b = 0;i<registeredplugins.size();i++) {

                    if(names[i]==registeredplugins.get(b).getName()) {
                        counter++;
                    }

                    if(counter>=2) { //Falls es duplcate gibt
                        doppelt=true;
                        break;
                    }
                }
                if(doppelt) {
                    break;
                }
            }


            if(doppelt) {

                throw new LoaderException("Es gibt mehrere Plugins mit dem geleichen Namen");

            }


            for(int i =0;i<Var.pluginManager.loadedplugins.size();i++) {


                if (registeredplugins.get(i).getName() != "" && registeredplugins.get(i).getName() != " " && registeredplugins.get(i).getName().length() > 2 && !registeredplugins.get(i).getName().endsWith(" ") && !registeredplugins.get(i).getName().startsWith(" ")) {
                    if(registeredplugins.get(i).getVersion()>0.0) {
                        System.out.println(registeredplugins.get(i).getName() + " geladen. In der Version "+registeredplugins.get(i).getVersion());

                    }
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

                    pluginthread.clear();

                    //TODO erkennen ob ein Plugin zu langsam ist (starttime>19)

                    finish.clear();
                    starttime.clear();


                    for(int i =0;i<registeredplugins.size();i++) {

                       final int finalI = i;

                       final int finalI1 = i;
                        pluginthread.add(new Thread() {
                            @Override
                            public void run() {


                                    starttime.add(System.currentTimeMillis());
                                    finish.add(Var.pluginManager.loadedplugins.get(finalI).run());



                            }
                        });


                        pluginthread.get(i).start();

                    }
                    firtstime[0] = false;



                }
            },0,16);
        }

    }

    public static void addsettings(VisTable settingsclass) {
        plugisettings.add(settingsclass);
    }
    public static void addMenuEntry(Menu menuentry) {
        pluginMenubar.add(menuentry);
    }


}
