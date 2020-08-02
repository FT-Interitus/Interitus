package de.ft.interitus.UI.Theme;

import com.badlogic.gdx.graphics.Color;

public interface Theme {

    Theme gettheme();

    Color ProgrammSpaceColor();

    Color BlocksColor();

    Color DeviceConnectionColor();

    Color ClearColor();

    Color WelcomeScreenBackgroundColor(); //TODO wird erstezt durch Bild

    Color WelcomeScreenFontColor();

    Color PopUpColor();

    boolean isdark();

    String getName();
}
