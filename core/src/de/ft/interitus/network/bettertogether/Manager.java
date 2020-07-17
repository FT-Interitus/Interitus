package de.ft.interitus.network.bettertogether;

import java.io.IOException;

public class Manager {


    public static void startserver() {
       Thread serverthread = new Thread() {
            @Override
            public void run() {
                try {
                    Server.start(9990);



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };


        serverthread.start();
    }

    public static void startclient() {
        Thread thread = new Thread()  {
            @Override
            public void run() {
                Client.connect("80.142.253.28", 9990);
            }
        };
        thread.start();
    }


}
