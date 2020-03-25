package de.ft.robocontrol;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import jdk.tools.jaotc.Main;

public class Block {

    private boolean marked=false;
    private int x;
    private int y;
    private int w;
    private int h;
    private int index;
    private boolean showdupulicate;
    private int x_dup;
    private boolean moving=false;
   private BlockUpdate blockupdate;
   private Block left = null;
   private Block right = null;
    public Block(int index,int x,int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.x_dup = this.x + this.w;

        this.index = index;
        blockupdate = new BlockUpdate(this);
        blockupdate.start();
    }

    public int getX_dup() {
        return x_dup;
    }

    public boolean isMoving() {
        return moving;
    }

    public boolean isShowdupulicate() {
        return showdupulicate;
    }

    public void setShowdupulicate(boolean showdupulicate) {
        this.showdupulicate = showdupulicate;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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
        if(left!=null) {
            if (left.getRight() != this) {
                left.setRight(this);
            }
        }
    }

    public void setRight(Block right) {
        if(this.right!=right) {
            this.right = right;
        }

        if(right!=null) {
            if (right.getLeft() != this) {
                right.setLeft(this);
            }
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
        this.x_dup = this.x + this.w;
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
        this.x_dup = this.x + this.w;
    }


    public void setWH(int w,int h){
        this.w=w;
        this.h=h;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void delete() {
        Var.markedblock=null;
        Var.marked=false;
        Var.ismoving=false;


        if(left!=null) {
            left.setRight(null);
        }

        if(right!=null) {
            right.setLeft(null);
        }

        left = null;
        right = null;

        try {
            //System.out.println("cancel eigentlich");
            blockupdate.time.cancel();

        }catch (Exception e) {
            System.out.println("mist");

        }
        int temp = MainGame.blocks.indexOf(this);
        MainGame.blocks.remove(MainGame.blocks.indexOf(this));
        for(int i = 0;i<MainGame.blocks.size()-temp;i++) {
            MainGame.blocks.get(i).setIndex(MainGame.blocks.get(i).getIndex()-1);
            //System.out.println("Noch nicht abgestürtzt!");
        }

    }


    public boolean getmousecollision() {
        return blockupdate.toggle;
    }

    public void draw(SpriteBatch batch, ShapeRenderer shape) {
        if(!this.blockupdate.toggle) {
            batch.draw(MainGame.img_block, this.getX(), this.getY(), this.getW(), this.getH());
        }  else{
        batch.draw(MainGame.img_mouseover, this.getX(), this.getY(), this.getW(), this.getH());
    }

        if(this.isMarked()) {
            batch.draw(MainGame.img_marked, this.getX(), this.getY(), this.getW(), this.getH());
        }
if(this.isShowdupulicate()) {
    batch.setColor(1,1,1,0.5f);
    batch.draw(MainGame.img_block, this.x_dup, this.y, this.getW(), this.getH());
    batch.setColor(1,1,1,1);
}

if(this.getLeft()!=null){
    batch.end();
    shape.begin(ShapeRenderer.ShapeType.Filled);
    shape.setColor(1f,0.4f,0.4f,0.4f);
    shape.ellipse(this.getX()-6,this.getY()+this.getH()/2-6,12,12);
    shape.end();
    batch.begin();
}
        if(this.getRight()!=null){
            batch.end();
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(1f,0.4f,0.4f,0.4f);
            shape.ellipse(this.getX()-6+this.getW(),this.getY()+this.getH()/2-6,12,12);
            shape.end();
            batch.begin();
        }


}}
