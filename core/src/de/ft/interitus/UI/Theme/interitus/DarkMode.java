package de.ft.interitus.UI.Theme.interitus;

import com.badlogic.gdx.graphics.Color;
import de.ft.interitus.UI.Theme.Theme;

public class DarkMode implements Theme {
    private final static Color clearColor = new Color(60f/255f,63f/255f,65f/255f,1);
    private final static Color welcomeFont = new Color(1,1,1,1);
    private final static Color welcomeBackground = new Color(43f/255f,43f/255f,43f/255f,1);
    private final static Color ProgrammSpace = new Color(43f/255f,43f/255f,43f/255f,1);
    private final static Color darkmode_blockbar = new Color(43f/255f,43f/255f,43f/255f,1);
    private final static Color connections = new Color(49f/255f,52f/255f,53f/255f,1);
    @Override
    public Theme gettheme() {
        return this;
    }

    @Override
    public Color ProgrammSpaceColor() {
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
    public boolean isdark() {
        return true;
    }

    @Override
    public String getName() {
        return "DarkMode";
    }
}
