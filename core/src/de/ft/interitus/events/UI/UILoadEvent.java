/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.UI;

import java.util.EventObject;

public class UILoadEvent extends EventObject {


    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public UILoadEvent(Object source) {
        super(source);
    }
}
