/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3FileHandle;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Files;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Programm;
import de.ft.interitus.UI.UIElements.TextField;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.FileHandler;

public class PluginLoader {

    public static void loadPlugin(File filetest) throws IOException {

        //Erzeugen des JAR-Objekts

        JarFile file = null;

        try {
            file = new JarFile(filetest);
        } catch (IOException e) {
            PluginManagerHandler.error = e;

        }


//Laden der MANIFEST.MF
        Manifest manifest = null;
        try {
            assert file != null;
            manifest = file.getManifest();
        } catch (IOException e) {
            PluginManagerHandler.error = e;

        } catch (NullPointerException ignored) {

        }


// auslesen der Attribute aus der Manifest

        assert manifest != null;
        Attributes attrib = manifest.getMainAttributes();

// holen der Mainclass aus den Attributen
        String main = attrib.getValue(Attributes.Name.MAIN_CLASS);
// laden der Klasse mit dem File als URL und der Mainclass
        Class cl = null;
        try {
            cl = new PluginClassLoader(new File(filetest.getAbsolutePath()).toURI().toURL()).loadClass(main);

           try {

               if(!PluginManagerHandler.pluginvalidator(new String(cl.getResourceAsStream("plugin.json").readAllBytes()))) {
                   Programm.logger.warning("Plugin loading error");
                   return;
               }

           }catch (Throwable e) {
               e.printStackTrace();

               Programm.logger.warning("Plugin doesn't provide plugin.json");

               return;
           }

        } catch (ClassNotFoundException e) {
            PluginManagerHandler.error = e;
            e.printStackTrace();
        } catch (MalformedURLException e) {
            PluginManagerHandler.error = e;
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
                    PluginManagerHandler.error = e;
                    return;
                } catch (NoSuchMethodException e) {
                    PluginManagerHandler.error = e;
                    return;
                }
                Programm.logger.config("Loaded " + filetest.getName());
                PluginManagerHandler.loadedplugins.add(plugin);
            } catch (InstantiationException e) {
                PluginManagerHandler.error = e;
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                PluginManagerHandler.error = e;
                e.printStackTrace();
            }

        } else {
            Programm.logger.severe("Fehlerhaftes Plugin");
        }


    }
}
