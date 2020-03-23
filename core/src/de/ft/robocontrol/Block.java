package de.ft.robocontrol;


public class Block {
    private int x;
    private int y;
    private int w;
    private int h;
    private int index;
    BlockUpdate blockupdate;

    public Block(int index,int x,int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.index = index;
        blockupdate = new BlockUpdate(this);
        blockupdate.start();
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    public void setPosition(int x, int y){
        this.x=x;
        this.y=y;
    }


    public void setWH(int w,int h){
        this.w=w;
        this.h=h;
    }

    public int getIndex() {
        return index;
    }

    public void delete() {
        try {
            System.out.println("cancel eigentlich");
            blockupdate.time.cancel();

        }catch (Exception e) {
            System.out.println("mist");

        }
    }


    public boolean getmousecollision() {
        return blockupdate.toggle;
    }
}
