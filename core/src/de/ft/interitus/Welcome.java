/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ft.interitus.loading.AssetLoader;

import java.util.Timer;
import java.util.TimerTask;


public class Welcome extends ScreenAdapter {


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
        }, 0, 1000);
    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(Settings.theme.WelcomeScreenBackgroundColor().r, Settings.theme.WelcomeScreenBackgroundColor().g, Settings.theme.WelcomeScreenBackgroundColor().b, Settings.theme.WelcomeScreenBackgroundColor().a);


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));


        // Programm.INSTANCE.setScreen(new ProgrammingSpace());


        AssetLoader.welcomefont.setColor(Settings.theme.WelcomeScreenFontColor());


        batch.begin();
        AssetLoader.welcomefont.draw(batch, "Hallo " + Var.username, 55, Gdx.graphics.getHeight() - 50);
        batch.end();

        if (forward) {
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
