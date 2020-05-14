package de.ft.interitus.UI.input.bar.tappedbar;

import com.badlogic.gdx.graphics.Texture;

public interface TapItem {
    void draw();
    int getX();
    int getY();
    int getW();
    int getH();
    void setX(int x);
    void setY(int y);
    void setW(int w);
    void setH(int h);
    Texture getImage();
    void setImage(Texture img);
    //void setImage(Texture texture);
    //Object getRefferdObject();
    //void setRefferdObject(Object RefferdObejct);


}
