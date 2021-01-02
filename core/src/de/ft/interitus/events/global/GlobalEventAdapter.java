/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.global;

import de.ft.interitus.UI.UIElements.UIElements.TabBar.Tab;
import de.ft.interitus.compiler.Compiler;

/**
 * Override only the methods you are interested in
 */
public class GlobalEventAdapter implements GlobalEventListener {
    @Override
    public void loadingdone(GlobalLoadingDoneEvent e) {

    }

    @Override
    public void loadingstart(GlobalLoadingStartEvent e) {

    }

    @Override
    public void erroroccurred(GlobalErrorOccurredEvent e) {

    }

    @Override
    public boolean filedroped(GlobalFileDropedEvent e, String filepaths) {
        return false;
    }

    @Override
    public boolean closeprogramm(GlobalCloseEvent e) {
        return true;
    }

    @Override
    public void focusLost(GlobalFocusLostEvent e) {

    }

    @Override
    public void tabclicked(GlobalTabClickEvent e, Tab tab) {

    }

    @Override
    public void compilingstarted(GlobalCompilingStartEvent e, Compiler compiler) {

    }


}
