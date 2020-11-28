/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes;

import de.ft.interitus.UI.ChangeListener;

public class BlockSettings {



     private ChangeListener listener;

    String settings = "";

    public BlockSettings() {

    }
    public BlockSettings(String settings) {

        this.settings = settings;

    }

    public String getSettings() {
        return settings;
    }


    public void setSettings(String settings) {
        if(listener!=null) {
            listener.change();
        }
        this.settings = settings;
    }

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }
}
