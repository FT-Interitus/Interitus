/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin;


import de.ft.interitus.Program;


import java.io.IOException;


public class StorePluginInstallManager {


    public String getURLValue(){
       return "";
    }

 //TODO define Origins

    public String installer( String url, String jsoninformation) throws IOException {

       PluginInstallManager.startInstallPlugin(url,jsoninformation);
            return "";

    }

    private boolean installRequest(String name, String version, String description, String author, String detailed_description) {
        //TODO UI
        return true;
    }


    public String isInstalled( String name) {

        Program.logger.config(name);



        //TODO check

        return "false";

    }





}
