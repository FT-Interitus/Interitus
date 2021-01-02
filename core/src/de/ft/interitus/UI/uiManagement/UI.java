/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.uiManagement;

public abstract class UI {
    public boolean enabled;
    /**
     *This draw area is fired if the UI should be visible
     */
    protected abstract void draw();
}
