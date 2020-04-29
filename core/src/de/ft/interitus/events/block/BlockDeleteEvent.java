package de.ft.interitus.events.block;

import de.ft.interitus.Block.Block;

import java.util.EventObject;

public class BlockDeleteEvent extends EventObject {
    Block block;
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public BlockDeleteEvent(Object source, Block block) {
        super(source);
    }

    public Block getBlock() {
        return block;
    }
}
