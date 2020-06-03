package de.ft.interitus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import static de.ft.interitus.Settings.darkmode;

public class Welcome extends ScreenAdapter implements Screen {

    public Welcome() {
        Gdx.graphics.setWindowedMode(Var.w, Var.h);
    }

    @Override
    public void render(float delta) {


        if (darkmode) {
            Gdx.gl.glClearColor(43f/255f,43f/255f,43f/255f,1);
        } else {
            Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));



  Programm.INSTANCE.setScreen(new ProgrammingSpace());


    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
