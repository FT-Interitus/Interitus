/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.UIElements;

public interface UIElement {


    void setBounds(int x, int y, int w, int h);

    void draw();

    void setAlpha(float alpha);

    int getX();

    int getY();

    int getW();

    int getH();

    Object setX(int x);

    Object setY(int y);

    Object setW(int w);

    Object setH(int h);

    Object setPosition(int x, int y);
}
