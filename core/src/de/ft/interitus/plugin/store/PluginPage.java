package de.ft.interitus.plugin.store;

import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.UI.settings.subitems.subitem13;

public class PluginPage {

    /***
     * Shows the information of the Plugin if you click on it
     * @param table
     * @param Storeentry
     */
    public static void add(VisTable table, StorePluginEntry Storeentry) {

        table.clearChildren();

        System.out.println(Storeentry.getId());
        subitem13.add(table, subitem13.scrollPane.getScrollPercentY());


    }
}
