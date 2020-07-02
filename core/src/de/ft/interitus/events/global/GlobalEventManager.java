package de.ft.interitus.events.global;

import de.ft.interitus.Programm;

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
    public boolean filedroped(GlobalFileDropedEvent e, String[] filepaths) {
        boolean handeled = false;
        for (int i = 0; i < listener.size(); i++) {
            if (((GlobalEventListener) listener.elementAt(i)).filedroped(e, filepaths)) {
                handeled = true;
            }

        }

        if (!handeled) {
            Programm.logger.severe("Datei konnte nicht verarbeitet werden"); //TODO Popup
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


}
