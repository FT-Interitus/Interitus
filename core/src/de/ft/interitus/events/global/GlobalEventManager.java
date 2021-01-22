/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.global;

import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.Program;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.UIElements.TabBar.Tab;
import de.ft.interitus.compiler.Compiler;

import java.util.Vector;

public class GlobalEventManager implements GlobalEventListener {
    protected Vector listener = new Vector();

    public void removeListener(GlobalEventListener l) {
        listener.remove(l);
    }


    public void addListener(GlobalEventListener a) {
        if (!listener.contains(a))
            listener.addElement(a);
    }


    @Override
    public void loadingdone(GlobalLoadingDoneEvent e) {
        for (int i = 0; i < listener.size(); i++)
            ((GlobalEventListener) listener.elementAt(i)).
                    loadingdone(e);
    }

    @Override
    public void loadingstart(GlobalLoadingStartEvent e) {
        for (int i = 0; i < listener.size(); i++)
            ((GlobalEventListener) listener.elementAt(i)).
                    loadingstart(e);
    }

    @Override
    public void erroroccurred(GlobalErrorOccurredEvent e) {
        for (int i = 0; i < listener.size(); i++)
            ((GlobalEventListener) listener.elementAt(i)).
                    erroroccurred(e);
    }

    @Override
    public boolean filedroped(GlobalFileDropedEvent e, String filepaths) {
        boolean handeled = false;
        for (int i = 0; i < listener.size(); i++) {
            if (((GlobalEventListener) listener.elementAt(i)).filedroped(e, filepaths)) {
                handeled = true;
                break;
            }

        }

        if (!handeled) {
            Program.logger.severe("Datei konnte nicht verarbeitet werden");
            Dialogs.showOKDialog(UI.stage, "Fehler", "Datei konnte nicht verarbeitet werden");
        }

        return false;
    }

    @Override
    public boolean closeprogramm(GlobalCloseEvent e) {
        boolean close = true;
        for (int i = 0; i < listener.size(); i++) {
            if (!((GlobalEventListener) listener.elementAt(i)).closeprogramm(e)) {
                close = false;
            }

        }

        return close;
    }

    @Override
    public void focusLost(GlobalFocusLostEvent e) {
        for (int i = 0; i < listener.size(); i++)
            ((GlobalEventListener) listener.elementAt(i)).
                    focusLost(e);
    }

    @Override
    public void tabclicked(GlobalTabClickEvent e, Tab tab) {
        for (int i = 0; i < listener.size(); i++)
            ((GlobalEventListener) listener.elementAt(i)).
                    tabclicked(e, tab);
    }

    @Override
    public void compilingstarted(GlobalCompilingStartEvent e, Compiler compiler) {
        for (int i = 0; i < listener.size(); i++)
            ((GlobalEventListener) listener.elementAt(i)).
                    compilingstarted(e, compiler);
    }


}
