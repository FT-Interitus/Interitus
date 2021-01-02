/*
 * Copyright (c) 2021.
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

            Block connection = BlockJumpingManager.getEndingLeftBlockSelection(block);

            connection.setPosition(projectVar.duplicate_block_right.getX_dup_right() , projectVar.duplicate_block_right.getY());
            new Wire(projectVar.duplicate_block_right,connection,false);
            connection.setLeft(projectVar.duplicate_block_right);
            connectedBlockJumpingToLeft(BlockJumpingManager.getEndingLeftBlockSelection(block));
            return;
        }

        //Do the same for left

        Block connection = BlockJumpingManager.getEndingRightBlockSelection(block);

        connection.setPosition(projectVar.duplicate_block_left.getX()-connection.getW(),projectVar.duplicate_block_left.getY());
        new Wire(connection,projectVar.duplicate_block_left,false);
        connection.setRight(projectVar.duplicate_block_left);
        connectedBlockJumpingToRight(BlockJumpingManager.getEndingRightBlockSelection(block));


    }


    /**
     * Disconnect a wire if there is space between the left and the right block from the wire
     *
     * @param block the specific block the changes should be applied to
     */
    private static void disconnectLeft(Block block) {
        assert ProjectManager.getActProjectVar() != null;
        if (block.getWire_left() == null) return;
        if (block.getWire_left().isVisible()) return;
        if(ProjectManager.getActProjectVar().marked_blocks.contains(block.getLeft())) return;
        block.getWire_left().delete();
        block.setLeft(null);


    }

    /**
     * Disconnect a wire if there is space between the left and the right block from the wire
     *
     * @param block the specific block the changes should be applied to
     */
    private static void disconnectRight(Block block) {
        assert ProjectManager.getActProjectVar() != null;
        if (block.getWire_right() == null) return;
        if (block.getWire_right().isVisible()) return;
        if(ProjectManager.getActProjectVar().marked_blocks.contains(block.getRight())) return;
        block.getWire_right().delete();
        block.setRight(null);


    }

    protected static void connectedBlockJumpingToLeft(Block block) {
        Block neighbor = block.getRight();
        while(neighbor!=null&&!neighbor.getWire_left().isVisible()) {
            neighbor.setPosition(neighbor.getLeft().getX_dup_right() , neighbor.getLeft().getY());
            neighbor = neighbor.getRight();
        }

    }

    protected static void connectedBlockJumpingToRight(Block block) {
        Block neighbor = block.getLeft();
        while(neighbor!=null&&!neighbor.getWire_right().isVisible()) {
            neighbor.setPosition(neighbor.getRight().getX()-neighbor.getW() , neighbor.getRight().getY());
            neighbor = neighbor.getLeft();
        }

    }


}
