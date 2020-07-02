package de.ft.interitus.UI.Theme;

import de.ft.interitus.Programm;
import de.ft.interitus.UI.Theme.interitus.DarkMode;
import de.ft.interitus.UI.Theme.interitus.WhiteMode;

public class ThemeManager {
    public static void register() {

        //Register default Interitus nativ Themes

        RegisteredThemes.themes.add(new WhiteMode());
        RegisteredThemes.themes.add(new DarkMode());
        Programm.logger.config("Registered " + RegisteredThemes.themes.size()+" native Themes");


    }
}
