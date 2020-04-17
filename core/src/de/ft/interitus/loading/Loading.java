package de.ft.interitus.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import de.ft.interitus.Programm;
import de.ft.interitus.Welcome;

public class Loading extends ScreenAdapter implements Screen {
    public Loading() {
        AssetLoader.load();


    }


    public void render(float delta) {


        Gdx.gl.glClearColor(1, 1, 1, 1);
       Gdx.graphics.setWindowedMode(1,1);

        if(AssetLoader.manager.update()) {
            AssetLoader.save();
            System.out.println("test1");
            if(Programm.inLoading==true) {
                System.out.println("test2");
                Programm.inLoading = false;
                this.dispose();
                System.out.println("test3");
                Programm.INSTANCE.setScreen(new Welcome());

            }
        }

    }


    public void dispose() {

    }


}
