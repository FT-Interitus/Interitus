/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin;

public interface Plugin {
    boolean register(ProgramRegistry registry);


    boolean init(PluginAssetManager assetManager);


    boolean run();




}
