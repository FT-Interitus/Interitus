package de.ft.interitus.plugin;

import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.plugin.PluginLoadedEvent;

public class PluginRegister {
    private String name = "";
    private double version;

    public PluginRegister() {
        EventVar.pluginEventManager.pluginload(new PluginLoadedEvent(this,this));
    }

    public PluginRegister(String name) {
        EventVar.pluginEventManager.pluginload(new PluginLoadedEvent(this,this));
    }

    public void config(Configuration option, String selection) {
        switch (option) {
            case name:
                this.name = selection;
                break;
            case version:
                this.version = Double.parseDouble(selection);
        }
    }

    public String getName() {
        return name;
    }

    public double getVersion() {
        return version;
    }
}
