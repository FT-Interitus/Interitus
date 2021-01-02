/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.Theme;

import com.badlogic.gdx.graphics.Color;

public interface Theme {

    Theme getTheme();

    Color ProgramSpaceColor();

    Color BlocksColor();

    Color DeviceConnectionColor();

    Color ClearColor();

    Color WelcomeScreenBackgroundColor(); //TODO replace with Picture

    Color WelcomeScreenFontColor();

    Color PopUpColor();

    String getName();
}
