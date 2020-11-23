/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin.store;


import de.ft.interitus.Program;
import de.ft.interitus.datamanager.programmdata.Data;
import de.ft.interitus.utils.DownloadFile;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
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
    public String installer(@RequestParam String url,@RequestParam String JsonInformation) throws IOException {

        Program.logger.config(url);

        JSONObject jsonObject = new JSONObject(JsonInformation);

        //TODO check
        //installRequest(jsonObject.getString("name"),jsonObject.getString("version"),jsonObject.getString("description"),jsonObject.getString("author"),jsonObject.getString("detailed_description"));

        File newplugin = new File(Data.folder.getAbsolutePath()+"/plugins/"+jsonObject.getString("name")+".itpl");
        FileOutputStream fileOutputStream = new FileOutputStream(newplugin);
        fileOutputStream.write(DownloadFile.downloadBytes(url));

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
