/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.utils;

import de.ft.interitus.Program;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DownloadFile {
    public static String downloadFile(String fileURL)
            throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
        httpConn.setDefaultUseCaches(false);
        httpConn.setIfModifiedSince(-1);
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1
                );
            }


            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            byte[] bytes = inputStream.readAllBytes();
            String s = new String(bytes, StandardCharsets.UTF_8);


            inputStream.close();
            httpConn.disconnect();
            return s;


        } else {
            Program.logger.warning("No file to download. Server replied HTTP code: " + responseCode);
            return null;
        }


    }

    public static byte[] downloadBytes(String fileURL)
            throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
        httpConn.setDefaultUseCaches(false);
        httpConn.setIfModifiedSince(-1);
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1
                );
            }


            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            byte[] bytes = inputStream.readAllBytes();


            inputStream.close();
            httpConn.disconnect();
            return bytes;


        } else {
            Program.logger.config("No file to download. Server replied HTTP code: " + responseCode);
            return null;
        }


    }

}
