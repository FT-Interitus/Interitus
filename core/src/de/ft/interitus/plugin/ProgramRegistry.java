/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.Settings;
import de.ft.interitus.UI.CheckShortcuts;
import de.ft.interitus.UI.Theme.RegisteredThemes;
import de.ft.interitus.UI.Theme.Theme;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.settings.SettingsUI;
import de.ft.interitus.UI.shortcut.ShortCut;
import de.ft.interitus.UI.shortcut.ShortCutChecker;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.ProjectTypesVar;
import de.ft.interitus.projecttypes.Importer.Import;
import de.ft.interitus.projecttypes.Importer.Importer;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.utils.ArrayList;

import java.io.IOException;
import java.util.List;


/**
 * Plugin bridge into the Programm
 */
public class ProgramRegistry {
    private static final ArrayList<ProjectTypes> pluginprojekttypes = new ArrayList<>();
    private static final List<VisTable> pluginsettings = new ArrayList<>();
    private static final List<Menu> pluginMenubar = new ArrayList<>();
    private static final ArrayList<ShortCutChecker> pluginshortCutsChecker = new ArrayList<>();
    private static final ArrayList<ShortCut> pluginshortCuts = new ArrayList<>();
    protected static final ArrayList<Pixmap> pluginpixmaps = new ArrayList<>();
    protected static final ArrayList<Texture> pluginTextures = new ArrayList<>();
    protected static final ArrayList<PluginRenderer> pluginRenderer = new ArrayList<>();
    private static final ArrayList<Plugin> pluginprojekttypesplugins = new ArrayList<>();
    private static final ArrayList<Plugin> pluginsettingsplugins = new ArrayList<>();
    private static final ArrayList<Plugin> pluginMenubarplugins = new ArrayList<>();
    private static final ArrayList<Plugin> pluginshortCutsCheckerplugins = new ArrayList<>();
    private static final ArrayList<Plugin> pluginshortCutsplugins = new ArrayList<>();
    private static final ArrayList<Plugin> themesplugins = new ArrayList<>();

