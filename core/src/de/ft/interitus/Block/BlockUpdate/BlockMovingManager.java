/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.BlockUpdate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Settings;
import de.ft.interitus.UI.ProgramGrid;
import de.ft.interitus.UI.UIElements.check.CheckCollision;
import de.ft.interitus.UI.UIElements.check.CheckMouse;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectVar;
import de.ft.interitus.utils.Unproject;


public class BlockMovingManager {

    public static final int movingTolerance = 2;
    private static final Vector2 tempVector = new Vector2();
    private static ProjectVar projectVar;


    protected static void update(Block block) {


        projectVar = ProjectManager.getActProjectVar();
        assert ProjectManager.getActProjectVar() != null;

        if (isBlockMoving(block)) {

            projectVar.moving_block = block;
            projectVar.changes = true;

            BlockJumpingManager.updateBlockDuplicate(block);
            block.getPos().set(Unproject.unproject().sub(block.getMovementDiff()));
            for (Block markedBlock : ProjectManager.getActProjectVar().marked_blocks) {
                if (markedBlock == block) continue;

                markedBlock.getPos().set(Unproject.unproject().sub(markedBlock.getMovementDiff()));

            }
            if (Settings.blockActiveSnapping)
                blockSnapping(block);


        } else {

            if (projectVar.moving_block == block) {
               if (Settings.blockSnapping && !Settings.blockActiveSnapping)
                        blockSnapping(projectVar.moving_block);

                BlockConnectionManager.placeBlock(block);

                stopMovingBlock();
            }


        }


    }

    /***
     * Checks all Information if Block is Moving e. g. Mouse is pressed
     *
     * @param block affected Block
     * @return if the Block is able to be moved
     */
    private static boolean isBlockMoving(Block block) {
        assert ProjectManager.getActProjectVar() != null;
        if (!Gdx.input.isButtonPressed(0)) return false;
        if (!block.isMarked()) return false;
        if (projectVar.moving_block == block) return true;
        if (Var.mouseDownPos.dst(Unproject.unproject()) <= movingTolerance) return false;
        if (!CheckCollision.checkmousewithblock(block)) return false;
        if (projectVar.moveingdatawire != null) return false;
        if (BlockDataWireManager.checkParameterEject(block)) return false;
        if (isOnBlockBar()) return false;
        if(isMouseOnBlockSettings()) return false;

        for(Block m:ProjectManager.getActProjectVar().marked_blocks) {
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
        BlockConnectionManager.connectedBlockJumpingToLeft(block);
        BlockConnectionManager.connectedBlockJumpingToRight(block);
    }

    /**
     * Check if the Block was dropped in the BlockBar -> delete
     * <p>
     * Than remove the Block from the MovingBlock Var
     */
    private static void stopMovingBlock() {
        assert ProjectManager.getActProjectVar() != null;
        if (isOnBlockBar())
            for(int i = 0; i<ProjectManager.getActProjectVar().marked_blocks.size(); i++) {
                Block marked =ProjectManager.getActProjectVar().marked_blocks.get(i);
                if(marked.getBlocktype().canbedeleted())
                    marked.delete(false);
                    i--;
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
    private static boolean isMouseOnBlockSettings() {
        return CheckMouse.isMouseover(UIVar.blockeinstellungen_x, UIVar.blockeinstellungen_y, UIVar.blockeinstellungen_w, UIVar.blockeinstellungen_h,false)&&UIVar.isBlockSettingsopen;
    }

}
