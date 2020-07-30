package de.ft.interitus.plugin;

public interface Plugin {
    boolean register();

    boolean stop();

    boolean run();

    String getName();

    double getVersion();
    String getDescription();
    String getLongDescription();
    String getAuthor();



}
