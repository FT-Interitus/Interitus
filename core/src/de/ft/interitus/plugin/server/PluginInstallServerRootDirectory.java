/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class PluginInstallServerRootDirectory implements HttpHandler {


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        PluginInstallServer.allowCors(exchange);


        String response = "<h1>Interitus is running.</h1>";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
