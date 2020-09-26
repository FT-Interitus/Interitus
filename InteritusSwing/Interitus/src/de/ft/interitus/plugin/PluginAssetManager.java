/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin;


import java.awt.image.BufferedImage;

public class PluginAssetManager {
    public BufferedImage collectTextureAsset(int id) {
        try {
            return ProgramRegistry.pluginTextures.get(id);
        }catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
