/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.UIElements;

public abstract class UIElement {

     public int x, y, w, h;
    public int marginLeft, marginTop, marginRight, marginBottom;
    public int margin;
    public float alpha=1.0f;

    public final void setBounds(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public abstract void draw();

    public final float getAlpha() {
        return alpha;
    }

    public final Object setAlpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    public final int getH() {
        return h;
    }

    public final Object setH(int h) {
        this.h = h;
        return this;
    }

    public final int getW() {
        return w;
    }

    public final Object setW(int w) {
        this.w = w;
        return this;
    }

    public final int getX() {
        return x;
    }

    public final Object setX(int x) {
        this.x = x;
        return this;
    }

    public final int getY() {
        return y;
    }

    public final Object setY(int y) {
        this.y = y;
        return this;
    }

    public final Object setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }


    public final Object setMargin(int margin) {
        this.marginBottom = margin;
        this.marginLeft = margin;
        this.marginRight = margin;
        this.marginTop = margin;
        this.margin = margin;
        return this;
    }

    public final int getMarginRight() {
        return marginRight;
    }

    public final Object setMarginRight(int marginRight) {
        this.marginRight = marginRight;
        return this;
    }

    public final int getMarginBottom() {
        return marginBottom;
    }

    public final Object setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
        return this;
    }

    public final int getMarginLeft() {
        return marginLeft;
    }

    public final Object setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
        return this;
    }

    public final int getMarginTop() {
        return marginTop;
    }

    public final Object setMarginTop(int marginTop) {
        this.marginTop = marginTop;
        return this;
    }
}
