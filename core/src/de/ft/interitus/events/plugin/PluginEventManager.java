/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.plugin;

import java.util.Vector;

public class PluginEventManager implements PluginEventListener {

    protected Vector listener = new Vector();

    public void removeListener(PluginEventListener l) {
        listener.remove(l);
    }


    public void addListener(PluginEventListener a) {
        if (!listener.contains(a))
            listener.addElement(a);
    }

    @Override
    public void pluginload(PluginLoadedEvent e) {
        for (int i = 0; i < listener.size(); i++)
            ((PluginEventListener) listener.elementAt(i)).
                    pluginload(e);
    }

    @Override
    public void pluginunload(PluginUnloadEvent e) {
        for (int i = 0; i < listener.size(); i++)
            ((PluginEventListener) listener.elementAt(i)).
                    pluginunload(e);
    }
}
