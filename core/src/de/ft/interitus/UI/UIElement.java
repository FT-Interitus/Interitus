/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;

public interface UIElement {
    void setOrigin(Table maintabel);
    Table getOrigin();
    void draw(UIRenderer renderer);
    int getRelativeX();
    int getRelativeY();
    UIElement marginTop(int margin);
    UIElement marginLeft(int margin);
    UIElement marginRight(int margin);
    UIElement marginBottom(int margin);


}
