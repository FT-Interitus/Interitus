package de.ft.interitus.events.plugin;

import de.ft.interitus.plugin.PluginRegister;

import java.util.EventObject;

public class PluginUnloadEvent extends EventObject {
    //TODO unload Plugin in PluginManager
    PluginRegister pluginRegister;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public PluginUnloadEvent(Object source, PluginRegister pluginRegister) {
        super(source);
        this.pluginRegister = pluginRegister;
    }

    public PluginRegister getPluginRegister() {
        return pluginRegister;
    }
}
