/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import de.ft.interitus.plugin.StorePluginInstallManager;

import java.io.IOException;
import java.net.InetSocketAddress;

public class PluginInstallServer {
    public static void start() {

        int port = 8459;
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.createContext("/", new PluginInstallServerRootDirectory());
        server.createContext("/install", new StorePluginInstallManager());
       // server.createContext("/echoGet", new EchoGetHandler());
        //server.createContext("/echoPost", new EchoPostHandler());
        server.setExecutor(null);
        server.start();

    }
    
    public static void allowCors(HttpExchange exchange) {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
            try {
                exchange.sendResponseHeaders(204, -1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
    }
}
