package de.ft.robocontrol.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sun.tools.javac.Main;
import de.ft.robocontrol.MainGame;

import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;

public class Switch {
    private int x;
    private int y;
    private int w=50;
    private int h=25;
    private int wecht=50;
    private int hecht=25;
    private int xecht;
    private int yecht;
    private boolean state=false;
    private boolean ismoving=false;
    private int switchpos=0;
    private int dm=19;
    private int dmecht=19;
    private boolean wackeln=true;
    private boolean visible=true;
    private boolean disable=false;
    private int wackelstärke=1;
    private long wwwave=0;
    private float size=1;
    private int multiplikator=3;
    private int multiplikatorecht=3;
    private float[] farbehebel={1,1,1,1};
    private Vector2 mousesave=new Vector2();
    SpriteBatch b=new SpriteBatch();
    Texture background;
    Texture inside;
    Texture Backgroundgreen;

    private boolean touched;
    Random random = new Random();

    public Switch(int x,int y){
        this.x=x;
        this.y=y;
        this.xecht=x;
        this.yecht=y;

        background=new Texture("switchbackground.png");
        inside=new Texture("switchinside.png");
        Backgroundgreen=new Texture("switchbackgroundgreen.png");
    }
    public Switch(){
        background=new Texture("switchbackground.png");
        inside=new Texture("switchinside.png");
        Backgroundgreen=new Texture("switchbackgroundgreen.png");
        this.xecht=x;
        this.yecht=y;
    }

    public void setBounds(int x,int y){
        this.x=x;
        this.y=y;
        this.xecht=x;
        this.yecht=y;

    }


    public boolean isMouseover(){

            if (Gdx.input.getX() > x && Gdx.input.getX() < x + w && Gdx.input.getY() > Gdx.graphics.getHeight() - y - h && Gdx.input.getY() < Gdx.graphics.getHeight() - y) {
                return true;
            } else {
                return false;
            }

    }

    public boolean isjustPressed(){
        boolean pressed=false;

            System.out.println(touched);
            if (Gdx.input.isButtonPressed(0)) {
                if (Gdx.input.getX() > x && Gdx.input.getX() < x + w && Gdx.input.getY() > Gdx.graphics.getHeight() - y - h && Gdx.input.getY() < Gdx.graphics.getHeight() - y) {
                    if (touched == false) {
                        mousesave.set(Gdx.input.getX(),Gdx.input.getY());
                        touched = true;
                    }
                }

            }
            if (!Gdx.input.isButtonPressed(0) && Math.abs(Gdx.input.getX()-mousesave.x)<1 && Math.abs(Gdx.input.getY()-mousesave.y)<1 && touched) {

                pressed = true;
                touched = false;
            } else {
                pressed = false;
            }
            if( Math.abs(Gdx.input.getX()-mousesave.x)>1 && Math.abs(Gdx.input.getY()-mousesave.y)>1 && touched && !Gdx.input.isButtonPressed(0) ){
                pressed=false;
                touched=false;
            }
            if( Gdx.input.getX()!=mousesave.x && Gdx.input.getY()!=mousesave.y && Gdx.input.isButtonPressed(0)   && touched ){
                System.out.println("pipipipipipkakakakakakakipipipipipi");
                switchpos=(int)(Gdx.input.getX()-mousesave.x);
                if(switchpos<=0){
                    switchpos=0;
                }
                if(switchpos>=w-dm-multiplikator-multiplikator){
                    switchpos=w-dm-multiplikator-multiplikator;
                }
            }
            if(switchpos>13){
                state=true;
            }else{
                state=false;
            }



        return pressed;

    }

    private void update(){
        if(isjustPressed()){
            if(disable==false) {
                if (state) {
                    state = false;
                } else {
                    state = true;
                }
            }else{
                wwwave=System.currentTimeMillis()+500;
            }
        }
    }

    public void draw(){

        update();


            if(System.currentTimeMillis()<wwwave){
                x = xecht + (wackelstärke*-1 + random.nextInt(wackelstärke*3));
                y = yecht + (wackelstärke*-1 + random.nextInt(wackelstärke*3));
            }else{
                if(wwwave!=0) {
                    x = xecht;
                    y = yecht;
                wwwave=0;
                }

            }



if(visible) {
    b.begin();
    b.setColor(1,1,1,1-(1f/26f)*(float)switchpos);
    b.draw(background, x, y, w, h);
    b.setColor(1,1,1,(1f/26f)*(float)switchpos);
    b.draw(Backgroundgreen, x, y, w, h);

    b.setColor(farbehebel[0],farbehebel[1],farbehebel[2],farbehebel[3]);
    b.draw(inside, x + multiplikator + switchpos, y + multiplikator, dm, dm);
    b.end();
}

        if(state==false && switchpos >=0){
            switchpos=switchpos-1;
            ismoving=true;
        }else if(state==true && switchpos <= w-dm-multiplikator-multiplikator){
            switchpos=switchpos+1;
            ismoving=true;
        }else{
            ismoving=false;
        }
System.out.println(switchpos);
        if(ismoving==true){

            //x=x+(-1+random.nextInt(3));
           // y=y+(-1+random.nextInt(3));

if(wackeln==true) {
    x = xecht + (wackelstärke*-1 + random.nextInt(wackelstärke*3));
    y = yecht + (wackelstärke*-1 + random.nextInt(wackelstärke*3));
}

        }else{
            x=xecht;
            y=yecht;

        }

    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setWackeln(boolean wackeln) {
        this.wackeln = wackeln;
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

    public int getXecht() {
        return xecht;
    }

    public int getYecht() {
        return yecht;
    }

    public boolean isIsmoving() {
        return ismoving;
    }

    public boolean isWackeln() {
        return wackeln;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setWackelstärke(int wackelstärke) {
        this.wackelstärke = wackelstärke;
    }

    public int getWackelstärke() {
        return wackelstärke;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public void setSize(float size) {
        this.size = size;
        w=(int)(wecht*size);
        h=(int)(hecht*size);
        dm=(int)(dmecht*size);
        multiplikator=(int)(multiplikatorecht*size);

    }

    public float getSize() {
        return size;
    }


    public void setHebelColor(float r,float g,float b,float a){
        farbehebel[0]=r;
        farbehebel[1]=g;
        farbehebel[2]=b;
        farbehebel[3]=a;
    }

    public void setBackground(Texture background) {
        this.background = background;
    }

    public void setBackgroundgreen(Texture backgroundgreen) {
        Backgroundgreen = backgroundgreen;
    }

    public void setInside(Texture inside) {
        this.inside = inside;
    }
}
