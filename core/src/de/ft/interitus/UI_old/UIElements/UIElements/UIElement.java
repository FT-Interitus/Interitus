/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI_old.UIElements.UIElements;

public interface UIElement {
    int getX();

    int getY();

    int getW();

    int getH();

    void setBounds(int x, int y, int w, int h);

    void draw();

    void setAlpha(float alpha);

}
