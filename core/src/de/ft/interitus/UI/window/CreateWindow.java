/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.window;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowConfiguration;
import de.ft.interitus.Settings;
import de.ft.interitus.Var;

public class CreateWindow {
    public static Window addWindow(String title, ApplicationListener listener) {
        Lwjgl3WindowConfiguration config = new Lwjgl3WindowConfiguration();
        config.useVsync(Settings.Vsync);
        config.setWindowIcon(Files.FileType.Internal, "Icon/interitus.png");
        config.setTitle(title);

        Window createWindow = new Window(listener, config);
        Var.extendsWindows.add(createWindow);
        return createWindow;
    }

}
