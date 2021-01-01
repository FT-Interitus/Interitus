/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Animation {
    final TextureRegion[][] regions;
    int speed = 50;
    Texture imganimation;
    Sprite animation;
    int frame = 0;
    int frameCount;
    Timer time = new Timer(speed, new ActionListener() {
        public void actionPerformed(ActionEvent evt) {

            frame++;
            if (frame > frameCount) {
                frame = 0;
            }

            animation.setRegion(regions[0][frame]);
        }
    });

    public Animation(Texture imganimation, int speed, int tileWidth, int tileHeight, int frameCount) {
        this.speed = speed;
        regions = TextureRegion.split(imganimation, tileWidth, tileHeight);
        animation = new Sprite(regions[0][0]);
        this.frameCount = frameCount;
    }

    public void startAnimation() {
        if (!time.isRunning()) {
            time.start();
        }
    }

    public void stopAnimation() {
        if (time.isRunning()) {
            time.stop();
        }
    }

    public Sprite getAnimation() {
        return animation;
    }
}
