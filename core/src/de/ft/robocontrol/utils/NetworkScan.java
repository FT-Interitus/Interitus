package de.ft.robocontrol.utils;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkScan {
    public static void get() {
        InetAddress byName = null;
        try {
            byName = InetAddress.getByName("192.168.2.2");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(byName);
        try {
            System.out.println(byName.isReachable(1000));
            try(final DatagramSocket socket = new DatagramSocket()){
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                String ip = socket.getLocalAddress().getHostAddress();
             System.out.println(ip.split("\\.")[2]);
                System.out.println(ip.split("\\.")[2]);
                System.out.println(ip.split("\\.")[2]);  System.out.println(ip.split("\\.")[2]);
                System.out.println(ip.split("\\.")[2]);  System.out.println(ip.split("\\.")[2]);  System.out.println(ip.split("\\.")[2]);






            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void scan()  {

    }
}
