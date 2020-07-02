package de.ft.interitus.loading;

import com.badlogic.gdx.ApplicationListener;
import de.ft.interitus.Programm;
import de.ft.interitus.UI.window.CreateWindow;
import de.ft.interitus.UI.window.Window;

public class SplashScreen {
    public static Window create() {
        Window window;

        window = CreateWindow.addWindow("Loading", new ApplicationListener() {
            @Override
            public void create() {
                Programm.logger.config("Created Splash Screen");
            }

            @Override
            public void resize(int width, int height) {

            }

            @Override
            public void render() {

            }

            @Override
            public void pause() {

            }

            @Override
            public void resume() {

            }

            @Override
            public void dispose() {

            }
        });


        window.getConfig().setWindowPosition(-1, -1);
        window.getConfig().setDecorated(false);


        window.create();
        return window;
    }
}
