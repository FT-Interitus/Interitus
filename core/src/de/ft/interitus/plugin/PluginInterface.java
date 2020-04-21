package de.ft.interitus.plugin;

public interface PluginInterface {
    public boolean start();
    public boolean stop();
    public void setPluginManager(PluginManager manager);
    public void hello();
}
