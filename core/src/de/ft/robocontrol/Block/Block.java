package de.ft.robocontrol.Block;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.robocontrol.MainGame;
import de.ft.robocontrol.ThreadManager;
import de.ft.robocontrol.data.user.changes.DataManager;
import de.ft.robocontrol.utils.CheckKollision;

public class Block {
    public boolean seted=true;
    public boolean moved=false;
    private boolean marked=false;
    private boolean biggestarea=false;
    private int x;
    private int y;
    private int w;
    private int h;
    private int index;
    private boolean showdupulicate_rechts;
    private boolean showdupulicate_links;
    private int x_dup_rechts;
    private int x_dup_links;
    private boolean moving=false;
   private BlockUpdate blockupdate;
   private Block left = null;
   private Block right = null;
    public Block(int index,int x,int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.x_dup_rechts = this.x + this.w;
        this.x_dup_links = this.x-this.w;




        this.index = index;
        blockupdate = new BlockUpdate(this);
        blockupdate.start();
        ThreadManager.add(blockupdate, this);
        ThreadManager.requeststart(0);
    }

    public BlockUpdate getBlockupdate() {
        return blockupdate;
    }

    public int getX_dup_rechts() {
        return x_dup_rechts;
    }

    public int getX_dup_links() {
        return x_dup_links;
    }

    public boolean isMoving() {
        return moving;
    }

    public boolean isShowdupulicate_rechts() {
        return showdupulicate_rechts;
    }

    public boolean isShowdupulicate_links() {
        return showdupulicate_links;
    }

    public void setShowdupulicate_rechts(boolean showdupulicate_rechts) {
        this.showdupulicate_rechts = showdupulicate_rechts;
    }

    public void setShowdupulicate_links(boolean showdupulicate_links) {
        this.showdupulicate_links = showdupulicate_links;
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
        this.x_dup_rechts = this.x + this.w;
        this.x_dup_links = this.x - this.w;
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
        this.x_dup_rechts = this.x + this.w;
        this.x_dup_links = this.x - this.w;
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
        BlockVar.markedblock=null;
        BlockVar.marked=false;
        BlockVar.ismoving=false;

        this.setIndex(-1);

        DataManager.change(this, false, true);

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
            blockupdate.block = null;
            blockupdate.time.cancel();
            blockupdate.interrupt();
        }catch (Exception e) {
            System.out.println("mist");

        }
        int temp = BlockVar.blocks.indexOf(this);
        BlockVar.blocks.remove(BlockVar.blocks.indexOf(this));
        for(int i = 0;i<BlockVar.blocks.size()-temp;i++) {
            BlockVar.blocks.get(i).setIndex(BlockVar.blocks.get(i).getIndex()-1);

        }

        try{
            BlockVar.blocks.remove(BlockVar.blocks.indexOf(this));
        }catch (Exception e) {
            System.out.println("Block schon überschrieben");
        }

    }


    public boolean getmousecollision() {
        return blockupdate.toggle;
    }

    public void draw(SpriteBatch batch, ShapeRenderer shape, BitmapFont font) {




        if(!this.blockupdate.toggle) {
            batch.draw(MainGame.img_block, this.getX(), this.getY(), this.getW(), this.getH());
        }  else{
        batch.draw(MainGame.img_mouseover, this.getX(), this.getY(), this.getW(), this.getH());
    }

        if(this.isMarked()) {
            batch.draw(MainGame.img_marked, this.getX(), this.getY(), this.getW(), this.getH());
        }
        if(BlockVar.biggestblock==this) {
            if (this.isShowdupulicate_rechts()) {
                batch.setColor(1, 1, 1, 0.5f);
                batch.draw(MainGame.img_block, this.x_dup_rechts, this.y, this.getW(), this.getH());
                batch.setColor(1, 1, 1, 1);
            }

            if (this.isShowdupulicate_links()) {
                batch.setColor(1, 1, 1, 0.5f);
                batch.draw(MainGame.img_block, this.x_dup_links, this.y, this.getW(), this.getH());
                batch.setColor(1, 1, 1, 1);
            }
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
        if(this== BlockVar.biggestblock) {
            batch.end();
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.rect(x, y, 20, 20);
            shape.end();
            batch.begin();
        }


        font.draw(batch, "index:  "+this.getIndex(), this.getX()+30, this.getY()+30);
}



public int getDublicatmarkedblockuberlappungsflache(){
        int flaeche=0;
    if(this.isShowdupulicate_rechts()) {

        try {

            flaeche=(CheckKollision.flache(this.getX_dup_rechts(),this.getY(),this.getW(),this.getH(), BlockVar.markedblock.getX(), BlockVar.markedblock.getY()));

        } catch (NullPointerException e) {

        }


    }


    if(this.isShowdupulicate_links()) {
        try {

            flaeche=(CheckKollision.flache(this.getX_dup_links(),this.getY(),this.getW(),this.getH(), BlockVar.markedblock.getX(), BlockVar.markedblock.getY()));

        } catch (NullPointerException e) {

        }

    }
    return flaeche;
}
public int getBlockMarkedblockuberlappungsflache(){
        int flaeche=0;
        try {
            flaeche = CheckKollision.flache(this.getX(), this.getY(), this.getW(), this.getH(), BlockVar.markedblock.getX(), BlockVar.markedblock.getY());
        }catch (NullPointerException e){}
        return flaeche;
}



}
