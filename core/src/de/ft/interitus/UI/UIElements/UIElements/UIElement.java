package de.ft.interitus.UI.UIElements.UIElements;

public interface UIElement {
    int getX();
    int getY();
    int getW();
    int getH();
    void setBounds(int x,int y,int w,int h);
    void draw();

}
