package de.ft.robocontrol;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

public class Welcome extends ScreenAdapter implements Screen {

    public Welcome() {

    }

    @Override
    public void render(float delta) {
        Gdx.graphics.setWindowedMode(Var.w, Var.h);
        Programm.INSTANCE.setScreen(new MainGame());
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
