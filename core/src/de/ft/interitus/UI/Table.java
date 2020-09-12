/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;


/**
 * If a UIElement implements this Interface it is possible that the UIElement will contain other UIElements
 */
public interface Table {
       void draw();
     void add(UIElement element);
     int getOriginX();
     int getOriginY();
     UIRenderer getRenderer();

}
