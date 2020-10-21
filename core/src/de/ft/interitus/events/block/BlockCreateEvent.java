/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.block;

import de.ft.interitus.Block.Block;
import de.ft.interitus.events.Event;
import de.ft.interitus.events.EventManager;

public class BlockCreateEvent implements Event {
      public   Block block;


    public BlockCreateEvent() {
        super();
    }



    public BlockCreateEvent(Block block) {
        this.block = block;
    }






}
