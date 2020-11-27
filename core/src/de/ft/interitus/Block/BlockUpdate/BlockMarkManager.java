/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.BlockUpdate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import de.ft.interitus.Block.Block;
import de.ft.interitus.UI.SelectionRectDrawer;
import de.ft.interitus.UI.UIElements.check.CheckCollision;
import de.ft.interitus.UI.UIElements.check.CheckMouse;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.Unproject;

import java.awt.*;


public class BlockMarkManager {
    static Rectangle selectionRect=new Rectangle();
    private static boolean selecting = false;

    protected static void update() {


        if(Gdx.input.isButtonPressed(0)&&ProjectManager.getActProjectVar().moving_block==null) {
            selectionRect.setLocation((int)Var.mouseDownPos.x,(int)Var.mouseDownPos.y);
            selectionRect.setSize((int)(Unproject.unproject().x-Var.mouseDownPos.x),(int)(Unproject.unproject().y-Var.mouseDownPos.y));
            SelectionRectDrawer.draw(selectionRect);
            selecting = true;
        }

        if(!Gdx.input.isButtonPressed(0)&&selecting)  {

            for(Block checkblock:ProjectManager.getActProjectVar().visible_blocks){
                if(CheckCollision.rectCollision(selectionRect,new Rectangle(checkblock.getX(),checkblock.getY(),checkblock.getW(),checkblock.getH()))) {
                   ProjectManager.getActProjectVar().marked_block.add(checkblock);

               }
            }

        }

        assert ProjectManager.getActProjectVar() !=null;

        if(!Gdx.input.isButtonPressed(0)) return;
        if(wasMouseDownOnBlockSettings()) return;

        if(Gdx.input.isButtonJustPressed(0) && !Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))  {
            ProjectManager.getActProjectVar().marked_block.clear();
        }

        if(ProjectManager.getActProjectVar().moving_block!=null) return; //Do not allow to select an other Block while anyone is moving
        for(Block block:ProjectManager.getActProjectVar().blocks) {
            if(CheckCollision.checkmousewithblock(block, Var.mouseDownPos))
                ProjectManager.getActProjectVar().marked_block.add(block);
        }


    }


    private static boolean wasMouseDownOnBlockSettings() {
       return CheckMouse.wasMousePressed(UIVar.blockeinstellungen_x, UIVar.blockeinstellungen_y, UIVar.blockeinstellungen_w, UIVar.blockeinstellungen_h);
    }


}
