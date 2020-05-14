package de.ft.interitus.Block;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.input.bar.tappedbar.TapItem;

public class TapBarBlockItem implements TapItem {
    int x;
    int y;
    int w;
    int h;
    Texture img;
    SpriteBatch batch=new SpriteBatch();
    ShapeRenderer renderer = new ShapeRenderer();

    @Override
    public void draw() {
        batch.begin();
        batch.end();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getW() {
        return w;
    }

    @Override
    public int getH() {
        return h;
    }

    @Override
    public void setX(int x) {
        this.x=x;
    }

    @Override
    public void setY(int y) {
        this.y=y;
    }

    @Override
    public void setW(int w) {
        this.w=w;
    }

    @Override
    public void setH(int h) {
        this.h=h;
    }

    @Override
    public Texture getImage() {
        return img;
    }

    @Override
    public void setImage(Texture img) {
        this.img=img;
    }
}
