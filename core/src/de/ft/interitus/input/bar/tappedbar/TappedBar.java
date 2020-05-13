package de.ft.interitus.input.bar.tappedbar;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.ft.interitus.Var;

import java.util.ArrayList;

public class TappedBar {
    int x=0;
    int y=0;
    int weight=500;
    int height=100;
    Texture img_mappe= new Texture("Bar/Mappe.png");
    SpriteBatch batch = new SpriteBatch();
    ShapeRenderer renderer = new ShapeRenderer();
    BitmapFont font = new BitmapFont();
    ArrayList<TapContent>taps=new ArrayList<>();
    public TappedBar(int x,int y){
        this.x=x;
        this.y=y;

        TapContent tb=new TapContent();
        tb.getTab_button().setImage(img_mappe);
        taps.add(tb);
        taps.add(tb);
        taps.add(tb);

    }
    public TappedBar(int x,int y,int w,int h){
        this.x=x;
        this.y=y;
        this.weight=w;
        this.height=h;
    }

    private void drawBar(){
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.rect(x,y,weight,height);
        renderer.line(x+weight/2,y,x+weight/2,y+height);
        renderer.end();
            for(int i=0;i<taps.size();i++){
                TapContent tb=taps.get(i);
                Vector2 tabbuttonsize=new Vector2(64,15);
                tb.getTab_button().setBounds(x+(int)(((taps.size()*tabbuttonsize.x)/taps.size()*i)+weight/2-taps.size()*tabbuttonsize.x/2),y,(int)tabbuttonsize.x,(int)tabbuttonsize.y);
                tb.getTab_button().draw();
            }
    }

    private void drawContent(){

    }

    public void draw(){
        drawBar();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
