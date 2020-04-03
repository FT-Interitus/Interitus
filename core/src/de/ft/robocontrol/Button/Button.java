package de.ft.robocontrol.Button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.robocontrol.utils.RoundRectangle;
import org.w3c.dom.Text;

public class Button {
    public int x;
    public int y;
    public int w;
    public int h;
    public Texture image=null;
    public Button(int x,int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
    }

    public void setImage(Texture image){
        this.image=image;
    }

    public void draw(){
        if(image==null) {
            ShapeRenderer s = new ShapeRenderer();
            s.begin(ShapeRenderer.ShapeType.Filled);
            RoundRectangle.abgerundetesRechteck(s, this.x, this.y, this.w, this.h, 5);
            s.end();
        }else{
            SpriteBatch batch=new SpriteBatch();
            batch.begin();
            batch.draw(image,this.x,this.y,this.w,this.h);
            batch.end();
        }
    }

    public void setY(int y) {
            this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
