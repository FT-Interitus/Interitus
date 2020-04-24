package de.ft.interitus.events;

import de.ft.interitus.Block.Block;

import java.util.EventObject;

public class BlockCreateEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public BlockCreateEvent(Object source, Block block) {
        super(source);
    }
}
