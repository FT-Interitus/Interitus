/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ft.interitus.UI.UI;
import de.ft.interitus.loading.AssetLoader;

import java.util.Timer;
import java.util.TimerTask;


public class Welcome extends ScreenAdapter {


    public static SpriteBatch batch = new SpriteBatch();

    public static boolean forward = false;
    public static boolean firsttime = true;
    public static boolean firstrenderingtime = true;


    @Override
    public void show() {
/*
        final Timer time = new Timer();
        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                forward = true;
                this.cancel();

            }
        }, 2000, 1000);

 */


    }

    @Override
    public void render(float delta) {

        if(firstrenderingtime) {
            firstrenderingtime = false;
            MainRendering.init();
            Var.programingSpace.open();

        }


        Gdx.gl.glClearColor(Settings.theme.WelcomeScreenBackgroundColor().r, Settings.theme.WelcomeScreenBackgroundColor().g, Settings.theme.WelcomeScreenBackgroundColor().b, Settings.theme.WelcomeScreenBackgroundColor().a);


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

       MainRendering.update();





        AssetLoader.welcomefont.setColor(Settings.theme.WelcomeScreenFontColor());


        batch.begin();
        AssetLoader.welcomefont.draw(batch, "Hallo " + Var.username, 55, Gdx.graphics.getHeight() - 50);
        batch.end();

        MainRendering.drawer();

        if (forward) {
            if(firsttime) {
                firsttime = false;



            }
            Var.inProgram=true;
            super.dispose();

            Program.INSTANCE.setScreen(Var.programingSpace);
            forward = false;
        }

    }


    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);


        try {
            UI.updateView(width, height);
        } catch (NullPointerException e) { //Falls die UI nicht initialisiert werden konnte

        }

        UI.UIviewport.update(width, height);


    }

}
