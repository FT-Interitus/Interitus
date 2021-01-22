/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.Theme;

import de.ft.interitus.Program;
import de.ft.interitus.UI.Theme.interitus.BlueDark;
import de.ft.interitus.UI.Theme.interitus.DarkMode;

public class ThemeManager {
    public static void register() {

        //Register default Interitus nativ Themes
        RegisteredThemes.themes.add(new DarkMode());
        RegisteredThemes.themes.add(new BlueDark());
        Program.logger.config("Registered " + RegisteredThemes.themes.size() + " native Themes");


    }
}
