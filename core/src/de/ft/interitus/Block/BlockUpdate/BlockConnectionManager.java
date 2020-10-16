/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.BlockUpdate;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Program;
import de.ft.interitus.UI.UIElements.check.CheckCollision;
import de.ft.interitus.projecttypes.ProgrammArea.ProgrammArea;
import de.ft.interitus.projecttypes.ProjectManager;

import java.util.Properties;

public class BlockConnectionManager {

    public static void startMovingBlock(Block block) {
        disconnectWireLeft(block);
        disconnectWireRight(block);

    }

    public static void placeBlock(Block block) {

      if(BlockJumpingManager.biggestIntersectionBlock(block)!=null) {
          Program.logger.config("Pair found");
      }

    }


    /**
     * Disconnect a wire if there is space between the left and the right block from the wire
     * @param block the specific block the changes should be applied to
     */
    private static void disconnectWireLeft(Block block) {
        if(block.getWire_left()==null) return;
        if(!block.getWire_left().isVisible()) return;
        block.getWire_left().delete();

    }

    /**
     * Disconnect a wire if there is space between the left and the right block from the wire
     * @param block the specific block the changes should be applied to
     */
    private static void disconnectWireRight(Block block) {
        if(block.getWire_right()==null) return;
        if(!block.getWire_right().isVisible()) return;
        block.getWire_right().delete();


    }



}
