package de.ft.interitus.UI.Theme;

import com.badlogic.gdx.graphics.Color;

public interface Theme {

    Theme gettheme();

    Color ProgrammSpaceColor();

    Color BlocksColor();

    Color DeviceConnectionColor();

    Color ClearColor(); //TODO wird ersetzt

    Color WelcomeScreenBackgroundColor(); //TODO wird erstezt durch Bild

    Color WelcomeScreenFontColor();

    boolean isdark();

    String getName();
}
