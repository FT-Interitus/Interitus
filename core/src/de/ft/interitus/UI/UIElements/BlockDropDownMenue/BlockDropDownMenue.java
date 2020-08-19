/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.BlockDropDownMenue;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.Block.Block;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.UIElements.UIElement;
import de.ft.interitus.UI.UIElements.check.CheckMouse;
import de.ft.interitus.projecttypes.BlockTypes.BlockModi;

public class BlockDropDownMenue implements UIElement {
    private int x=0;
    private int y=0;
    private int w=0;
    private int h=0;
    private final static int margin=5;

 private Block block=null;
    private boolean dropped=false;

    private BitmapFont font = new BitmapFont();
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

        if(CheckMouse.isJustPressedNormal(this.x,this.y,this.w,this.h, false)){
            dropped=!dropped;
        }
        if(dropped) {
            int aktualy = +10;
            ProgrammingSpace.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            ProgrammingSpace.shapeRenderer.roundendrect(this.x-margin,this.y-21*block.getBlocktype().getBlockModis().size()-10-margin,this.longestText+this.w+5+margin*2, 21*block.getBlocktype().getBlockModis().size()+margin*2, 2);
            ProgrammingSpace.shapeRenderer.end();
        for (int i = 0; i < block.getBlocktype().getBlockModis().size(); i++) {
                aktualy += 21;

                UI.UIbatch.begin();
                UI.UIbatch.draw(block.getBlocktype().getBlockModis().get(i).getModiImage(), this.x, this.y- aktualy, this.w,this.h);
                UI.UIbatch.end();

                ProgrammingSpace.batch.begin();
                glyphLayout.setText(font, block.getBlocktype().getBlockModis().get(i).getname());
                font.draw(ProgrammingSpace.batch, glyphLayout, this.x+this.w+5,this.y-aktualy+glyphLayout.height  + this.h/2- glyphLayout.height/2);
                ProgrammingSpace.batch.end();

            }

        }






        }

    @Override
    public void setAlpha(float alpha) {

    }

}
