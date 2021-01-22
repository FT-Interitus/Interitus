/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.block;

import de.ft.interitus.Block.Block;
import de.ft.interitus.events.Event;

public class BlockCreateEvent implements Event {
    private final Block block;

    public BlockCreateEvent(Block block) {
        this.block = block;
    }


    public Block getBlock() {
        return block;
    }
}
