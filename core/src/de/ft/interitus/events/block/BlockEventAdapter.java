package de.ft.interitus.events.block;

import de.ft.interitus.Block.Block;

/**
 * Override only methods you are interested in
 */
public class BlockEventAdapter implements BlockEventListener {
    @Override
    public void createBlock(BlockCreateEvent e) {

    }

    @Override
    public void deleteBlock(BlockDeleteEvent e) {

    }

    @Override
    public void killmovingwires(BlockKillMovingWiresEvent e) {

    }

    @Override
    public void setNeighbor(BlockNeighborSetEvent e, Block block, Block neightbour, boolean right) {

    }


}
