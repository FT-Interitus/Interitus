package de.ft.robocontrol.Button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.swing.*;

public class IntegerAuswahl {
    private int x,y,w,h;
    private SpriteBatch b=new SpriteBatch();
    private Texture Textfeldmitte;
    private Texture ButtonObenImage;
    private Texture ButtonUntenImage;
    private Button buttonOben=new Button();
    private Button buttonUnten=new Button();
    private BitmapFont font;
    private int value=0;
    private int grenzel=-999;
    private int grenzer=999;

    public IntegerAuswahl(int x, int y, int w, int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        Textfeldmitte=new Texture("IntegerAuswahlMitte.png");
        ButtonObenImage=new Texture("IntegerAuswahlOben.png");
        ButtonUntenImage=new Texture("IntegerAuswahlUnten.png");
        font=new BitmapFont();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setW(int w) {
        this.w = w;
    }
    public void draw(){
        if(buttonOben.isjustPressed() && value<grenzer){
            value++;
        }
        if(buttonUnten.isjustPressed() && value>grenzel){
            value--;
        }

        buttonOben.setBounds(x,y+h,50,13);
        buttonOben.setImage(ButtonObenImage);
        buttonUnten.setBounds(x,y-13,50,13);
        buttonUnten.setImage(ButtonUntenImage);
        b.begin();
        b.draw(Textfeldmitte,x,y,w,h);
        b.end();
        buttonOben.draw();
        buttonUnten.draw();
        b.begin();
        font.draw(b,""+value,x+5,y+font.getLineHeight());
        b.end();

    }

    public void setGrenzen(int l,int r){
        this.grenzel=l;
        this.grenzer=r;
    }


    public void setValue(int value) {
        this.value = value;
    }

    public void setButtonObenImage(Texture buttonObenImage) {
        ButtonObenImage = buttonObenImage;
    }

    public void setButtonUntenImage(Texture buttonUntenImage) {
        ButtonUntenImage = buttonUntenImage;
    }

    public void setTextfeldmitte(Texture textfeldmitte) {
        Textfeldmitte = textfeldmitte;
    }
}
