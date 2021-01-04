/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import de.ft.interitus.Program;
import de.ft.interitus.plugin.server.PluginInstallServer;


import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StorePluginInstallManager implements HttpHandler {


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        PluginInstallServer.allowCors(exchange);
        Map<String, Object> parameters = new HashMap<String, Object>();
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String query = br.readLine();
        parseQuery(query, parameters);


        String found_url = null;
        String found_jsoninformation = null;

        System.out.println(exchange.getRequestURI().getQuery());

        for(int i=0;i<exchange.getRequestURI().getQuery().split("&").length;i++) {
            try {
                String key = exchange.getRequestURI().getQuery().split("&")[i].split("=", 2)[0].replace("=", "");
                String content = exchange.getRequestURI().getQuery().split("&")[i].split("=", 2)[1];


                if (key.trim().contentEquals("url")) found_url = content;
                if (key.trim().contentEquals("jsoninformation")) found_jsoninformation = content;
            }catch (Exception ignored) { }
        }
        if(found_jsoninformation==null||found_url==null) return;
        PluginInstallManager.startInstallPlugin(found_url,found_jsoninformation);

        String response = "<h1>Install Plugin...</h1>";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }

    public static void parseQuery(String query, Map<String,
                Object> parameters) throws UnsupportedEncodingException {

        if (query != null) {
            String pairs[] = query.split("[&]");
            for (String pair : pairs) {
                String param[] = pair.split("[=]");
                String key = null;
                String value = null;
                if (param.length > 0) {
                    key = URLDecoder.decode(param[0],
                            System.getProperty("file.encoding"));
                }

                if (param.length > 1) {
                    value = URLDecoder.decode(param[1],
                            System.getProperty("file.encoding"));
                }

                if (parameters.containsKey(key)) {
                    Object obj = parameters.get(key);
                    if (obj instanceof List<?>) {
                        List<String> values = (List<String>) obj;
                        values.add(value);

                    } else if (obj instanceof String) {
                        List<String> values = new ArrayList<String>();
                        values.add((String) obj);
                        values.add(value);
                        parameters.put(key, values);
                    }
                } else {
                    parameters.put(key, value);
                }
            }
        }
    }

}
