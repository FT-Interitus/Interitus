/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.Theme.interitus;

import com.badlogic.gdx.graphics.Color;
import de.ft.interitus.UI.Theme.Theme;

public class BlueDark implements Theme {
    private final static Color clearColor = new Color(50f / 255f, 56f / 255f, 68f / 255f, 1);
    private final static Color welcomeFont = new Color(1, 1, 1, 1);
    private final static Color welcomeBackground = new Color(43f / 255f, 43f / 255f, 43f / 255f, 1);
    private final static Color ProgrammSpace =new Color(40f / 255f, 44f / 255f, 52f / 255f, 1);
    private final static Color darkmode_blockbar =new Color(40f / 255f, 44f / 255f, 52f / 255f, 1);
    private final static Color connections = new Color(61f / 255f, 66f / 255f, 75f / 255f, 1);
    private final static Color popups = new Color(61f / 255f, 66f / 255f, 75f / 255f, 1);

    @Override
    public Theme getTheme() {
        return this;
    }

    @Override
    public Color ProgramSpaceColor() {
        return ProgrammSpace;
    }

    @Override
    public Color BlocksColor() {
        return darkmode_blockbar;
    }

    @Override
    public Color DeviceConnectionColor() {
        return connections;
    }

    @Override
    public Color ClearColor() {
        return clearColor;
    }

    @Override
    public Color WelcomeScreenBackgroundColor() {
        return welcomeBackground;
    }

    @Override
    public Color WelcomeScreenFontColor() {
        return welcomeFont;
    }

    @Override
    public Color PopUpColor() {
        return popups;
    }


    @Override
    public String getName() {
        return "Blue-Dark";
    }
}
