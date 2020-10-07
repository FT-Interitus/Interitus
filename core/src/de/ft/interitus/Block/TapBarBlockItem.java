/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import de.ft.interitus.ProgramingSpace;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.check.CheckMouse;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.tappedbar.TapItem;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectManager;


public class TapBarBlockItem implements TapItem {

    private final PlatformSpecificBlock psb;
    private int x;
    private int y;
    private int w = 50;
    private int h = 60;
    private boolean doonce=false;
    private long zeitstempel;

    public TapBarBlockItem(PlatformSpecificBlock psb) {

        this.psb = psb;
    }

    @Override
    public void draw() {

        if (!UIVar.isdialogeopend) {
            if (CheckMouse.isJustPressedNormal(x, y, w, h, false)) {

                Block tempblock = ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(ProjectManager.getActProjectVar().blocks.size(), (int) ProgramingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).x, (int) ProgramingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).y, psb.getWidth(), UIVar.BlockHeight, psb, ProjectManager.getActProjectVar().projectType.getBlockUpdateGenerator(), ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator());

                ProjectManager.getActProjectVar().marked_block = tempblock;
                ProjectManager.getActProjectVar().marked = true;

                ProjectManager.getActProjectVar().blocks.add(tempblock);


                tempblock.setMarked(true);
                tempblock.setMoving(true);

                ProjectManager.getActProjectVar().diff_save.set(psb.getWidth() / 2, UIVar.BlockHeight / 2);
            }
        }

        UI.UIbatch.begin();


        UI.UIbatch.draw(AssetLoader.block_middle,this.x+6,this.y,this.w-(6*2),this.h);
        UI.UIbatch.draw(AssetLoader.block_left,this.x,this.y,6,this.h);
        UI.UIbatch.draw(AssetLoader.block_right,this.x+6+this.getW()-(6*2),this.y,6,this.h);

        if (psb.getBlockCategorie() != null) {
            switch (psb.getBlockCategorie()) {

                case ActionBlocks -> {

                    UI.UIbatch.draw(AssetLoader.green_bar_middle, this.getX() + 6, this.getY() - 1 + this.getH() - 13, this.getW() - 12, 13); // Block ohne das er makiert ist
                    UI.UIbatch.draw(AssetLoader.green_bar_left, this.getX() + 1, this.getY() - 1 + this.getH() - 13, 6, 13);
                    UI.UIbatch.draw(AssetLoader.green_bar_right, this.getX() + this.getW() - 7, this.getY() - 1 + this.getH() - 13, 6, 13);


                }


                case Programm_Sequence -> {
                    UI.UIbatch.draw(AssetLoader.orange_bar_middle, this.getX() + 6, this.getY() - 1 + this.getH() - 13, this.getW() - 12, 13); // Block ohne das er makiert ist
                    UI.UIbatch.draw(AssetLoader.orange_bar_left, this.getX() + 1, this.getY() - 1 + this.getH() - 13, 6, 13);
                    UI.UIbatch.draw(AssetLoader.orange_bar_right, this.getX() + this.getW() - 7, this.getY() - 1 + this.getH() - 13, 6, 13);

                }

                case Sensors -> {
                    UI.UIbatch.draw(AssetLoader.yellow_bar_middle, this.getX() + 6, this.getY() - 1 + this.getH() - 13, this.getW() - 12, 13); // Block ohne das er makiert ist
                    UI.UIbatch.draw(AssetLoader.yellow_bar_left, this.getX() + 1, this.getY() - 1 + this.getH() - 13, 6, 13);
                    UI.UIbatch.draw(AssetLoader.yellow_bar_right, this.getX() + this.getW() - 7, this.getY() - 1 + this.getH() - 13, 6, 13);

                }

                case Data_Operation -> {
                    UI.UIbatch.draw(AssetLoader.red_bar_middle, this.getX() + 6, this.getY() - 1 + this.getH() - 13, this.getW() - 12, 13); // Block ohne das er makiert ist
                    UI.UIbatch.draw(AssetLoader.red_bar_left, this.getX() + 1, this.getY() - 1 + this.getH() - 13, 6, 13);
                    UI.UIbatch.draw(AssetLoader.red_bar_right, this.getX() + this.getW() - 7, this.getY() - 1 + this.getH() - 13, 6, 13);


                }


                case Specials -> {
                    UI.UIbatch.draw(AssetLoader.blue_bar_middle, this.getX() + 6, this.getY() - 1 + this.getH() - 13, this.getW() - 12, 13); // Block ohne das er makiert ist
                    UI.UIbatch.draw(AssetLoader.blue_bar_left, this.getX() + 1, this.getY() - 1 + this.getH() - 13, 6, 13);
                    UI.UIbatch.draw(AssetLoader.blue_bar_right, this.getX() + this.getW() - 7, this.getY() - 1 + this.getH() - 13, 6, 13);

                }

                case OwnBlocks -> {
                    UI.UIbatch.draw(AssetLoader.turquoise_bar_middle, this.getX() + 6, this.getY() - 1 + this.getH() - 13, this.getW() - 12, 13); // Block ohne das er makiert ist
                    UI.UIbatch.draw(AssetLoader.turquoise_bar_left, this.getX() + 1, this.getY() - 1 + this.getH() - 13, 6, 13);
                    UI.UIbatch.draw(AssetLoader.turquoise_bar_right, this.getX() + this.getW() - 7, this.getY() - 1 + this.getH() - 13, 6, 13);


                }


            }

        }else{
            UI.UIbatch.draw(AssetLoader.orange_bar_middle, this.getX() + 6, this.getY()-1+this.getH()-13, this.getW() - 12, 13); // Block ohne das er makiert ist
            UI.UIbatch.draw(AssetLoader.orange_bar_left, this.getX()+1, this.getY()-1+this.getH()-13, 6,13);
            UI.UIbatch.draw(AssetLoader.orange_bar_right, this.getX() + this.getW() - 7, this.getY()-1+this.getH()-13, 6, 13);

        }
try {
    UI.UIbatch.draw(psb.getDescriptionImage(), this.getX() + 10, this.getY() + 9, 30, 30);
}catch(NullPointerException e){

}


        UI.UIbatch.end();

        //QuickInfo
        if(UI.blockbarquickinfo.getContentOverKey(psb.getName())!=null) {
            UI.blockbarquickinfo.getContentOverKey(psb.getName()).setDisabled(false);
            UI.blockbarquickinfo.getContentOverKey(psb.getName()).setMouseoverRect(this.x, this.y, this.w, this.h);

        }

    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getW() {
        return w;
    }

    @Override
    public void setW(int w) {
        this.w = w;
    }

    @Override
    public int getH() {
        return h;
    }

    @Override
    public void setH(int h) {
        this.h = h;
    }


}
