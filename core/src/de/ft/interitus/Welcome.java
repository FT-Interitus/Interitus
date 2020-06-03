package de.ft.interitus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ft.interitus.loading.AssetLoader;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static de.ft.interitus.Settings.darkmode;

public class Welcome extends ScreenAdapter implements Screen {


    public static SpriteBatch batch = new SpriteBatch();

    public static boolean forward = false;

    public Welcome() {
        Gdx.graphics.setWindowedMode(Var.w, Var.h);

        final Timer time = new Timer();
        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

               forward = true;
                this.cancel();

            }
        },2000,1000);
    }

    @Override
    public void render(float delta) {


        if (darkmode) {
            Gdx.gl.glClearColor(43f/255f,43f/255f,43f/255f,1);
        } else {
            Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));



 // Programm.INSTANCE.setScreen(new ProgrammingSpace());

        if (darkmode) {

            AssetLoader.welcomefont.setColor(1,1,1,1);
        } else {
            AssetLoader.welcomefont.setColor(0,0,0,1);
        }
    batch.begin();
    AssetLoader.welcomefont.draw(batch,"Hallo " + Var.username,55,Gdx.graphics.getHeight()-50);
    batch.end();

if(forward) {
    Programm.INSTANCE.setScreen(new ProgrammingSpace());
    forward = false;
    super.dispose();
}

    }




    @Override
    public void dispose() {
        super.dispose();
    }
}
