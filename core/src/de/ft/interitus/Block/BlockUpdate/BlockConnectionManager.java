/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.BlockUpdate;

import de.ft.interitus.Block.Block;

public class BlockConnectionManager {


    public static void startMovingBlock(Block block) {
        disconnectWireLeft(block);
        disconnectWireRight(block);

    }


    /**
     * Disconnect a wire if there is space between the left and the right block from the wire
     * @param block the specific block the changes should be applied to
     */
    private static void disconnectWireLeft(Block block) {
        if(!block.getWire_left().isVisible()) return;
        block.getWire_left().delete();

    }

    /**
     * Disconnect a wire if there is space between the left and the right block from the wire
     * @param block the specific block the changes should be applied to
     */
    private static void disconnectWireRight(Block block) {
        if(!block.getWire_right().isVisible()) return;
        block.getWire_right().delete();


    }



}
