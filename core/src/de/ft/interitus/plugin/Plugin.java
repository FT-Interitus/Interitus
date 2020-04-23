package de.ft.interitus.plugin;

public interface Plugin {
    public boolean register();
    public boolean stop();
    public boolean run();

}
