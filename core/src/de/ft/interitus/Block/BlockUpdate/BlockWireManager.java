/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.BlockUpdate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Wire;
import de.ft.interitus.Program;
import de.ft.interitus.UI.UIElements.check.CheckCollision;
import de.ft.interitus.UI.UIElements.check.CheckMouse;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.Unproject;

import java.util.Properties;

public class BlockWireManager {

    private static final Vector2 tempVector = new Vector2();
    protected static final int movingTolerance = 1;
    protected static void update(Block block) {
        assert ProjectManager.getActProjectVar()!=null;

        ProjectManager.getActProjectVar().showLeftDocker = ProjectManager.getActProjectVar().movingWire!=null;

        if(ProjectManager.getActProjectVar().showLeftDocker) {
            if(ProjectManager.getActProjectVar().movingWire.getLeftConnection()!=block) {
                if(isWireConnectionActivated(block.getWireConnector_left(),false)) {
                    if(block.getBlocktype().canHasLeftConnector()) {
                        block.setLeft(ProjectManager.getActProjectVar().movingWire.getLeftConnection());
                        ProjectManager.getActProjectVar().movingWire.setRightConnection(block);
                    }
                }
            }
        }



        if(block.getWire_right()==null&&block.getBlocktype().canHasRightConnector()) {
            if (!isWireConnectionActivated(block.getwireconnector_right(), true)) return;
            if (ProjectManager.getActProjectVar().movingWire != null)
                ProjectManager.getActProjectVar().movingWire.delete();
            new Wire(block);
        }


    }


    private static boolean isWireConnectionActivated(Vector2 connector,boolean tolerance) {


        boolean mousedown = CheckCollision.checkVectors(connector, 20, 20, Var.mouseDownPos, 1, 1);
        if(tolerance) {
            tempVector.set(Var.mouseDownPos);
            return Gdx.input.isButtonPressed(0) && mousedown && tempVector.dst(Unproject.unproject()) > movingTolerance;
        }else{
            return  Gdx.input.isButtonJustPressed(0)&&mousedown;
        }
    }


    private static boolean clickedOnBlock() {

        assert ProjectManager.getActProjectVar()!=null;
        for(Block block:ProjectManager.getActProjectVar().visible_blocks) {
            if(CheckCollision.checkmousewithblock(block)) return true;
        }
        return false;
    }

    protected static void updateMovingWire() {
        assert ProjectManager.getActProjectVar()!=null;
        if(Gdx.input.isButtonJustPressed(0)) {
            if(!clickedOnBlock()) {
                ProjectManager.getActProjectVar().movingWire.delete();
            }


        }


    }


}
