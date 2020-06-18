package de.ft.interitus.UI.window;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowConfiguration;
import com.badlogic.gdx.graphics.GL20;
import de.ft.interitus.Settings;
import de.ft.interitus.Var;

public class CreateWindow {
    public static Window addWindow(String title,ApplicationListener listener) {
         Lwjgl3WindowConfiguration config = new Lwjgl3WindowConfiguration();
         config.useVsync(Settings.Vsync);
         config.setWindowIcon(Files.FileType.Internal, "Icon/iteritus.png");
         config.setTitle(title);

        Window createWindow = new Window(listener,config);
        Var.extendsWindows.add(createWindow);
        return createWindow;
    }

}
