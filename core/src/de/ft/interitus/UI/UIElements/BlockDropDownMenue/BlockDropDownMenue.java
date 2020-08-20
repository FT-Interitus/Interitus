/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.BlockDropDownMenue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.Block.Block;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.UIElements.UIElement;
import de.ft.interitus.UI.UIElements.check.CheckKollision;
import de.ft.interitus.projecttypes.BlockTypes.BlockModi;
import de.ft.interitus.utils.Unproject;

public class BlockDropDownMenue implements UIElement {
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



    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getW() {
        return this.w;
    }

    @Override
    public int getH() {
        return this.h;
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
    }


    private void longestText() {

        for (BlockModi modi : block.getBlocktype().getBlockModis()) {
            glyphLayout.setText(UI.font, modi.getname());
            if (glyphLayout.width > this.longestText) {
                this.longestText = (int) glyphLayout.width;
            }
        }
    }

    @Override
    public void draw() {
        longestText();
        ProgrammingSpace.batch.begin();
        ProgrammingSpace.batch.draw(block.getBlocktype().getBlockModis().get(block.getBlocktype().actBlockModiIndex).getModiImage(),this.x,this.y,this.w,this.h);
        ProgrammingSpace.batch.end();

        if(CheckKollision.checkmousewithobject(this.x,this.y,this.w,this.h, Unproject.unproject().x,Unproject.unproject().y)&&Gdx.input.isButtonJustPressed(0)){
            dropped=!dropped;
        }
        if(dropped) {
            int aktualy = +10;
            ProgrammingSpace.BlockshapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            ProgrammingSpace.BlockshapeRenderer.setColor(0.3f,0.3f,0.3f,1);
            ProgrammingSpace.BlockshapeRenderer.roundendrect(this.x-margin,this.y-21*block.getBlocktype().getBlockModis().size()-10-margin,this.longestText+this.w+5+margin*2, 21*block.getBlocktype().getBlockModis().size()+margin*2, 2);
            ProgrammingSpace.BlockshapeRenderer.end();
        for (int i = 0; i < block.getBlocktype().getBlockModis().size(); i++) {
                aktualy += 21;

            if(CheckKollision.checkmousewithobject(this.x, this.y- aktualy, this.longestText+this.w+5+margin*2,this.h, Unproject.unproject().x,Unproject.unproject().y)){
                ProgrammingSpace.BlockshapeRenderer.setColor(0f/255f, 101f/255f, 168f/255f,1);

                if(Gdx.input.isButtonPressed(0)){
                    ProgrammingSpace.BlockshapeRenderer.setColor(0f/255f, 101f/255f, 100f/255f,1);
                    block.getBlocktype().changeBlockModus(i);

                }
                ProgrammingSpace.BlockshapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                ProgrammingSpace.BlockshapeRenderer.rect(this.x, this.y- aktualy, this.longestText+this.w+5+margin*2,this.h);
                ProgrammingSpace.BlockshapeRenderer.end();
            }


                ProgrammingSpace.batch.begin();
            ProgrammingSpace.batch.draw(block.getBlocktype().getBlockModis().get(i).getModiImage(), this.x, this.y- aktualy, this.w,this.h);
            ProgrammingSpace.batch.end();

                ProgrammingSpace.batch.begin();
                glyphLayout.setText(ProgrammingSpace.font, block.getBlocktype().getBlockModis().get(i).getname());
                ProgrammingSpace.font.draw(ProgrammingSpace.batch, glyphLayout, this.x+this.w+5,this.y-aktualy+glyphLayout.height  + this.h/2- glyphLayout.height/2);
                ProgrammingSpace.batch.end();




            }

        }




if(Gdx.input.isButtonPressed(0) && !CheckKollision.checkmousewithobject(this.x,this.y,this.w,this.h, Unproject.unproject().x,Unproject.unproject().y)){
    dropped=false;
}

        }

    @Override
    public void setAlpha(float alpha) {

    }

}
