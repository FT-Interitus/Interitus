/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin;

public class Native implements Plugin {
    @Override
    public boolean register() {
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
        return 1.0;
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
