package de.ft.interitus.UI.editor;

import com.badlogic.gdx.ApplicationListener;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.window.CreateWindow;
import de.ft.interitus.UI.window.Window;

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
                UIVar.isdialogeopend = true;
                UIVar.uilocked = true;
            }

            @Override
            public void pause() {

            }

            @Override
            public void resume() {

            }

            @Override
            public void dispose() {
                UI.button_editor.setIsworking(false);

                UIVar.isdialogeopend = false;
                UIVar.uilocked = false;
            }
        });

      window.create();



    }

}
