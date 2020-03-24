package de.ft.robocontrol;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Block {
    private boolean marked=false;
    private int x;
    private int y;
    private int w;
    private int h;
    private int index;
    BlockUpdate blockupdate;
   private Block left = null;
   private Block right = null;
    public Block(int index,int x,int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.index = index;
        blockupdate = new BlockUpdate(this);
        blockupdate.start();
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setLeft(Block left) {
        if(this.left!=left) {
            this.left = left;
        }
        if(left.getRight()!=this) {
            left.setRight(this);
        }
    }

    public void setRight(Block right) {
        if(this.right!=right) {
            this.right = right;
        }
        if(right.getLeft()!=this) {
            right.setLeft(this);
        }
    }

    public Block getLeft() {
        return left;
    }

    public Block getRight() {
        return right;
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

        if(left!=null) {
            left.setRight(null);
        }

        if(right!=null) {
            right.setLeft(null);
        }

        left = null;
        right = null;

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

    public void draw(SpriteBatch batch) {
        if(!this.blockupdate.toggle) {
            batch.draw(MainGame.img_block, this.getX(), this.getY(), this.getW(), this.getH());
        }else{
            batch.draw(MainGame.img_selected, this.getX(), this.getY(), this.getW(), this.getH());
        }
    }
}
