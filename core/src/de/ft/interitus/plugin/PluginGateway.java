package de.ft.interitus.plugin;

import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.Settings;
import de.ft.interitus.UI.Theme.RegisteredThemes;
import de.ft.interitus.UI.Theme.Theme;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.shortcut.ShortCut;
import de.ft.interitus.UI.shortcut.ShortCutChecker;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.utils.ArrayList;

import java.util.List;


/**
 * Plugin bridge into the Programm
 */
public class PluginGateway {
    public static ArrayList<ProjectTypes> pluginprojekttypes = new ArrayList<>();
    public static List<VisTable> pluginsettings = new ArrayList<>();
    public static List<Menu> pluginMenubar = new ArrayList<>();
    public static ArrayList<ShortCutChecker> pluginshortCutsChecker = new ArrayList<>();
    public static ArrayList<ShortCut> pluginshortCuts = new ArrayList<>();
    private static final ArrayList<Plugin> pluginprojekttypesplugins = new ArrayList<>();
    private static final ArrayList<Plugin> pluginsettingsplugins = new ArrayList<>();
    private static final ArrayList<Plugin> pluginMenubarplugins = new ArrayList<>();
    private static final ArrayList<Plugin> pluginshortCutsCheckerplugins = new ArrayList<>();
    private static final ArrayList<Plugin> pluginshortCutsplugins = new ArrayList<>();

    private static final ArrayList<Plugin> themesplugins = new ArrayList<>();

    public static boolean addsettings(VisTable settingsclass, Plugin requestedplugin) {
        if (PluginManagerHandler.loadedplugins.contains(requestedplugin)) {
            pluginsettings.add(settingsclass);
            pluginsettingsplugins.add(requestedplugin);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addMenuEntry(Menu menuentry, Plugin requestedplugin) {
        if (PluginManagerHandler.loadedplugins.contains(requestedplugin)) {
            pluginMenubar.add(menuentry);
            pluginMenubarplugins.add(requestedplugin);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addProjectType(ProjectTypes PT, Plugin requestedplugin) {
        if (PluginManagerHandler.loadedplugins.contains(requestedplugin)) {
            pluginprojekttypes.add(PT);
            pluginprojekttypesplugins.add(requestedplugin);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addShortcut(Plugin requestedplugin, ShortCut shortCut) {
        if (PluginManagerHandler.loadedplugins.contains(requestedplugin)) {
            pluginshortCuts.add(shortCut);
            pluginshortCutsplugins.add(requestedplugin);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addShortcutChecker(ShortCutChecker shortCutChecker, Plugin requestedplugin) {
        if (PluginManagerHandler.loadedplugins.contains(requestedplugin)) {
            pluginshortCutsChecker.add(shortCutChecker);
            pluginshortCutsCheckerplugins.add(requestedplugin);
            return true;
        } else {
            return false;
        }
    }

    public static boolean registerTheme(Theme theme, Plugin requestedplugin) {
        if (PluginManagerHandler.loadedplugins.contains(requestedplugin)) {
            RegisteredThemes.themes.add(theme);
            themesplugins.add(requestedplugin);
            return true;
        } else {
            return false;
        }
    }


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
        }catch (Throwable e) {
            e.printStackTrace();
        }


    }

}
