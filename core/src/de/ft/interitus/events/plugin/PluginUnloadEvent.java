/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.plugin;

import java.util.EventObject;

public class PluginUnloadEvent extends EventObject {


    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public PluginUnloadEvent(Object source) {
        super(source);

    }


}
