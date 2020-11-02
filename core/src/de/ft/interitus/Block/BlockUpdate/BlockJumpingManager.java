/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.BlockUpdate;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Program;
import de.ft.interitus.UI.UIElements.check.CheckCollision;
import de.ft.interitus.projecttypes.ProjectManager;

import java.awt.*;

public class BlockJumpingManager {
    private static float lastIntersection = 0;

    /***
     *
     * @param block
     * @param rl 0 = right 1=left
     * @return
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
                size = CheckCollision.flache(visible_block.getX()-block.getW(), visible_block.getY(), (int) width, visible_block.getH(), block.getX(), block.getY());


            if (size > maxValue) {
                maxValue = size;
                maxBlockValue = visible_block;
            }

        }

        lastIntersection = maxValue;

        return maxBlockValue;

    }

    protected static void updateBlockDuplicate(Block movingBlock) {

        float rightIntersection = getBlockDuplicateRight(movingBlock);
        float leftIntersection = getBlockDuplicateLeft(movingBlock);
        if (ProjectManager.getActProjectVar().duplicate_block_left != null && ProjectManager.getActProjectVar().duplicate_block_right != null) {

            if (leftIntersection > rightIntersection) {
                ProjectManager.getActProjectVar().duplicate_block_right = null;
            } else {
                ProjectManager.getActProjectVar().duplicate_block_left = null;
            }

        }


    }

    private static float getBlockDuplicateRight(Block movingBlock) {

        Block intersectingBlock = biggestIntersectionBlock(movingBlock, 0, true, 1);
        if(intersectingBlock!=null&&!intersectingBlock.getBlocktype().canhasrightconnector()) return -1;
        if (intersectingBlock != ProjectManager.getActProjectVar().duplicate_block_right)
            ProjectManager.getActProjectVar().duplicate_block_right = intersectingBlock;
        return lastIntersection;

    }


    private static float getBlockDuplicateLeft(Block movingBlock) {

        Block intersectingBlock = biggestIntersectionBlock(movingBlock, 1, true, 1);
        if(intersectingBlock!=null&&!intersectingBlock.getBlocktype().canhasleftconnector()) return -1;
        if (intersectingBlock != ProjectManager.getActProjectVar().duplicate_block_left)
            ProjectManager.getActProjectVar().duplicate_block_left = intersectingBlock;
        return lastIntersection;


    }

}