    @SuppressWarnings("unused")
    public  boolean addsettings(VisTable settingsclass, Plugin requestedplugin) {
        if (PluginManagerHandler.loadedplugins.contains(requestedplugin)) {
            pluginsettings.add(settingsclass);
            pluginsettingsplugins.add(requestedplugin);
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unused")
    public  boolean addMenuEntry(Menu menuentry, Plugin requestedplugin) {
        if (PluginManagerHandler.loadedplugins.contains(requestedplugin)) {
            pluginMenubar.add(menuentry);
            pluginMenubarplugins.add(requestedplugin);
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unused")
    public  boolean addProjectType(ProjectTypes PT, Plugin requestedplugin) {
        if (PluginManagerHandler.loadedplugins.contains(requestedplugin)) {
            //Do not allow two ProjectTypes with the same Name
            for (ProjectTypes projectTypes : ProjectTypesVar.projectTypes) {
                if (PT.getName().contentEquals(projectTypes.getName())) {
                    return false;
                }
            }
            pluginprojekttypes.add(PT);
            pluginprojekttypesplugins.add(requestedplugin);
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unused")
    public  boolean addShortcut(Plugin requestedplugin, ShortCut shortCut) {
        if (PluginManagerHandler.loadedplugins.contains(requestedplugin)) {
            pluginshortCuts.add(shortCut);
            pluginshortCutsplugins.add(requestedplugin);
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unused")
    public  boolean addShortcutChecker(ShortCutChecker shortCutChecker, Plugin requestedplugin) {
        if (PluginManagerHandler.loadedplugins.contains(requestedplugin)) {
            pluginshortCutsChecker.add(shortCutChecker);
            pluginshortCutsCheckerplugins.add(requestedplugin);
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unused")
    public  boolean registerTheme(Theme theme, Plugin requestedplugin) {
        if (PluginManagerHandler.loadedplugins.contains(requestedplugin)) {
            RegisteredThemes.themes.add(theme);
            themesplugins.add(requestedplugin);
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unused")
    public  boolean registerAddon(Addon addon, Plugin requestedplugin) {
        if (PluginManagerHandler.loadedplugins.contains(requestedplugin)) {
            ProjectTypesVar.addons.add(addon);

            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unused")
    public  boolean registerImporter(Import importer, Plugin requestedplugin) {
        if (PluginManagerHandler.loadedplugins.contains(requestedplugin)) {
            Importer.importer.add(importer);

            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unused")
    public  int loadAsset(String internalPath, Plugin requestedplugin) {
        if (PluginManagerHandler.loadedplugins.contains(requestedplugin)) {


            try {
                byte[] imagesbytes = requestedplugin.getClass().getResourceAsStream(internalPath).readAllBytes();
                pluginpixmaps.add(new Pixmap(imagesbytes, 0, imagesbytes.length));
                return pluginpixmaps.size() - 1;

            } catch (IOException e) {
                return -1;
            }


        } else {
            return -1;
        }
    }

    @SuppressWarnings("unused")
    public  boolean addPluginRender(Plugin requestedplugin, PluginRenderer renderer) {
        if (PluginManagerHandler.loadedplugins.contains(requestedplugin)) {

            pluginRenderer.add(renderer);


            return true;

        } else {
            return false;
        }
    }

    /**
     * @deprecated it isn't posible to unload a Plugin
     * @param plugin
     */

    static void removepluginregisters(Plugin plugin) {

        try {

            for (int i = 0; i < pluginsettingsplugins.size(); i++) {
                if (pluginsettingsplugins.get(i) == plugin) {
                    pluginsettings.remove(i);
                    pluginsettingsplugins.remove(i);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        try {

            for (int i = 0; i < pluginMenubarplugins.size(); i++) {
                if (pluginMenubarplugins.get(i) == plugin) {
                    UI.menuBar.removeMenu(pluginMenubar.get(i));
                    pluginMenubar.remove(i);
                    pluginMenubarplugins.remove(i);


                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        try {

            for (int i = 0; i < pluginprojekttypesplugins.size(); i++) {
                if (pluginprojekttypesplugins.get(i) == plugin) {
                    pluginprojekttypes.remove(i);
                    pluginprojekttypesplugins.remove(i);
                }
            }
        } catch (Throwable e) {

        }

        try {

            for (int i = 0; i < pluginshortCutsCheckerplugins.size(); i++) {
                if (pluginshortCutsCheckerplugins.get(i) == plugin) {
                    pluginshortCutsCheckerplugins.remove(i);
                    pluginshortCutsChecker.remove(i);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        try {


            for (int i = 0; i < pluginshortCutsplugins.size(); i++) {
                if (pluginshortCutsplugins.get(i) == plugin) {
                    pluginshortCutsplugins.remove(i);
                    pluginshortCuts.remove(i);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        try {
            for (int i = 0; i < themesplugins.size(); i++) {
                if (themesplugins.get(i) == plugin) {
                    themesplugins.remove(i);
                    if (Settings.theme == RegisteredThemes.themes.get(i + 2)) {
                        Settings.theme = RegisteredThemes.themes.get(0);
                    }
                    RegisteredThemes.themes.remove(i + 2); //<- Because there are 2 Native Themes yet
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }


    }
    public static void addProjectTypes() {

        ProjectTypesVar.projectTypes.addAll(ProgramRegistry.pluginprojekttypes);
    }

    public static void addShortCuts() {
        CheckShortcuts.shortCuts.addAll(ProgramRegistry.pluginshortCuts);
        CheckShortcuts.shortCutsChecker.addAll(ProgramRegistry.pluginshortCutsChecker);
    }

    public static void addMenuBarItems() {
        for (int i = 0; i < ProgramRegistry.pluginMenubar.size(); i++) { //Alle Plugins MenuBar werden der MenuBar
            UI.menuBar.addMenu(ProgramRegistry.pluginMenubar.get(i));
        }
    }

    public static void addSettings(SettingsUI.TestNode node) {
        for (int i = 0; i < ProgramRegistry.pluginsettings.size(); i++) {
            node.add(new SettingsUI.TestNode(new VisLabel(" " + PluginManagerHandler.loadedplugins.get(i).getName() + " "), 17 + i));
        }
    }

    public static void getSettingsContainer(VisTable container,int id) {
        container.add(ProgramRegistry.pluginsettings.get(id));

    }

}
