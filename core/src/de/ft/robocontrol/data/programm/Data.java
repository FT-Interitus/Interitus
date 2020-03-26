package de.ft.robocontrol.data.programm;

import com.badlogic.gdx.Gdx;
import de.ft.robocontrol.Block.Block;
import de.ft.robocontrol.UI.UI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Data {
    public static ArrayList<String> path = new ArrayList<String>();
    public static ArrayList<String> filename = new ArrayList<String>();

    public static void init() {

       File folder = new File(System.getProperty("user.home")+"/.racd");
        File recent = new File(System.getProperty("user.home")+"/.racd/recent.json");
       Path path = folder.toPath();
       if(!folder.exists()) {

           folder.mkdir();
           try {
               recent.createNewFile();
           } catch (IOException e) {
               e.printStackTrace();
           }
           try { Files.setAttribute(path,"dos:hidden", true ); }
           catch (IOException e) { e.printStackTrace();}
       }else if(!recent.exists()) {

           try {
               recent.createNewFile();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

    }






}
