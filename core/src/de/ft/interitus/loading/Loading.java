package de.ft.interitus.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import de.ft.interitus.Programm;
import de.ft.interitus.Welcome;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.global.GlobalEventManager;
import de.ft.interitus.events.global.GlobalLoadingDoneEvent;
import de.ft.interitus.events.global.GlobalLoadingStartEvent;

public class Loading extends ScreenAdapter implements Screen {
    public Loading() {
        EventVar.globalEventManager.loadingstart(new GlobalLoadingStartEvent(this));

        AssetLoader.load();


    }


    public void render(float delta) {




        if(AssetLoader.manager.update()) {
            AssetLoader.save();
            if(Programm.inLoading==true) {
                Programm.inLoading = false;
                this.dispose();
                EventVar.globalEventManager.loadingdone(new GlobalLoadingDoneEvent(this));
                Programm.INSTANCE.setScreen(new Welcome());

            }
        }

    }


    public void dispose() {

    }


}
