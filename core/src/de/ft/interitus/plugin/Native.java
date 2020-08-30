/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin;

import de.ft.interitus.Var;

public class Native implements Plugin {
    @Override
    public boolean register(ProgramRegistry registry) {
        return false;
    }

    @Override
    public boolean init(PluginAssetManager assetManager) {
        return false;
    }


    @Override
    public boolean stop() {
        return false;
    }

    @Override
    public boolean run() {
        return false;
    }

    @Override
    public String getName() {
        return "Nativ";
    }

    @Override
    public double getVersion() {
        return Var.PROGRAMM_VERSION_ID;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getLongDescription() {
        return null;
    }

    @Override
    public String getAuthor() {
        return null;
    }
}
