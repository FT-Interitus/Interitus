/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ft.interitus.utils.ShapeRenderer;

public class UIRenderer {
    private SpriteBatch batch;
    private ShapeRenderer renderer;
    public UIRenderer(SpriteBatch batch, ShapeRenderer renderer) {

        this.batch = batch;
        this.renderer = renderer;

    }


    public ShapeRenderer getShapeRenderer() {
        return renderer;
    }

    public SpriteBatch getSpriteBatch() {
        return batch;
    }


    public int calcrealx(UIElement element) {

      return   element.getOrigin().getOriginX()+element.getRelativeX();



    }

    public int calcrealy(UIElement element) {

        return element.getOrigin().getOriginY()+element.getRelativeY();

    }


}
