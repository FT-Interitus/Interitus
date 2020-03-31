package de.ft.robocontrol.roboconnection;

import de.ft.robocontrol.UI.ConnectionWindow;

public class UIbridge {
    public static void UpdateConnectionWindowPortsList(){


        Thread updater = new Thread() {
            @Override
            public void run() {
                ConnectionWindow.selectportlist.setItems(SerialConnection.getPortNames());
            }
        };
        updater.start();

    }
}
