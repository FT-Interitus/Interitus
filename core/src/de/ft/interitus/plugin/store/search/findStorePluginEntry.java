package de.ft.interitus.plugin.store.search;

import de.ft.interitus.plugin.store.StorePluginEntry;
import de.ft.interitus.plugin.store.StorePluginsVar;

import java.util.ArrayList;

public class findStorePluginEntry {
    static ArrayList<StorePluginEntry> findobjects = new ArrayList<>();

    public static ArrayList<StorePluginEntry> search(String search) {

        findobjects.clear();
        for (StorePluginEntry storePluginEntry : StorePluginsVar.pluginEntries) {

            if (storePluginEntry.getDescription().contains(search)) {
                findobjects.add(storePluginEntry);
            } else if (storePluginEntry.getName().contains(search)) {
                findobjects.add(storePluginEntry);
            } else if (storePluginEntry.getDetailed_description().contains(search)) {
                findobjects.add(storePluginEntry);
            }//TODO eventuell weitere Such parameter

        }

        return findobjects;

    }
}
