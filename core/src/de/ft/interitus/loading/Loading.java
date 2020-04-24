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




        if(AssetLoader.manager.update()) {
            AssetLoader.save();
            if(Programm.inLoading==true) {
                Programm.inLoading = false;
                this.dispose();
                Programm.INSTANCE.setScreen(new Welcome());

            }
        }

    }


    public void dispose() {

    }


}
