package de.ft.robocontrol.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import de.ft.robocontrol.MainGame;
import de.ft.robocontrol.utils.CheckKollision;
import de.ft.robocontrol.utils.RoundRectangle;
import org.w3c.dom.Text;

import java.awt.event.ActionEvent;
import java.util.EventObject;

public class Button {
     private int x;
     private int y;
    private int w;
    private int h;
    private String text;
    private Texture image=null;
    private boolean touched;

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
         boolean pressed;
        System.out.println(touched);
        if(Gdx.input.isButtonPressed(0)) {
            if (Gdx.input.getX() > x && Gdx.input.getX() < x + w && Gdx.input.getY() > Gdx.graphics.getHeight() - y - h && Gdx.input.getY() < Gdx.graphics.getHeight() - y) {
                if(touched==false) {
                    touched = true;
                }
            }

        }
        if(!Gdx.input.isButtonPressed(0) && touched){

            pressed=true;
touched=false;
        }else{
            pressed=false;
        }


return pressed;

    }


    public boolean isPresseded(){
        if(Gdx.input.getX()>x && Gdx.input.getX()<x+w && Gdx.input.getY()>Gdx.graphics.getHeight()-y-h && Gdx.input.getY()<Gdx.graphics.getHeight()-y && Gdx.input.isButtonPressed(0)){
            return true;
        }else{
            return false;
        }
    }


    public boolean isMouseover(){
        if(Gdx.input.getX()>x && Gdx.input.getX()<x+w && Gdx.input.getY()>Gdx.graphics.getHeight()-y-h && Gdx.input.getY()<Gdx.graphics.getHeight()-y){
            return true;
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
        SpriteBatch batch=new SpriteBatch();
        BitmapFont font=new BitmapFont();
        if(image==null) {
            ShapeRenderer s = new ShapeRenderer();
            s.begin(ShapeRenderer.ShapeType.Filled);
            RoundRectangle.abgerundetesRechteck(s, this.x, this.y, this.w, this.h, 5);
            s.end();
        }else{
            batch.begin();
            batch.draw(image,this.x,this.y,this.w,this.h);
            batch.end();
        }
        if(text!=null) {
            batch.begin();
            font.draw(batch, this.text, x + w / 2 - (this.text.length() / 2) * 6, y + h / 2);
            batch.end();
        }
    }

    public void buttonsetY(int y) {
            this.y = y;
    }

    public void buttonsetX(int x) {
        this.x = x;
    }

    public void buttonsetH(int h) {
        this.h = h;
    }

    public void buttonsetW(int w) {
        this.w = w;
    }

    public int buttongetW() {
        return this.w;
    }

    public int buttongetH() {
        return this.h;
    }

    public int buttongetY() {
        return this.y;
    }

    public int buttongetX() {
        return this.x;
    }


    public class ClickListener extends EventObject {
        public ClickListener(Object source) {
            super(source);
        }
    }

    public interface ClickEvent {
        public void ButtonClicked(ClickListener e);
    }


}


