package de.ft.interitus.input.popup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ft.interitus.input.Button;
import de.ft.interitus.input.check.Check;

import java.util.ArrayList;

public class PopupMenue {
    private int x;
    private int y;
    private int buttonheight=20;
    private SpriteBatch batch=new SpriteBatch();
    private ArrayList<Button>buttons=new ArrayList<>();
    private Texture popupButtonimage=new Texture("popupbuttonimage.png");
    private boolean show=false;
    private Check check=new Check();

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

    public void rechtsKlickControlle(){
        if(Gdx.input.isButtonJustPressed(1) && !check.isMouseover(this.x,this.y,200,buttonheight*buttons.size())){
            show=true;
            this.x=Gdx.input.getX();
            this.y= Gdx.graphics.getHeight()-Gdx.input.getY();
        }
        if(Gdx.input.isButtonJustPressed(0) && !check.isMouseover(this.x,this.y,200,buttonheight*buttons.size())){
            show=false;
        }
    }

    public void draw(){
        if(show) {
            batch.begin();
            for (int i = 0; i < buttons.size(); i++) {
                buttons.get(i).setBounds(this.x, this.y + (buttonheight * i), 200, buttonheight);
                buttons.get(i).setImage(popupButtonimage);
                buttons.get(i).draw();
            }
            batch.end();
        }
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
