package de.ft.interitus.events.block;

import de.ft.interitus.Block.Block;
import de.ft.interitus.events.block.BlockCreateEvent;

public interface BlockEventListener extends java.util.EventListener {

    public void createBlock(BlockCreateEvent e);

}
