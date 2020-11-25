/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin;


import de.ft.interitus.Program;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class StorePluginInstallManager {

    @CrossOrigin()
    @RequestMapping(value ="/")
    public String getURLValue(){
       return "";
    }

    @CrossOrigin() //TODO define Origins
    @RequestMapping(value = "/install")
    public String installer(@RequestParam String url,@RequestParam String jsoninformation) throws IOException {

       PluginInstallManager.startInstallPlugin(url,jsoninformation);
            return "";

    }

    private boolean installRequest(String name, String version, String description, String author, String detailed_description) {
        //TODO UI
        return true;
    }

    @CrossOrigin() //TODO define Origins
    @RequestMapping(value = "/isInstalled")
    public String isInstalled(@RequestParam String name) {

        Program.logger.config(name);



        //TODO check

        return "false";

    }





}
