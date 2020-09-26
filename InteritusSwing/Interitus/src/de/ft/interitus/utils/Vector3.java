package de.ft.interitus.utils;

public class Vector3 {
    public float x=0;
    public float y=0;
    public float z=0;

    public Vector3(float x, float y, float z) {
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public Vector3(){
        
    }
    public void set(float x, float y,float z) {
        this.x = x;
        this.y = y;
        this.z=z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
}
