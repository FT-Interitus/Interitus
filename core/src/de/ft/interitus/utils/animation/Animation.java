package de.ft.interitus.utils.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Animation {
    int speed=50;
     Texture imganimation;
     Sprite animation;
     int frame = 0;
    final TextureRegion[][] regions;

    public Animation(Texture imganimation,int speed,int tileWidth,int tileHeight){
        this.speed=speed;
         regions = TextureRegion.split(imganimation, tileWidth, tileHeight);
        animation = new Sprite(regions[0][0]);
    }

    public void startAnimation(){
        if(!time.isRunning()){
            time.start();
        }
    }
    public void stopAnimation(){
        if(time.isRunning()){
            time.stop();
        }
    }


    Timer time = new Timer( speed, new ActionListener()
    {
        public void actionPerformed(ActionEvent evt )
        {

            frame++;
            if (frame > 3) {
                frame = 0;
            }

            animation.setRegion(regions[0][frame]);
        }
    });

    public Sprite getAnimation() {
        return animation;
    }
}
