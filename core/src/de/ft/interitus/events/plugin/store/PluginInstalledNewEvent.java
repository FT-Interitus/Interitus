/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.plugin.store;

import de.ft.interitus.plugin.store.StorePluginEntry;

import java.util.EventObject;

public class PluginInstalledNewEvent extends EventObject {
    StorePluginEntry storePluginEntry;

    /**
     * Constructs a prototypical Event.
     *
     * @param source           the object on which the Event initially occurred
     * @param storePluginEntry
     * @throws IllegalArgumentException if source is null
     */
    public PluginInstalledNewEvent(Object source, StorePluginEntry storePluginEntry) {
        super(source);
        this.storePluginEntry = storePluginEntry;
    }

    public StorePluginEntry getStorePluginEntry() {
        return storePluginEntry;
    }
}
