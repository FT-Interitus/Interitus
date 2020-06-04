package de.ft.interitus.plugin;

import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.plugin.PluginLoadedEvent;

public class PluginRegister {
    private String name = "";
    private double version;
    private String author ="";
    private String description="";
    private String detailed_description="";

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
                break;
            case author:
                this.author = selection;
                break;
            case description:
                this.description = selection;
                break;
            case detailed_description:
                this.detailed_description = selection;
                break;
        }
    }

    public String getName() {
        return name;
    }

    public String getDetailed_description() {
        return detailed_description;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public double getVersion() {
        return version;
    }
}
