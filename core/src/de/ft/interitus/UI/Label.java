/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Label implements UIElement {
    private String text;
    private Table origin;
    int margin_left =0;
    int margin_right =0;
    int margin_top =0;
    int margin_buttom =0;
    public Label() {

    }

    public Label(String text) {
        this.text = text;
    }
    @Override
    public void setOrigin(Table maintabel) {

        origin = maintabel;

    }

    @Override
    public Table getOrigin() {
        return origin;
    }

    @Override
    public void draw(UIRenderer renderer) {



        renderer.getShapeRenderer().begin(ShapeRenderer.ShapeType.Filled);
        renderer.getShapeRenderer().setColor(1,0,0,1);
        renderer.getShapeRenderer().rect(renderer.calcrealx(this),renderer.calcrealy(this),50,50);
        renderer.getShapeRenderer().end();



    }

    @Override
    public int getRelativeX() {
        return 0;
    }

    @Override
    public int getRelativeY() {
        return 0;
    }




    public UIElement marginTop(int margin) {

        this.margin_top = margin;

        return this;
    }


    public UIElement marginLeft(int margin) {
        this.margin_left = margin;
        return this;
    }


    public UIElement marginRight(int margin) {
        this.margin_right = margin;
        return this;
    }


    public UIElement marginBottom(int margin) {
        this.margin_buttom = margin;
        return this;
    }
}
