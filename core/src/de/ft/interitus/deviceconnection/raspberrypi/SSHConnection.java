/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.deviceconnection.raspberrypi;

import com.jcraft.jsch.*;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.Program;

import java.io.InputStream;

public class SSHConnection {
    private static final JSch testconnection = new JSch();
    private static Session testconnectionsession;
    private static Session updatesession;

    public static void connect() {
        JSch jSch = new JSch();
        String host = "192.168.2.117";
        String user = "pi";
        String password = "Pi-Server";

        try {
            Session session = jSch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");

            session.connect(30000);

            Channel channel = session.openChannel("exec");

            ((ChannelExec) channel).setCommand("echo Hi Pi> test");

            channel.connect(3 * 1000);
            channel.disconnect();
            ((ChannelExec) channel).setCommand("echo Hi Pi> test3");
            channel.connect(3000);


        } catch (JSchException e) {
            e.printStackTrace();
            DisplayErrors.error = e;
        }


    }

    public static boolean checkconnection(String ipaddress, String username, String password,int port) {
        try {
            testconnectionsession = testconnection.getSession(username, ipaddress, port);
            testconnectionsession.setConfig("StrictHostKeyChecking", "no");
            testconnectionsession.setPassword(password);
            testconnectionsession.connect(1000);
            testconnectionsession.disconnect();
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public static int update(String ipaddress, String username, String password,int port) {
        int exitstate = -1;
        try {

            updatesession = testconnection.getSession(username, ipaddress, port);
            updatesession.setConfig("StrictHostKeyChecking", "no");
            updatesession.setPassword(password);
            updatesession.connect(2000);

            Channel channel = updatesession.openChannel("exec");

            ((ChannelExec) channel).setCommand("sudo apt-get update");
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);
            InputStream in = channel.getInputStream();
            channel.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) continue;
                    Program.logger.config("exit-status: " + channel.getExitStatus());
                    exitstate = channel.getExitStatus();
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            channel.disconnect();
            testconnectionsession.disconnect();


            return exitstate;
        } catch (Exception e) {
            DisplayErrors.error = e;
            e.printStackTrace();
            return exitstate;
        }
    }

    public static boolean installpython(String ipaddress, String username, String password,int port) {
        try {
            updatesession = testconnection.getSession(username, ipaddress, port);
            updatesession.setConfig("StrictHostKeyChecking", "no");
            updatesession.setPassword(password);
            updatesession.connect(2000);

            Channel channel = updatesession.openChannel("exec");

            ((ChannelExec) channel).setCommand("sudo apt-get install python");
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);
            InputStream in = channel.getInputStream();
            channel.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) continue;
                    Program.logger.config("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            channel.disconnect();
            testconnectionsession.disconnect();


            return true;
        } catch (Exception e) {

            e.printStackTrace();
            DisplayErrors.error = e;
            return false;
        }
    }

}
