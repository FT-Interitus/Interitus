/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.check.CheckMouse;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.tappedbar.TapItem;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectManager;
import org.w3c.dom.css.Rect;


public class TapBarBlockItem implements TapItem {
    private final Texture img;
    private final PlatformSpecificBlock psb;
    private int x;
    private int y;
    private int w = 50;
    private int h = 60;
    private boolean doonce=false;
    private long zeitstempel;

    public TapBarBlockItem(PlatformSpecificBlock psb, Texture img) {
        this.img = img;
        this.psb = psb;
    }

    @Override
    public void draw() {

        if (!UIVar.isdialogeopend) {
            if (CheckMouse.isJustPressedNormal(x, y, w, h)) {

                Block tempblock = ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(ProjectManager.getActProjectVar().blocks.size(), (int) ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).x, (int) ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).y, psb.getWidth(), UIVar.BlockHeight, psb, ProjectManager.getActProjectVar().projectType.getBlockUpdateGenerator(), ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator());

                ProjectManager.getActProjectVar().markedblock = tempblock;
                ProjectManager.getActProjectVar().marked = true;

                ProjectManager.getActProjectVar().blocks.add(tempblock);


                tempblock.setMarked(true);
                tempblock.setMoving(true);

                ProjectManager.getActProjectVar().unterschiedsave.set(psb.getWidth() / 2, UIVar.BlockHeight / 2);
            }
        }

        UI.UIbatch.begin();
        UI.UIbatch.draw(img, this.x, this.y, this.w, this.h);
        UI.UIbatch.end();

        //QuickInfo
        /*
        if(CheckMouse.isMouseover(this.x,this.y,this.w,this.h)){
            UI.blockbarquickinfo.setText("abcde");
            UI.blockbarquickinfo.fadeIn();
            doonce=false;
        }else if(!doonce){
            doonce=true;
            UI.blockbarquickinfo.fadeOut();
        }
        */
        if (CheckMouse.isMouseover(this.x, this.y, this.w, this.h)) {
            if (doonce) {
                zeitstempel = System.currentTimeMillis() + 2000;
                doonce = false;
            }
            if (zeitstempel < System.currentTimeMillis()) {
                UI.blockbarquickinfo.setText(this.psb.getName());
                UI.blockbarquickinfo.setX(Gdx.input.getX());
                UI.blockbarquickinfo.setY(Gdx.graphics.getHeight()-Gdx.input.getY());
                UI.blockbarquickinfo.fadeIn();
            }
        } else {
            if (!doonce) {
                doonce = true;
                UI.blockbarquickinfo.fadeOut();

            }

        }
        /*
        UI.blockbarquickinfo.setText(psb.getName());
        UI.blockbarquickinfo.setSelfCheckRectangle(new Rectangle(this.x,this.y,this.w,this.h));
        UI.blockbarquickinfo.setDoonce(doonce);
        UI.blockbarquickinfo.setZeitstempel(zeitstempel);
        UI.blockbarquickinfo.RectangleSelfCheck();
        doonce=UI.blockbarquickinfo.isDoonce();
        zeitstempel=UI.blockbarquickinfo.getZeitstempel();*/
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
