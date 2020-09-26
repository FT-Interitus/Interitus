package de.ft.interitus.utils;

import java.util.Vector;

public class Vector2 {
    public float x=0;
    public float y=0;

    public Vector2(float x, float y) {
        this.x=x;
        this.y=y;
    }
    public Vector2(){
        
    }
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
