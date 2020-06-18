package de.ft.interitus.UI.window;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowConfiguration;
import de.ft.interitus.Var;


public class Window {
    private ApplicationListener listener;
    private Lwjgl3WindowConfiguration config;
    private Lwjgl3Window window;
   protected Window(ApplicationListener listener, Lwjgl3WindowConfiguration config) {
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

        Thread createWindow = new Thread() {
            @Override
            public void run() {
                Lwjgl3Application app = (Lwjgl3Application)Gdx.app;
                window =app.newWindow(listener,config);
            }


        };
       createWindow.start();

    }
    public void destroy() {
        try {
            window.closeWindow();
            Var.extendsWindows.remove(this);
        }catch (NullPointerException e) {

        }
    }
}
