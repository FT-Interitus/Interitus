/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin.store;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class StorePluginInstallManager {

    @CrossOrigin()
    @RequestMapping(value ="/")
    public String getURLValue(){
       return "";
    }
    @CrossOrigin() //TODO define Origins
    @RequestMapping(value = "/install")
    public String installer(@RequestParam String url) {

        System.out.println(url);
            return "";

    }


}
