/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.BlockUpdate;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Wire;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectVar;

public class BlockConnectionManager {

    public static void startMovingBlock(Block block) {
        disconnectLeft(block);
        disconnectRight(block);



    }

    public static void placeBlock(Block block) {
        ProjectVar projectVar = ProjectManager.getActProjectVar();
        assert projectVar != null;

        if (projectVar.duplicate_block_left == null && projectVar.duplicate_block_right == null) return;

        if (projectVar.duplicate_block_right != null) {
            block.setPosition(projectVar.duplicate_block_right.getX_dup_rechts() , projectVar.duplicate_block_right.getY());
            new Wire(projectVar.duplicate_block_right,block,false);
            block.setLeft(projectVar.duplicate_block_right);
            return;
        }

        if(projectVar.duplicate_block_left != null) {
            block.setPosition(projectVar.duplicate_block_left.getX()-block.getW(),projectVar.duplicate_block_left.getY());
            new Wire(block,projectVar.duplicate_block_left,false);
            block.setRight(projectVar.duplicate_block_left);
        }


    }


    /**
     * Disconnect a wire if there is space between the left and the right block from the wire
     *
     * @param block the specific block the changes should be applied to
     */
    private static void disconnectLeft(Block block) {
        if (block.getWire_left() == null) return;
        if (block.getWire_left().isVisible()) return;
        block.getWire_left().delete();
        block.setLeft(null);


    }

    /**
     * Disconnect a wire if there is space between the left and the right block from the wire
     *
     * @param block the specific block the changes should be applied to
     */
    private static void disconnectRight(Block block) {
        if (block.getWire_right() == null) return;
        if (block.getWire_right().isVisible()) return;
        block.getWire_right().delete();
        block.setRight(null);


    }


}
