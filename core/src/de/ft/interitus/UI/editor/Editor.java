package de.ft.interitus.UI.editor;

import com.badlogic.gdx.ApplicationListener;
import de.ft.interitus.UI.window.CreateWindow;
import de.ft.interitus.UI.window.Window;
import de.ft.interitus.Var;

public class Editor {
    public static void open() {

      Window window =  CreateWindow.addWindow("Editor", new ApplicationListener() {
            @Override
            public void create() {

            }

            @Override
            public void resize(int width, int height) {

            }

            @Override
            public void render() {
                Var.isdialogeopend = true;
                Var.uilocked = true;
            }

            @Override
            public void pause() {

            }

            @Override
            public void resume() {

            }

            @Override
            public void dispose() {
                Var.isdialogeopend = false;
                Var.uilocked = false;
            }
        });

      window.create();



    }

}
