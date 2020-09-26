/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.UI;

public class PluginDrawer {
    public static void draw() {


        for(PluginRenderer renderer: ProgramRegistry.pluginRenderer) {
            renderer.render(ProgrammingSpace.batch, UI.UIbatch,ProgrammingSpace.BlockshapeRenderer,ProgrammingSpace.shapeRenderer);
        }



    }

    public static void loadimages() {
        for (int i = 0; i < ProgramRegistry.pluginpixmaps.size(); i++) {
            ProgramRegistry.pluginTextures.add(new Texture(ProgramRegistry.pluginpixmaps.get(i)));
        }

    }

}
