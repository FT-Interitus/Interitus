package de.ft.interitus.events.plugin.store;

import java.util.Vector;

public class PluginStoreEventManager implements PluginStoreListener{

    protected Vector listener = new Vector();

    public void removeListener(PluginStoreListener l) {
        listener.remove(l);
    }


    public void addListener(PluginStoreListener a) {
        if (!listener.contains(a))
            listener.addElement(a);
    }

    @Override
    public void plugininstalled(PluginInstalledNewEvent e) {
        for (int i = 0; i < listener.size(); i++)
            ((PluginStoreListener) listener.elementAt(i)).
                    plugininstalled(e);
    }
}
