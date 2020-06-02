package de.ft.interitus.plugin;

public interface Plugin {
    boolean register();

    boolean stop();

    boolean run();


}
