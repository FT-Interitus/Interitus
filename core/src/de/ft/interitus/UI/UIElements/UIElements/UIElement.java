/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.UIElements;

public abstract class UIElement {

    public int x, y, w, h;
    public int marginLeft, marginTop, marginRight, marginBottom;
    public int margin;
    public float alpha = 1.0f;
    public boolean disabled = false;

    public void setBounds(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public abstract void draw();

    public float getAlpha() {
        return alpha;
    }

    public UIElement setAlpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    public int getH() {
        return h;
    }

    public UIElement setH(int h) {
        this.h = h;
        return this;
    }

    public int getW() {
        return w;
    }

    public UIElement setW(int w) {
        this.w = w;
        return this;
    }

    public int getX() {
        return x;
    }

    public UIElement setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public UIElement setY(int y) {
        this.y = y;
        return this;
    }

    public UIElement setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }


    public UIElement setMargin(int margin) {
        this.marginBottom = margin;
        this.marginLeft = margin;
        this.marginRight = margin;
        this.marginTop = margin;
        this.margin = margin;
        return this;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public UIElement setMarginRight(int marginRight) {
        this.marginRight = marginRight;
        return this;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public UIElement setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
        return this;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public UIElement setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
        return this;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public UIElement setMarginTop(int marginTop) {
        this.marginTop = marginTop;
        return this;
    }

    public UIElement disableUIElement(boolean disabled) {
        this.disabled = disabled;
        return this;
    }
}
