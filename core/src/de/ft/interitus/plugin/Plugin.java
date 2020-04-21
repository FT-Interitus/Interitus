package de.ft.interitus.plugin;

public interface Plugin {
    public boolean register();
    public boolean stop();
    public void run();
    public void setPluginRegister(PluginRegister manager);
    public void hello();

}
