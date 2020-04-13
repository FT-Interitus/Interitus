package de.ft.robocontrol.input.popup;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ft.robocontrol.input.Button;

import java.util.ArrayList;

public class PopupMenue {
    private int x;
    private int y;
    private int buttonheight=20;
    private SpriteBatch batch=new SpriteBatch();
    private ArrayList<Button>buttons=new ArrayList<>();
    private Texture popupButtonimage=new Texture("popupbuttonimage.png");
    public PopupMenue(){

    }
    public PopupMenue(int x,int y){
        this.x=x;
        this.y=y;
    }

    public void setBounds(int x,int y){
        this.x=x;
        this.y=y;
    }
    public void draw(){
        batch.begin();
        for(int i=0;i<buttons.size();i++){
            buttons.get(i).setBounds(this.x,this.y+(buttonheight*i),200,buttonheight);
            buttons.get(i).setImage(popupButtonimage);
            buttons.get(i).draw();
        }
        batch.end();
    }
    public void addItem(String text){
        Button b=new Button();
        b.setText(text);
        buttons.add(b);

    }
    public void setItems(String[] it){
        buttons.clear();
        for(int i=0;i<it.length;i++){
            Button b=new Button();
            b.setText(it[i]);
            buttons.add(b);
        }
    }
    public int getPressed(){
        int p=-1;
        for(int i=0;i<buttons.size();i++){
            if(buttons.get(i).isjustPressed()){
                p=i;
            }
        }
        return p;
    }
}
