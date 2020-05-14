package de.ft.interitus.UI.input.bar.tappedbar;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

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
    TapContent selectetContent=null;
    public TappedBar(int x,int y){
        this.x=x;
        this.y=y;


        taps.add(new TapContent(img_mappe));
        taps.add(new TapContent(img_mappe));
        taps.add(new TapContent(img_mappe));
        taps.add(new TapContent(img_mappe));
        taps.add(new TapContent(img_mappe));
        taps.add(new TapContent(img_mappe));

        selectetContent=taps.get(0);

    }
    public TappedBar(int x,int y,int w,int h){
        this.x=x;
        this.y=y;
        this.weight=w;
        this.height=h;
    }

    public void addContent(TapContent tc){
        taps.add(tc);
    }

    private void drawBar(){
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.rect(x,y,weight,height);
        renderer.line(x+weight/2,y,x+weight/2,y+height);
        renderer.end();
            for(int i=0;i<taps.size();i++){
                TapContent tb=taps.get(i);
                //Vector2 tabbuttonsize=new Vector2(64,15);
                tb.getTab_button().setW(64);
                tb.getTab_button().setH(15);

                if(tb.getTab_button().isjustPressednormal()){
                    selectetContent=tb;
                }


                if(selectetContent==taps.get(i)){
                    tb.getTab_button().setBounds(x+(((taps.size()*tb.getTab_button().getW())/taps.size()*i)+weight/2-taps.size()*tb.getTab_button().getW()/2),y+height+10,tb.getTab_button().getW(),tb.getTab_button().getH());

                }else{
                    tb.getTab_button().setBounds(x+(((taps.size()*tb.getTab_button().getW())/taps.size()*i)+weight/2-taps.size()*tb.getTab_button().getW()/2),y+height,tb.getTab_button().getW(),tb.getTab_button().getH());

                }
                tb.getTab_button().draw();
            }
    }

    private void drawContent(){
        for(int i=0;i<selectetContent.items.size();i++){

        }
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
