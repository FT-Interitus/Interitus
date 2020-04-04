package de.ft.robocontrol.input;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextField {
    private int x,y,w,h;
    SpriteBatch b=new SpriteBatch();
    Texture background;
    public TextField(int x,int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        background=new Texture("TextFieldBackground.png");
    }
    public void draw(){
        b.begin();
        b.draw(background,x,y,w,h);
        b.end();
    }
}
