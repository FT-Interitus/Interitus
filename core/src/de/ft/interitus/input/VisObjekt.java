package de.ft.interitus.input;

public interface VisObjekt {

    int getX();
    int getY();
    int getW();
    int getH();
    void setBounds(int x,int y,int w,int h);
    void draw();



}