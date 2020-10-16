/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.BlockUpdate;

import de.ft.interitus.Block.Block;
import de.ft.interitus.UI.UIElements.check.CheckCollision;
import de.ft.interitus.projecttypes.ProjectManager;

import java.awt.Rectangle;

public class BlockJumpingManager {
    private static final Rectangle rectangle = new Rectangle();
    private static final Rectangle rectangleDuplicate = new Rectangle();


    // FIXME: 16.10.20
    public static Block biggestIntersectionBlock(Block block) {

        assert ProjectManager.getActProjectVar()!=null;

        Block maxBlockValue = null;
        float maxValue = 0f;

        for(Block visible_block:ProjectManager.getActProjectVar().visible_blocks) {

            if(!CheckCollision.checkblockwithduplicate(block,visible_block,0)) continue;

            rectangleDuplicate.width = (int) (visible_block.getW() / 1.5f);
            rectangleDuplicate.height =visible_block.getH();
            rectangleDuplicate.x = visible_block.getX();
            rectangleDuplicate.y = visible_block.getY();

            rectangle.width = block.getW();
            rectangle.height = block.getH();
            rectangle.x = block.getX();
            rectangle.y = block.getY();


            float size = (float) (rectangle.intersection(rectangleDuplicate).getHeight()*rectangle.intersection(rectangleDuplicate).getWidth());

            if(size>maxValue) {
                maxValue = size;
                maxBlockValue = block;
            }

        }

        return maxBlockValue;

    }

}
