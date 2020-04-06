package de.ft.robocontrol;

import com.badlogic.gdx.*;


public class Welcome extends ScreenAdapter implements Screen {


    public Welcome() {
        Programm.inWelcome = true;
       Programm.inWelcome = false; //TODO hier ändern

        Gdx.graphics.setWindowedMode(Var.w,Var.h);

    }




    public void render(float delta) {
        Programm.INSTANCE.setScreen(new ProgrammingSpace()); //TODO hier ändern
;
        if(Settings.darkmode) {
            Gdx.gl.glClearColor(0.23f, 0.23f, 0.23f, 1);
        }else{
            Gdx.gl.glClearColor(1, 1, 1, 1);
        }
    }


    public void resize(int width, int height) {

    }


    public void hide() {

    }

    public void dispose() {

    }
}
