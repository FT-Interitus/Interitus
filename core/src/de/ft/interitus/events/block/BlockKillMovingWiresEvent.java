/*
 * Copyright (c) 2020.
 * Author Tim & Felix
 */

package de.ft.interitus.events.block;



import java.util.EventObject;

public class BlockKillMovingWiresEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public BlockKillMovingWiresEvent(Object source) {
        super(source);
    }
}
