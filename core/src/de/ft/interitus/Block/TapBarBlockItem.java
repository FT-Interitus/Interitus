package de.ft.interitus.Block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.input.bar.tappedbar.TapItem;
import de.ft.interitus.UI.input.check.Check;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;


public class TapBarBlockItem implements TapItem {
    int x;
    int y;
    int w=40;
    int h=50;
    Texture img;
    PlatformSpecificBlock psb;
    SpriteBatch batch=new SpriteBatch();
    Check check = new Check();

    public TapBarBlockItem(PlatformSpecificBlock psb,Texture img){
        this.img=img;
        this.psb=psb;
    }

    @Override
    public void draw() {

        if(check.isJustPressedNormal(x,y,w,h)){
            Block tempblock = new Block(BlockVar.blocks.size(),(int)ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0)).x,(int)ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0)).y,150,70,psb);
            BlockVar.blocks.add(tempblock);
            tempblock.setMarked(true);
            tempblock.setMoving(true);

            BlockVar.unterschiedsave.set(150/2,70/2);
        }

        batch.begin();
        batch.draw(img,this.x,this.y,this.w,this.h);
        batch.end();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getW() {
        return w;
    }

    @Override
    public int getH() {
        return h;
    }

    @Override
    public void setX(int x) {
        this.x=x;
    }

    @Override
    public void setY(int y) {
        this.y=y;
    }

    @Override
    public void setW(int w) {
        this.w=w;
    }

    @Override
    public void setH(int h) {
        this.h=h;
    }


}
