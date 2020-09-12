/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ft.interitus.utils.ShapeRenderer;

import java.util.ArrayList;

public class Stage implements Table {

   private final ArrayList<UIElement> elements = new ArrayList<>();
    private final UIRenderer renderer;


    private int origin_x =0;
    private int origin_y=0;
    private int w=0;
    private int h=0;

    public Stage() {


        renderer = new UIRenderer(new SpriteBatch(),new ShapeRenderer());

    }

    public Stage(SpriteBatch spriteBatch,ShapeRenderer shapeRenderer) {

        renderer = new UIRenderer(spriteBatch,shapeRenderer);

    }

    public void add(UIElement element) {

        element.setOrigin(this);

        elements.add(element);

    }



    public void setOrigin(int x,int y) {
        this.origin_x = x;
        this.origin_y = y;

    }


    public void draw() {

        for(int i=0;i<elements.size();i++) {

            elements.get(i).draw(renderer);
        }



    }

    @Override
    public int getOriginX() {
        return origin_x;
    }

    @Override
    public int getOriginY() {
        return origin_y;
    }

    @Override
    public UIRenderer getRenderer() {
        return renderer;
    }
}
