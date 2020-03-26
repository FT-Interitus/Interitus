package de.ft.robocontrol.data.programm;

import com.badlogic.gdx.Gdx;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Data {
    public static void init() {
       File folder = new File(System.getProperty("user.home")+"/.racd");
       Path path = folder.toPath();
       if(!folder.exists()) {
           folder.mkdir();
           try { Files.setAttribute(path,"dos:hidden", true ); }
           catch (IOException e) { e.printStackTrace();}
       }
    }

}
