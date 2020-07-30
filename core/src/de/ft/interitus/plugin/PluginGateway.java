package de.ft.interitus.plugin;

import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.Programm;
import de.ft.interitus.UI.Theme.RegisteredThemes;
import de.ft.interitus.UI.Theme.Theme;
import de.ft.interitus.UI.shortcut.ShortCut;
import de.ft.interitus.UI.shortcut.ShortCutChecker;
import de.ft.interitus.projecttypes.ProjectTypes;

import de.ft.interitus.utils.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Plugin bridge into the Programm
 */
public class PluginGateway {
    public static ArrayList<ProjectTypes> pluginprojekttypes = new ArrayList<>();
    public static List<VisTable> plugisettings = new ArrayList<>();
    public static List<Menu> pluginMenubar = new ArrayList<>();
    public static ArrayList<ShortCutChecker> pluginshortCutsChecker = new ArrayList<>();
    public static ArrayList<ShortCut> pluginshortCuts = new ArrayList<>();

    public static void addsettings(VisTable settingsclass) {
        plugisettings.add(settingsclass);
    }

    public static void addMenuEntry(Menu menuentry) {
        pluginMenubar.add(menuentry);
    }

    public static void addProjectType(ProjectTypes PT) {
        pluginprojekttypes.add(PT);
    }

    public static void addShortcut(ShortCut... shortCut) {
        pluginshortCuts.addAll(Arrays.asList(shortCut));
    }

    public static void addShortcutChecker(ShortCutChecker shortCutChecker) {
        pluginshortCutsChecker.add(shortCutChecker);
    }

    public static void registerTheme(Theme theme) {
        RegisteredThemes.themes.add(theme);
    }

}
