package de.ft.interitus.UI.Theme.interitus;

import com.badlogic.gdx.graphics.Color;
import de.ft.interitus.UI.Theme.Theme;

public class WhiteMode implements Theme {
    public final static Color ProgrammSpace = new Color(1f, 1f, 1f, 1);
    public final static Color BlockBarColor = new Color(1f, 1f, 1f, 1);
    public final static Color DeviceConnection = new Color(1f, 1f, 1f, 1);
    private final static Color clearColor = new Color(0.54f, 0.533f, 0.51f, 1);
    private final static Color welcomeFont = new Color(0, 0, 0, 1);
    private final static Color welcomeBackground = new Color(1f, 1f, 1f, 1);

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
        return BlockBarColor;
    }

    @Override
    public Color DeviceConnectionColor() {
        return DeviceConnection;
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
        return false;
    }

    @Override
    public String getName() {
        return "White Mode";
    }
}
