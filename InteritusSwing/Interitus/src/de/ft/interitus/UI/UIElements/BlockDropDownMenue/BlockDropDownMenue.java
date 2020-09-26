/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.BlockDropDownMenue;


import de.ft.interitus.Block.Block;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.check.CheckKollision;
import de.ft.interitus.projecttypes.BlockTypes.BlockModi;
import de.ft.interitus.utils.Input;
import de.ft.interitus.utils.Unproject;

public class BlockDropDownMenue {
    private int x=0;
    private int y=0;
    private int w=0;
    private int h=0;
    private final static int margin=5;

 private Block block=null;
    private boolean dropped=false;



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
        //TODO L
/*
        for (BlockModi modi : block.getBlocktype().getBlockModis()) {
            glyphLayout.setText(UI.font, modi.getname());
            if (glyphLayout.width > this.longestText) {
                this.longestText = (int) glyphLayout.width;
            }
        }

 */
    }


    public void draw(Block block) {
        longestText();
        //TODO L    ProgrammingSpace.batch.begin();
        //TODO L   ProgrammingSpace.batch.draw(block.getBlocktype().getBlockModis().get(block.getBlocktype().actBlockModiIndex).getModiImage(),this.x,this.y,this.w,this.h);
        //TODO L   ProgrammingSpace.batch.end();

        if(CheckKollision.checkpointwithobject(this.x,this.y,this.w,this.h, Unproject.unproject().x,Unproject.unproject().y)&&Input.isButtonJustPressed(0)){
            dropped=!dropped;
        }
        if(dropped) {
            int aktualy = +10;
            //TODO L  ProgrammingSpace.BlockshapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            //TODO L  ProgrammingSpace.BlockshapeRenderer.setColor(0.3f,0.3f,0.3f,1);
            //TODO L  ProgrammingSpace.BlockshapeRenderer.roundendrect(this.x-margin,this.y-21*block.getBlocktype().getBlockModis().size()-10-margin,this.longestText+this.w+5+margin*2, 21*block.getBlocktype().getBlockModis().size()+margin*2, 2);
            //TODO L ProgrammingSpace.BlockshapeRenderer.end();
        for (int i = 0; i < block.getBlocktype().getBlockModis().size(); i++) {
                aktualy += 21;

            if(CheckKollision.checkpointwithobject(this.x, this.y- aktualy, this.longestText+this.w+5+margin*2,this.h, Unproject.unproject().x,Unproject.unproject().y)){
                //TODO L ProgrammingSpace.BlockshapeRenderer.setColor(0f/255f, 101f/255f, 168f/255f,1);

                if(Input.isButtonPressed(0)){
                    //TODO L  ProgrammingSpace.BlockshapeRenderer.setColor(0f/255f, 101f/255f, 100f/255f,1);
                    block.getBlocktype().changeBlockModus(i,block, false);

                }
                //TODO L  ProgrammingSpace.BlockshapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                //TODO L  ProgrammingSpace.BlockshapeRenderer.rect(this.x, this.y- aktualy, this.longestText+this.w+5+margin*2,this.h);
                //TODO L ProgrammingSpace.BlockshapeRenderer.end();
            }


            //TODO L   ProgrammingSpace.batch.begin();
            //TODO L  ProgrammingSpace.batch.draw(block.getBlocktype().getBlockModis().get(i).getModiImage(), this.x, this.y- aktualy, this.w,this.h);
            //TODO L   ProgrammingSpace.batch.end();

            //TODO L  ProgrammingSpace.batch.begin();
            //TODO L   glyphLayout.setText(ProgrammingSpace.font, block.getBlocktype().getBlockModis().get(i).getname());
            //TODO L    ProgrammingSpace.font.draw(ProgrammingSpace.batch, glyphLayout, this.x+this.w+5,this.y-aktualy+glyphLayout.height  + this.h/2- glyphLayout.height/2);
            //TODO L     ProgrammingSpace.batch.end();




            }

        }




if(Input.isButtonPressed(0) && !CheckKollision.checkpointwithobject(this.x,this.y,this.w,this.h, Unproject.unproject().x,Unproject.unproject().y)){
    dropped=false;
}

        }




}
