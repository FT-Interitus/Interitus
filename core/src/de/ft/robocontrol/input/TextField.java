package de.ft.robocontrol.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ft.robocontrol.UI.Devicemanagmenttab;
import de.ft.robocontrol.input.check.Check;

public class TextField {
    private int x,y,w,h;
    SpriteBatch b=new SpriteBatch();
    BitmapFont font=new BitmapFont();
    Texture background;
    Texture Backgroundactive;
    Check check=new Check();
    private boolean active=false;
    private String input="default";
    private int begrenzung=10;
    public TextField(int x,int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        background=new Texture("TextFieldBackground.png");
        Backgroundactive=new Texture("TextFieldBackgroundActive.png");

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyTyped (char key) {
                if(active) {

                    if(input.length()<=begrenzung) {
                        input = input + Character.toString(key);
                    }
                }
                return true;
            }

            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {

                return true;
            }
        });

    }

    private void active(){
        if(check.isjustPressed(x,y,w,h)){
            if(active){
                active=false;
            }else{
                active=true;
            }
        }
        if(!check.isMouseover(x,y,w,h) && Gdx.input.isButtonPressed(0)){
            active=false;
        }
    }

    public void draw(){
        active();

        if(active) {

            if (Gdx.input.isKeyPressed(Input.Keys.DEL) && input.length() > 0) {

                input = input.substring(0, input.length() - 1);
            }
        }








        b.begin();
        if(active){
            b.draw(Backgroundactive,x,y,w,h);

        }else {
            b.draw(background,x,y,w,h);

        }
        font.draw(b, input, x + 5, y + font.getLineHeight());

        b.end();
    }
}
