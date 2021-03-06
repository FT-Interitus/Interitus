/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ft.interitus.utils.ShapeRenderer;

public interface PluginRenderer {
    void render(SpriteBatch ProgramBatch, SpriteBatch UIBatch, ShapeRenderer ProgramShapeRenderer, ShapeRenderer UIShapeRenderer);
}
