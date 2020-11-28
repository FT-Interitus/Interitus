/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.BlockUpdate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.UI.ProgramGrid;
import de.ft.interitus.UI.UIElements.check.CheckCollision;
import de.ft.interitus.UI.UIElements.check.CheckMouse;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectVar;
import de.ft.interitus.utils.Unproject;

public class BlockMovingManager {

    private static final int tolerance = 2;
    private static final Vector2 tempVector = new Vector2();
    private static ProjectVar projectVar;


    protected static void update(Block block) {


        projectVar = ProjectManager.getActProjectVar();
        assert projectVar != null;

        if (isBlockMoving(block)) {

            projectVar.moving_block = block;
            projectVar.changes = true;

            BlockJumpingManager.updateBlockDuplicate(block);
            block.getPos().set(Unproject.unproject().sub(block.getMovementDiff()));
            for (Block markedBlock : ProjectManager.getActProjectVar().marked_block) {
                if (markedBlock == block) continue;

                markedBlock.getPos().set(Unproject.unproject().sub(markedBlock.getMovementDiff()));

            }
            if (ProgramGrid.block_active_snapping)
                blockSnapping(block);


        } else {

            if (projectVar.moving_block == block) {
               //if (ProgramGrid.block_snapping && !ProgramGrid.block_active_snapping)
                   // for(Block marked:ProjectManager.getActProjectVar().marked_block)
                        //blockSnapping(marked);

                BlockConnectionManager.placeBlock(block);

                stopMovingBlock();
            }


        }


    }

    /***
     * Checks all Information if Block is Moving e. g. Mouse is pressed
     *
     * @param block
     * @return
     */
    private static boolean isBlockMoving(Block block) {

        if (!Gdx.input.isButtonPressed(0)) return false;
        if (!block.isMarked()) return false;
        if (projectVar.moving_block == block) return true;
        if (Var.mouseDownPos.dst(Unproject.unproject()) <= tolerance) return false;
        if (!CheckCollision.checkmousewithblock(block)) return false;
        if (projectVar.moveingdatawire != null) return false;
        if (BlockDataWireManager.checkParameterEject(block)) return false;

        for(Block m:ProjectManager.getActProjectVar().marked_block) {
            m.getMovementDiff().set(generateDiff(m, Unproject.unproject()));
            BlockConnectionManager.startMovingBlock(m);
        }


        return true;


    }


    private static Vector2 generateDiff(Block block, Vector2 mouseValue) {

        tempVector.set(mouseValue.x, mouseValue.y);
        return tempVector.sub(block.getPos());

    }


    /**
     * Information: If you run this very oft you have a very cool animation (:
     *
     * @param block which should affect
     */
    private static void blockSnapping(Block block) {
        int newX = (int) ((block.getX()) / ProgramGrid.margin);
        int newY = (int) ((block.getY()) / ProgramGrid.margin);
        block.setX((int) (newX * ProgramGrid.margin));
        block.setY((int) (newY * ProgramGrid.margin));
    }

    /**
     * Check if the Block was droped in the BlockBar -> delete
     * <p>
     * Than remove the Block from the MovingBlock Var
     */
    private static void stopMovingBlock() {

        if (isOnBlockBar())
            for(int i=0;i<ProjectManager.getActProjectVar().marked_block.size();i++) {
                Block marked =ProjectManager.getActProjectVar().marked_block.get(i);
                if(marked.getBlocktype().canbedeleted())
                    marked.delete(false);
            }


        projectVar.moving_block = null;
        ProjectManager.getActProjectVar().duplicate_block_left = null;
        ProjectManager.getActProjectVar().duplicate_block_right = null;
    }

    /**
     * Checks if Mouse is on the Block Bar
     *
     * @return result from check
     * @see UIVar,CheckMouse
     */
    private static boolean isOnBlockBar() {
        return CheckMouse.isMouseover(UIVar.BlockBarX, UIVar.BlockBarY, UIVar.BlockBarW, UIVar.BlockBarH, false);
    }

}
