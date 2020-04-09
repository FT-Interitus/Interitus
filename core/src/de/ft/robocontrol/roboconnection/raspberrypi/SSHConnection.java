package de.ft.robocontrol.roboconnection.raspberrypi;

import com.jcraft.jsch.*;

public class SSHConnection {
   private static JSch testconnection = new JSch();
   private static Session testconnectionsession;
    public static void connect() {
        JSch jSch = new JSch();
        String host="192.168.2.117";
        String user = "pi";
        String password = "Pi-Server";

        try {
            Session session = jSch.getSession(user,host,22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");

            session.connect(30000);

            Channel channel=session.openChannel("exec");

            ((ChannelExec) channel).setCommand("echo Hi Pi> test");

            channel.connect(3*1000);
            channel.disconnect();
            ((ChannelExec) channel).setCommand("echo Hi Pi> test3");
            channel.connect(3000);


        } catch (JSchException e) {
            e.printStackTrace();
        }


    }

    public static boolean checkconnection(String ipaddress,String username,String password) {
        try {
           testconnectionsession = testconnection.getSession(username,ipaddress,22); //TODO port auswählbar
           testconnectionsession.setPassword(password);
           testconnectionsession.connect();
           testconnectionsession.disconnect();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
