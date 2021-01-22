/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.Settings;
import de.ft.interitus.WindowManager;

import java.awt.*;

public class SelectionRectDrawer {
    public static void draw(Rectangle rect) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); //Enable Alpha Rendering
        WindowManager.BlockshapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        WindowManager.BlockshapeRenderer.setColor(Settings.theme.ClearColor().r, Settings.theme.ClearColor().g, Settings.theme.ClearColor().b, 0.6f);
        WindowManager.BlockshapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        WindowManager.BlockshapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }
}
