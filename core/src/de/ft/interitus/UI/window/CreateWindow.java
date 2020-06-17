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


    public static void createdebugwindow() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            Var.extendsWindows.get(0).destroy();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.N)) {

            Window window = CreateWindow.addWindow("Debug Window",new ApplicationListener() {
                @Override
                public void create() {

                }

                @Override
                public void resize(int width, int height) {

                }

                @Override
                public void render() {
                    Gdx.gl.glClearColor(Settings.theme.ClearColor().r,Settings.theme.ClearColor().g,Settings.theme.ClearColor().b,Settings.theme.ClearColor().a);

                    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

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

            window.create();






        }
    }
}
