/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.plugin;

public interface PluginEventListener {
    void pluginload(PluginLoadedEvent e);

    void pluginunload(PluginUnloadEvent e);
}
