/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.loading;

import com.badlogic.gdx.ScreenAdapter;
import de.ft.interitus.Program;
import de.ft.interitus.UI.UI;
import de.ft.interitus.Var;
import de.ft.interitus.Welcome;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.global.GlobalLoadingDoneEvent;
import de.ft.interitus.events.global.GlobalLoadingStartEvent;
import de.ft.interitus.projecttypes.BlockTypes.Init;
import org.lwjgl.glfw.GLFW;

public class Loading extends ScreenAdapter {
    public Loading loading = this;

    public Loading() {

        EventVar.globalEventManager.loadingstart(new GlobalLoadingStartEvent(this));



        AssetLoader.load();

    }


    public void render(float delta) {


        try {

            if (AssetLoader.manager.update()) {



                try {
                    AssetLoader.save();
                } catch (Exception e) {

                    Program.logger.severe("Error while saving Assets");
                }
                if (Program.inLoading == true) {
                    Program.inLoading = false;
                    this.dispose();
                    EventVar.globalEventManager.loadingdone(new GlobalLoadingDoneEvent(this));
                    UI.initnachassetsloading();
                    Init.initBlocks();


                    Var.splashscreen.destroy();

                    Var.window.setVisible(true);
                    GLFW.glfwFocusWindow(Var.window.getWindowHandle());
                    Program.INSTANCE.setScreen(Var.welcome);


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }





}
