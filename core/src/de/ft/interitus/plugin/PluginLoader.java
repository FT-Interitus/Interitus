/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin;

import de.ft.interitus.Program;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class PluginLoader {

    public static boolean loadPlugin(File filetest) {

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
        Class<Plugin> cl = null;
        try {
            cl = (Class<Plugin>) new PluginClassLoader(new File(filetest.getAbsolutePath()).toURI().toURL()).loadClass(main);

            try {

                if (!PluginManagerHandler.pluginvalidator(new String(cl.getResourceAsStream("plugin.json").readAllBytes()))) {
                    Program.logger.warning("Plugin loading error");
                    return false;
                }

            } catch (Throwable e) {
                e.printStackTrace();

                Program.logger.warning("Plugin doesn't provide plugin.json");

                return false;
            }

        } catch (ClassNotFoundException | MalformedURLException e) {
            PluginManagerHandler.error = e;
            e.printStackTrace();
        }


// holen der Interfaces die die Klasse impementiert
        assert cl != null;
        Class[] interfaces = cl.getInterfaces();
        for (int y = 0; y < interfaces.length; y++)
            if (interfaces[y].getName().equals("de.ft.interitus.plugin.Plugin")) {
                break;
            }
        Plugin plugin;
        try {
            try {
                plugin = cl.getDeclaredConstructor().newInstance();
            } catch (InvocationTargetException | NoSuchMethodException e) {
                PluginManagerHandler.error = e;
                return false;
            }
            Program.logger.config("Loaded " + filetest.getName());
            PluginManagerHandler.loadedplugins.add(plugin);
        } catch (InstantiationException | IllegalAccessException e) {
            PluginManagerHandler.error = e;
            e.printStackTrace();
        }
        return true;


    }
}
