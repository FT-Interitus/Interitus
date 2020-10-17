/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.BlockUpdate;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Program;
import de.ft.interitus.projecttypes.ProjectManager;

public class BlockConnectionManager {

    public static void startMovingBlock(Block block) {
        disconnectLeft(block);
        disconnectRight(block);



    }

    public static void placeBlock(Block block) {


        if(ProjectManager.getActProjectVar().duplicate_block_left==null&&ProjectManager.getActProjectVar().duplicate_block_right==null) return;


    }


    /**
     * Disconnect a wire if there is space between the left and the right block from the wire
     * @param block the specific block the changes should be applied to
     */
    private static void disconnectLeft(Block block) {
        if(block.getWire_left()==null) return;
        if(block.getWire_left().isVisible()) return;
        block.getWire_left().delete();
        block.setLeft(null);


    }

    /**
     * Disconnect a wire if there is space between the left and the right block from the wire
     * @param block the specific block the changes should be applied to
     */
    private static void disconnectRight(Block block) {
        if(block.getWire_right()==null) return;
        if(block.getWire_right().isVisible()) return;
        block.getWire_right().delete();
        block.setRight(null);


    }



}
