/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.BlockUpdate;

import com.badlogic.gdx.Gdx;
import de.ft.interitus.Block.Block;
import de.ft.interitus.UI.UIElements.check.CheckCollision;
import de.ft.interitus.UI.UIElements.check.CheckMouse;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.ProjectManager;


public class BlockMarkManager {

    protected static void update() {



        assert ProjectManager.getActProjectVar() !=null;

        if(!Gdx.input.isButtonPressed(0)) return;
        if(wasMouseDownOnBlockSettings()) return;

        if(Gdx.input.isButtonJustPressed(0))  {
            ProjectManager.getActProjectVar().marked_block = null;
        }

        if(ProjectManager.getActProjectVar().moving_block!=null) return; //Do not allow to select an other Block while anyone is moving
        for(Block block:ProjectManager.getActProjectVar().blocks) {
            if(CheckCollision.checkmousewithblock(block, Var.mouseDownPos))
                ProjectManager.getActProjectVar().marked_block =block;
        }

        if(ProjectManager.getActProjectVar().marked_block==null) {
            BlockJumpingManager.unMarkBlock();
        }

    }

    private static boolean wasMouseDownOnBlockSettings() {
       return CheckMouse.wasMousePressed(UIVar.blockeinstellungen_x, UIVar.blockeinstellungen_y, UIVar.blockeinstellungen_w, UIVar.blockeinstellungen_h);
    }


}
