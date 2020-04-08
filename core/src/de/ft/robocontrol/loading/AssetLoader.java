package de.ft.robocontrol.loading;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetLoader {


    public static String group = "";
    public static String workingdirectory = "";

    //Block Textures
    public static Texture img_block;
    public static Texture img_block_mouseover;
    public static Texture img_marked;

    //Switch Textures
    public static Texture switch_background;
    public static Texture switch_inside;
    public static Texture switch_background_green;

    //Switch Textures White
    public static Texture switch_background_white;
    public static Texture switch_inside_white;
    public static Texture switch_background_green_white;

    //Setup Help
    public static Texture help_arduino_boards;
    public static Texture help_raspberrypi_boards;
    public static Texture help_ev3;

    public static AssetManager manager = new AssetManager();

    public static void load() {


        try {

            group = "Blöcke";
            //Block Textures
            manager.load(workingdirectory+ "block.png", Texture.class);
            manager.load(workingdirectory+"block_mouseover.png", Texture.class);
            manager.load(workingdirectory+"block_marked.png", Texture.class);

            group = "Switches";
            //Switch
            //Switch Dark
            manager.load(workingdirectory+"switchbackground.png", Texture.class);
            manager.load(workingdirectory+"switchinside.png", Texture.class);
            manager.load(workingdirectory+"switchbackgroundgreen.png", Texture.class);
            //Switch White
            manager.load(workingdirectory+"switchbackground_white.png", Texture.class);
            manager.load(workingdirectory+"switchinside.png", Texture.class);
            manager.load(workingdirectory+"switchbackground_whitegreen.png", Texture.class);
            //Setup Help
            manager.load(workingdirectory+"ArduinoHilfe.png",Texture.class);
            manager.load(workingdirectory+"RaspberryPiHilfe.png",Texture.class);
            manager.load(workingdirectory+"EV3Hilfe.png",Texture.class);


        }catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }



    }

    public static void save() {


        try {
            group = "";
            //Block
            img_block = manager.get(workingdirectory+"block.png", Texture.class);
            img_block_mouseover = manager.get(workingdirectory+"block_mouseover.png", Texture.class);
            img_marked = manager.get(workingdirectory+"block_marked.png", Texture.class);

            //Switch
            switch_background = manager.get(workingdirectory+"switchbackground.png", Texture.class);
            switch_inside = manager.get(workingdirectory+"switchinside.png", Texture.class);
            switch_background_green = manager.get(workingdirectory+"switchbackgroundgreen.png", Texture.class);
            //Switch White
            switch_background_white = manager.get(workingdirectory+"switchbackground_white.png", Texture.class);
            switch_inside_white = manager.get(workingdirectory+"switchinside.png", Texture.class);
            switch_background_green_white = manager.get(workingdirectory+"switchbackground_whitegreen.png", Texture.class);
            //Setup help
            help_arduino_boards = manager.get(workingdirectory+"ArduinoHilfe.png",Texture.class);
            help_raspberrypi_boards =manager.get(workingdirectory+"RaspberryPiHilfe.png",Texture.class);
            help_ev3 = manager.get(workingdirectory+"EV3Hilfe.png",Texture.class);

        }catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void unload() {


    }
}
