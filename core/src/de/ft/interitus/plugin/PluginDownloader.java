/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin;

import de.ft.interitus.Program;
import de.ft.interitus.UI.Notification.Notification;
import de.ft.interitus.datamanager.programmdata.Data;
import de.ft.interitus.utils.CountingInputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class PluginDownloader {

    protected static HttpURLConnection openConnection(String fileURL)
            throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
        httpConn.setDefaultUseCaches(false);
        httpConn.setIfModifiedSince(-1);
       // int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
       // if (responseCode == HttpURLConnection.HTTP_OK) {

            return httpConn;

       // } else {
        //    Program.logger.config("No file to download. Server replied HTTP code: " + responseCode);
        //    return null;
        //}


    }

    protected static void downloadPlugin(String url, String name, Notification notification) throws IOException {

        File newPlugin = new File(Data.folder.getAbsolutePath() + "/plugins/" + name + ".itpl");
        FileOutputStream fileOutputStream = new FileOutputStream(newPlugin);
        HttpURLConnection connection;
        try {

            connection = openConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
            notification.setTitle("Fehler");
            notification.setProgressbarvalue(-1);
            notification.setCloseable(true);
            notification.setStayalive(false);
            notification.setMessage("Fehler beim Herunterladen");
            return;
        }
        assert connection != null;

        long totalSize = connection.getContentLengthLong();

        if(totalSize<0) {
            notification.setTitle("Fehler");
            notification.setProgressbarvalue(-1);
            notification.setCloseable(true);
            notification.setStayalive(false);
            notification.setMessage("Fehler beim Herunterladen");
            return;
        }

        CountingInputStream countingInputStream = new CountingInputStream(connection.getInputStream());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                notification.setProgressbarvalue((int) (((float)countingInputStream.getBytesRead()/(float)totalSize)*100f));

            }
        }, 0, 10);

        byte[] downloadedFile = countingInputStream.readAllBytes();
        timer.cancel();
        timer.purge();
        notification.setProgressbarvalue(100);
        while (notification.getDisplayedProgressbarValue() < 99) {
            try {
                TimeUnit.MILLISECONDS.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        fileOutputStream.write(downloadedFile);
        notification.setTitle("Plugin Installiert");
        notification.setMessage("Das wars schon...\nInteritus lädt das Plugin!");
        notification.setProgressbarvalue(-1);
        notification.setCloseable(true);
        notification.setStayalive(true);

        if (PluginLoader.loadPlugin(newPlugin)) {

            PluginManagerHandler.loadedplugins.getLastObject().register(PluginManagerHandler.registry);
            ProgramRegistry.loadPluginAfterInitialize();

        } else {
            notification.setMessage("Das wars schon...\nInteritus bitte neustarten!");
        }


    }

}
