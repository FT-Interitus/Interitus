package de.ft.interitus.network.bettertogether;

import java.io.IOException;

public class Manager {
    public static void init() {
        Thread serverthread = new Thread() {
            @Override
            public void run() {
                try {
                    Server.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };


        serverthread.start();

        Client.connect("192.168.2.143",3141);

    }


}
