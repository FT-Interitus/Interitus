package de.ft.interitus.plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class PluginManagerImpl {
    private List<PluginInterface> loadedplugins = new ArrayList<>();
    public void start(){
        File[] files = new File("plugins").listFiles();
        for(File f : files)
            loadPlugin(f);
        for(PluginInterface pi : loadedplugins)
            pi.start();
    }
    public void stop(){
        for(PluginInterface pi : loadedplugins)
            pi.stop();
    }
    public void loadPlugin(File filetest){
        //Erzeugen des JAR-Objekts
        JarFile file = null;
        try {
            file = new JarFile(filetest);
        } catch (IOException e) {
            e.printStackTrace();
        }
//Laden der MANIFEST.MF
        Manifest manifest = null;
        try {
            manifest = file.getManifest();
        } catch (IOException e) {
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
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
// holen der Interfaces die die Klasse impementiert
        Class[] interfaces = cl.getInterfaces();
// Durchlaufen durch die Interfaces der Klasse und nachsehn ob es das passende Plugin implementiert
        boolean isplugin = false;
        for(int y = 0; y < interfaces.length && !isplugin; y++)
            if(interfaces[y].getName().equals("de.ft.interitus.plugin.PluginInterface"))
                isplugin = true;
        if(isplugin){
            PluginInterface plugin = null;
            try {
                try {
                    plugin = (PluginInterface) cl.getDeclaredConstructor().newInstance();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            loadedplugins.add(plugin);
        }
    }
    public void openWindow(String msg){

    }
}
