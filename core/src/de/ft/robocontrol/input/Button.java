package de.ft.robocontrol.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.ft.robocontrol.input.check.Check;
import de.ft.robocontrol.utils.RoundRectangle;

public class Button {
     private int x;
     private int y;
    private int w;
    private int h;
    private String text;
    private Texture image=null;

    private boolean visible=true;
    private boolean disable=false;
    private SpriteBatch batch=new SpriteBatch();
    private ShapeRenderer s=new ShapeRenderer();
    private GlyphLayout glyphLayout = new GlyphLayout();


    private Check check=new Check();



    BitmapFont font = new BitmapFont();


    public Button(int x,int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;

    }
    public Button(){


    }

    public void setBounds(int x,int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;

    }

    public boolean isjustPressed(){
        boolean pressed=false;
if(!disable) {

pressed=check.isjustPressed(x,y,w,h);

}else{
    return false;
}

        return pressed;

    }


    public boolean isPresseded(){
        if(!disable) {

return check.isPressed(x,y,w,h);
        }else{
            return false;
        }
    }


    public boolean isMouseover(){
        if(!disable) {

            return check.isMouseover(x,y,w,h);
        }else{
            return false;
        }
    }

    public void setText(String text){
        this.text=text;
    }

    public void setImage(Texture image){
        this.image=image;
    }

    public void draw(){
        if(isVisible()) {
            if (image == null) {

                s.begin(ShapeRenderer.ShapeType.Filled);
                RoundRectangle.abgerundetesRechteck(s, this.x, this.y, this.w, this.h, 5);
                s.end();
            } else {
                batch.begin();
                if (isMouseover()) {
                    batch.setColor(1, 1, 1, 0.5f);
                } else {
                    batch.setColor(1, 1, 1, 1);
                }
                if (isMouseover() && Gdx.input.isButtonPressed(0)) {
                    batch.setColor(1, 0.5f, 0.5f, 1);
                }
                if(isDisable()){
                    batch.setColor(1,1,1,0.2f);
                }
                batch.draw(image, this.x, this.y, this.w, this.h);
                batch.end();
            }
            if (text != null) {
                batch.begin();

                glyphLayout.setText(font, this.text);
                font.draw(batch, glyphLayout, x + 5, y + glyphLayout.height + h / 2 - glyphLayout.height / 2);

                batch.end();
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    public String getText() {
        return text;
    }

    public Texture getImage() {
        return image;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}


