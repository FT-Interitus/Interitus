/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.block;

import de.ft.interitus.Block.Block;

public interface BlockEventListener extends java.util.EventListener {

    //TODO Block moved hovered marked Event...
    void createBlock(BlockCreateEvent e);

    void deleteBlock(BlockDeleteEvent e);

    void killmovingwires(BlockKillMovingWiresEvent e);

    void setNeighbor(BlockNeighborSetEvent e, Block block, Block neightbour, boolean right);
}
