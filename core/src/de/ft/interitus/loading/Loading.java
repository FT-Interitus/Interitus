package de.ft.interitus.loading;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.GdxRuntimeException;
import de.ft.interitus.Programm;
import de.ft.interitus.UI.UI;
import de.ft.interitus.Welcome;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.global.GlobalLoadingDoneEvent;
import de.ft.interitus.events.global.GlobalLoadingStartEvent;

public class Loading extends ScreenAdapter implements Screen {
    public Loading() {
        EventVar.globalEventManager.loadingstart(new GlobalLoadingStartEvent(this));

        AssetLoader.load();


    }


    public void render(float delta) {

try {
    if (AssetLoader.manager.update()) {
        AssetLoader.save();
        if (Programm.inLoading == true) {
            Programm.inLoading = false;
            this.dispose();
            EventVar.globalEventManager.loadingdone(new GlobalLoadingDoneEvent(this));
            UI.initnachassetsloading();
            Programm.INSTANCE.setScreen(new Welcome());

        }
    }
}catch (GdxRuntimeException e) {
    e.printStackTrace();
}

    }


    public void dispose() {

    }


}
