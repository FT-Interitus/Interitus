package de.ft.robocontrol.loading;

import com.badlogic.gdx.*;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.ft.robocontrol.MainGame;
import de.ft.robocontrol.Programm;
import de.ft.robocontrol.Var;
import de.ft.robocontrol.Welcome;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GLContext;



public class Loading extends ScreenAdapter implements Screen {


public Loading() {
    AssetLoader.load();

}


    public void show() {

    }


    public void render(float delta) {


        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.graphics.setWindowedMode(1,1);
        if(AssetLoader.manager.update()) {
            AssetLoader.save();

            if(Programm.inLoading==true) {
                System.out.println("Hier");
                Programm.inLoading = false;
                this.dispose();
                Programm.INSTANCE.setScreen(new Welcome());

            }
        }

    }


    public void dispose() {

    }

    public void hide() {
        this.dispose();
    }

}
