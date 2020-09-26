/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.plugin.store;

import java.util.Vector;

public class PluginStoreEventManager implements PluginStoreEventListener {

    protected Vector listener = new Vector();

    public void removeListener(PluginStoreEventListener l) {
        listener.remove(l);
    }


    public void addListener(PluginStoreEventListener a) {
        if (!listener.contains(a))
            listener.addElement(a);
    }


}
