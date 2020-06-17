package de.ft.interitus.UI.window;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowConfiguration;


public class Window {
    ApplicationListener listener;
    Lwjgl3WindowConfiguration config;
    private Lwjgl3Window window;
    public Window(ApplicationListener listener, Lwjgl3WindowConfiguration config) {
        this.listener = listener;
        this.config = config;
    }


    public ApplicationListener getListener() {
        return listener;
    }

    public void setListener(ApplicationListener listener) {
        this.listener = listener;
    }

    public Lwjgl3WindowConfiguration getConfig() {
        return config;
    }

    public void setConfig(Lwjgl3WindowConfiguration config) {
        this.config = config;
    }

   public void create() {
        Lwjgl3Application app = (Lwjgl3Application)Gdx.app;
        window = app.newWindow(listener, config);

    }
    public void destroy() {
        try {
            window.closeWindow();
        }catch (NullPointerException e) {

        }
    }
}
