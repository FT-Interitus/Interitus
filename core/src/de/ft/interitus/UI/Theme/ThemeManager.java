package de.ft.interitus.UI.Theme;

import de.ft.interitus.UI.Theme.interitus.DarkMode;
import de.ft.interitus.UI.Theme.interitus.WhiteMode;

public class ThemeManager {
    public static void register() {

        //Register default Interitus Themes

        RegisteredThemes.themes.add(new WhiteMode());
        RegisteredThemes.themes.add(new DarkMode());


    }
}
