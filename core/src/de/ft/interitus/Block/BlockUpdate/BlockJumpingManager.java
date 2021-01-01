/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.BlockUpdate;

import de.ft.interitus.Block.Block;
import de.ft.interitus.UI.UIElements.check.CheckCollision;
import de.ft.interitus.projecttypes.ProjectManager;

public class BlockJumpingManager {
    private static float lastIntersection = 0;

    /***
     *
     * @param block the affected Block
     * @param rl 0 = right 1=left
     *
     */
    private static Block biggestIntersectionBlock(Block block, int rl, boolean useIntersectionBlock, float testedWidth) {

        assert ProjectManager.getActProjectVar() != null;

        Block maxBlockValue = null;
        float maxValue = 0f;

        for (Block visible_block : ProjectManager.getActProjectVar().visible_blocks) {

            float width;
            if (visible_block == block) continue;
            if (!CheckCollision.checkblockwithduplicate(block, visible_block, rl)) continue;

            if (!useIntersectionBlock)
                if (testedWidth != 0)
                    width = (int) (visible_block.getW() / testedWidth);
                else
                    width = (int) (visible_block.getW() / 1.5f);

            else if (testedWidth != 0)
                width = (int) (block.getW() / testedWidth);
            else
                width = (int) (block.getW() / 1.5f);
            float size;

            if (rl == 0)
                size = CheckCollision.flache(visible_block.getX_dup_rechts(), visible_block.getY(), (int) width, visible_block.getH(), block.getX(), block.getY());
            else
                size = CheckCollision.flache(visible_block.getX() - block.getW(), visible_block.getY(), (int) width, visible_block.getH(), block.getX(), block.getY());


            if (size > maxValue) {
                maxValue = size;
                maxBlockValue = visible_block;
            }

        }

        lastIntersection = maxValue;

        return maxBlockValue;

    }

    protected static void updateBlockDuplicate(Block movingBlock) {
        assert ProjectManager.getActProjectVar() != null;

        float rightIntersection = getBlockDuplicateRight(getEndingLeftBlockSelection(movingBlock));
        float leftIntersection = getBlockDuplicateLeft(getEndingRightBlockSelection(movingBlock));
        if (ProjectManager.getActProjectVar().duplicate_block_left != null && ProjectManager.getActProjectVar().duplicate_block_right != null) {

            if (leftIntersection > rightIntersection) {
                ProjectManager.getActProjectVar().duplicate_block_right = null;
            } else {
                ProjectManager.getActProjectVar().duplicate_block_left = null;
            }

        }


    }


    /**
     * gets the leftest neighbour from a Block
     *
     * @param block affected Block
     */
    protected static Block getEndingLeftBlockSelection(Block block) {
        Block neighbor = block;
        while (neighbor.getLeft() != null && !neighbor.getWire_left().isVisible()) {
            neighbor = neighbor.getLeft();
        }
        return neighbor;

    }

    /**
     * gets the rightest neighbour from a Block
     *
     * @param block affected Block
     */
    protected static Block getEndingRightBlockSelection(Block block) {
        Block neighbor = block;
        while (neighbor.getRight() != null && !neighbor.getWire_right().isVisible()) {
            neighbor = neighbor.getRight();
        }
        return neighbor;

    }

    private static float getBlockDuplicateRight(Block movingBlock) {
        assert ProjectManager.getActProjectVar() != null;
        if (movingBlock.left != null) return -1;

        if (!movingBlock.getBlocktype().canHasLeftConnector()) return -1;
        Block intersectingBlock = biggestIntersectionBlock(movingBlock, 0, true, 1);

        if (intersectingBlock != null && !intersectingBlock.getBlocktype().canHasRightConnector()) return -1;
        if (intersectingBlock != null && intersectingBlock.right != null) return -1;

        if (intersectingBlock != ProjectManager.getActProjectVar().duplicate_block_right)
            ProjectManager.getActProjectVar().duplicate_block_right = intersectingBlock;
        return lastIntersection;

    }


    private static float getBlockDuplicateLeft(Block movingBlock) {
        assert ProjectManager.getActProjectVar() != null;
        if (movingBlock.right != null) return -1;

        if (!movingBlock.getBlocktype().canHasRightConnector()) return -1;
        Block intersectingBlock = biggestIntersectionBlock(movingBlock, 1, true, 1);

        if (intersectingBlock != null && !intersectingBlock.getBlocktype().canHasLeftConnector()) return -1;
        if (intersectingBlock != null && intersectingBlock.left != null) return -1;

        if (intersectingBlock != ProjectManager.getActProjectVar().duplicate_block_left)
            ProjectManager.getActProjectVar().duplicate_block_left = intersectingBlock;
        return lastIntersection;


    }

}
