/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.global;

import java.util.EventObject;

public class GlobalCloseEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     * <p>
     * return false to not close the programm
     */
    public GlobalCloseEvent(Object source) {
        super(source);
    }
}
