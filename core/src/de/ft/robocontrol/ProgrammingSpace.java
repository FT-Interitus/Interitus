package de.ft.robocontrol;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import de.ft.robocontrol.loading.AssetLoader;

public class ProgrammingSpace  implements Screen {

    public ProgrammingSpace() {
        System.out.println("asdasd");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        System.out.println("Test");
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.graphics.setWindowedMode(1,1);


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
