/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.BlockDropDownMenue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.Block.Block;
import de.ft.interitus.WindowManager;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.check.CheckCollision;
import de.ft.interitus.projecttypes.BlockTypes.BlockMode;
import de.ft.interitus.utils.Unproject;

public class BlockDropDownMenue {
    private int x=0;
    private int y=0;
    private int w=0;
    private int h=0;
    private final static int margin=5;

 private Block block=null;
    private boolean dropped=false;


    private GlyphLayout glyphLayout = new GlyphLayout();
    private int longestText;

    public BlockDropDownMenue(int x, int y, int w, int h,Block block){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.block=block;
    }




    public int getX() {
        return this.x;
    }


    public int getY() {
        return this.y;
    }


    public int getW() {
        return this.w;
    }


    public int getH() {
        return this.h;
    }


    public void setBounds(int x, int y, int w, int h) {
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
    }


    private void longestText() {

        for (BlockMode modi : block.getBlocktype().getBlockModes()) {
            glyphLayout.setText(UI.font, modi.getname());
            if (glyphLayout.width > this.longestText) {
                this.longestText = (int) glyphLayout.width;
            }
        }
    }


    public void draw(Block block) {
        longestText();
        WindowManager.blockBatch.begin();
        WindowManager.blockBatch.draw(block.getBlocktype().getBlockModes().get(block.getBlocktype().actBlockModiIndex).getModiImage(),this.x,this.y,this.w,this.h);
        WindowManager.blockBatch.end();

        if(CheckCollision.checkpointwithobject(this.x,this.y,this.w,this.h, Unproject.unproject().x,Unproject.unproject().y)&&Gdx.input.isButtonJustPressed(0)){
            dropped=!dropped;
        }
        if(dropped) {
            int aktualy = +10;
            WindowManager.BlockshapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            WindowManager.BlockshapeRenderer.setColor(0.3f,0.3f,0.3f,1);
            WindowManager.BlockshapeRenderer.roundendrect(this.x-margin,this.y-21*block.getBlocktype().getBlockModes().size()-10-margin,this.longestText+this.w+5+margin*2, 21*block.getBlocktype().getBlockModes().size()+margin*2, 2);
            WindowManager.BlockshapeRenderer.end();
        for (int i = 0; i < block.getBlocktype().getBlockModes().size(); i++) {
                aktualy += 21;

            if(CheckCollision.checkpointwithobject(this.x, this.y- aktualy, this.longestText+this.w+5+margin*2,this.h, Unproject.unproject().x,Unproject.unproject().y)){
                WindowManager.BlockshapeRenderer.setColor(0f/255f, 101f/255f, 168f/255f,1);

                if(Gdx.input.isButtonPressed(0)){
                    WindowManager.BlockshapeRenderer.setColor(0f/255f, 101f/255f, 100f/255f,1);
                    block.getBlocktype().changeBlockMode(i,block, false);

                }
                WindowManager.BlockshapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                WindowManager.BlockshapeRenderer.rect(this.x, this.y- aktualy, this.longestText+this.w+5+margin*2,this.h);
                WindowManager.BlockshapeRenderer.end();
            }


                WindowManager.blockBatch.begin();
            WindowManager.blockBatch.draw(block.getBlocktype().getBlockModes().get(i).getModiImage(), this.x, this.y- aktualy, this.w,this.h);
            WindowManager.blockBatch.end();

                WindowManager.blockBatch.begin();
                glyphLayout.setText(WindowManager.font, block.getBlocktype().getBlockModes().get(i).getname());
            WindowManager.font.draw(WindowManager.blockBatch, glyphLayout, this.x+this.w+5,this.y-aktualy+glyphLayout.height  + this.h/2- glyphLayout.height/2);
                WindowManager.blockBatch.end();




            }

        }




if(Gdx.input.isButtonPressed(0) && !CheckCollision.checkpointwithobject(this.x,this.y,this.w,this.h, Unproject.unproject().x,Unproject.unproject().y)){
    dropped=false;
}

        }




}
