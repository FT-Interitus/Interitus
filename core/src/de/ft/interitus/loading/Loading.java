package de.ft.interitus.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.GdxRuntimeException;
import de.ft.interitus.Programm;
import de.ft.interitus.Settings;
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

        Gdx.gl.glClearColor(Settings.theme.ClearColor().r,Settings.theme.ClearColor().g,Settings.theme.ClearColor().b,Settings.theme.ClearColor().a);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
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
