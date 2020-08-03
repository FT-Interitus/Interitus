/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.loading;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import de.ft.interitus.Programm;
import de.ft.interitus.Settings;
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
                Gdx.gl.glClearColor(Settings.theme.ClearColor().r, Settings.theme.ClearColor().g, Settings.theme.ClearColor().b, Settings.theme.ClearColor().a);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
