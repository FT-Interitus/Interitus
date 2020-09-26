/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.global;

import java.util.EventObject;

public class GlobalLoadingDoneEvent extends EventObject {
    /**
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GlobalLoadingDoneEvent(Object source) {
        super(source);
    }
}
