package de.ft.interitus.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.plugin.store.StorePluginsVar;
import de.ft.interitus.utils.DownloadFile;

import java.io.IOException;
import java.util.ArrayList;

public class AssetLoader {


    public static ArrayList<Texture> storeimages = new ArrayList<>();
    public static ArrayList<ArrayList<Object>> pluginimages = new ArrayList<>();

    public static Pixmap backcursor;

    public static String group = "";
    public static String workingdirectory = "";

    //Block Textures
    public static Texture img_block;
    public static Texture img_block_right;
    public static Texture img_block_left;
    public static Texture img_block_mouseover;
    public static Texture img_marked;
    public static Texture connector;
    public static Texture connector_offerd;

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
    public static Texture help_platforms;

    //Wire
    public static Texture wire;
    //Wire Nod
    public static Texture wire_node;

    //Plugin Warte Bild
    public static Texture pluginwait;


    //////////////////-Tap Block Bar-////////////////////////////
    //////////////////-Aktionsblöck-////////////////////////////
    public static Texture aktion_mittlerermotor;
    public static Texture aktion_großermotor;
    public static Texture aktion_standartsteuerung;
    public static Texture aktion_hebelsteuerung;
    public static Texture aktion_anzeige;
    public static Texture aktion_klang;
    public static Texture aktion_steinstatusleuchte;
    //////////////////-Programm ablauf-////////////////////////////
    public static Texture programmablauf_start;
    public static Texture Programmablauf_wait;
    public static Texture programmablauf_schleufe;
    public static Texture programmablauf_schalter;
    public static Texture programmablauf_interrupt;
    //////////////////-Sensorblöcke-////////////////////////////


    public static Texture img_mappe1;
    public static Texture img_mappe2;
    public static Texture img_mappe3;
    public static Texture img_mappe4;
    public static Texture img_mappe5;
    public static Texture img_mappe6;


    public static BitmapFont welcomefont;//font

    /////////////////////-ButtonBar-//////////////////////////
    public static Texture img_startbutton;
    public static Texture img_startbutton_mouseover;
    public static Texture img_startbutton_pressed;

    public static Texture img_stopbutton;
    public static Texture img_stopbutton_mouseover;
    public static Texture img_stopbutton_pressed;

    public static Texture img_projectstructur;
    public static Texture img_projectstructur_mouseover;
    public static Texture img_projectstructur_pressed;

    public static Texture img_debugstart;
    public static Texture img_debugstart_mouseover;
    public static Texture img_debugstart_pressed;
    
    ///////////////////////////-BlockParameter Bilder-/////////////////////////////
    public static Texture img_WaitBlock_warteZeit_Parameter;

    //////////////////////////-TabBar-//////////////////////////////
    public static Texture img_Tab;

    /////////WaitBlock////////////
    public static Texture WaitBlock_left;
    public static Texture WaitBlock_right;
    public static Texture WaitBlock_middle;
    public static Texture WaitBlock_smallimage;
    /////////SetupBlock////////////
    public static Texture SetupBlock_left;
    public static Texture SetupBlock_right;
    public static Texture SetupBlock_middle;
    /////////LoopBlock////////////
    public static Texture LoopBlock_left;
    public static Texture LoopBlock_right;
    public static Texture LoopBlock_middle;
    /////////PinModeBlock////////////
    public static Texture PinModeBlock_left;
    public static Texture PinModeBlock_right;
    public static Texture PinModeBlock_middle;
    public static Texture PinModeBlock_smallimage;
    /////////DigitalWriteBlock////////////
    public static Texture DigitalWrite_left;
    public static Texture DigitalWrite_right;
    public static Texture DigitalWrite_middle;
    public static Texture DigitalWrite_smallimage;

    public static Texture Parameter_High_Low;
    public static Texture Parameter_Pin;
    public static Texture Parameter_IO;

    public static Texture mouseover_links;
    public static Texture mouse_over_rechts;
    public static Texture mouse_over_mitte;

    public static Texture marked_links;
    public static Texture marked_rechts;
    public static Texture marked_mitte;




    public static AssetManager manager = new AssetManager();

    public static ArrayList<Pixmap> pixmap = new ArrayList<>();
    public static boolean finishpluginimageloading = false;

    public static void loadmore(String file, Class type) {
        manager.load(workingdirectory + file, type);
    }

    public static Object save(String file, Class Type) {
        return manager.get(workingdirectory + file, Type);
    }


    public static void load() {


        try {

            group = "Schriftarten";
            try {
                //schriftart
                FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("comicsans.ttf"));
                FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
                parameter.size = 50;
                welcomefont = generator.generateFont(parameter); // font size 12 pixels
                generator.dispose(); // don't forget to dispose to avoid memory leaks!
            } catch (Exception e) {
                System.out.println("Fehler beim Laden der Schrift");
            }


            group = "TabBar";


            backcursor = new Pixmap(Gdx.files.internal("backcursor.png"));



            manager.load(workingdirectory + "TabBar/Tabimage.png", Texture.class);

            group = "ButtonBar";
            manager.load(workingdirectory + "ButtonBar/startbutton.png", Texture.class);
            manager.load(workingdirectory + "ButtonBar/startbutton_mouseover.png", Texture.class);
            manager.load(workingdirectory + "ButtonBar/startbutton_pressed.png", Texture.class);

            manager.load(workingdirectory + "ButtonBar/stopbutton.png", Texture.class);
            manager.load(workingdirectory + "ButtonBar/stopbutton_mouseover.png", Texture.class);
            manager.load(workingdirectory + "ButtonBar/stopbutton_pressed.png", Texture.class);

            manager.load(workingdirectory + "ButtonBar/projektstrukturbutton.png", Texture.class);
            manager.load(workingdirectory + "ButtonBar/projektstrukturbutton_mouseover.png", Texture.class);
            manager.load(workingdirectory + "ButtonBar/projektstrukturbutton_pressed.png", Texture.class);

            manager.load(workingdirectory + "ButtonBar/debugbutton.png", Texture.class);
            manager.load(workingdirectory + "ButtonBar/debugbutton_mouseover.png", Texture.class);
            manager.load(workingdirectory + "ButtonBar/debugbutton_pressed.png", Texture.class);


            group = "mappen";
            manager.load(workingdirectory + "Bar/Mappe1.png", Texture.class);
            manager.load(workingdirectory + "Bar/Mappe2.png", Texture.class);
            manager.load(workingdirectory + "Bar/Mappe3.png", Texture.class);
            manager.load(workingdirectory + "Bar/Mappe4.png", Texture.class);
            manager.load(workingdirectory + "Bar/Mappe5.png", Texture.class);
            manager.load(workingdirectory + "Bar/Mappe6.png", Texture.class);


            group = "Blöcke";
            //Block Textures
            manager.load(workingdirectory + "Block/Blockrechts.png", Texture.class);
            manager.load(workingdirectory + "Block/Blocklinks.png", Texture.class);
            manager.load(workingdirectory + "Block/Blockmitte.png", Texture.class);
            manager.load(workingdirectory + "block_mouseover.png", Texture.class);
            manager.load(workingdirectory + "block_marked.png", Texture.class);
            manager.load(workingdirectory + "connector.png", Texture.class);
            manager.load(workingdirectory + "connector_offerd.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/WaitBlock/WaitParameter.png", Texture.class);
            //////////////////////////////////////////////////////////////////////////////////////////////////////////
            manager.load(workingdirectory + "Block/Block_Wait/links.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_Wait/rechts.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_Wait/mitte.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_Setup/links.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_Setup/rechts.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_Setup/mitte.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_Loop/links.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_Loop/rechts.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_Loop/mitte.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_PinMode/links.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_PinMode/rechts.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_PinMode/mitte.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_PinMode/Parameter_Pin.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_PinMode/Parameter_IO.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_DigitalWrite/links.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_DigitalWrite/rechts.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_DigitalWrite/mitte.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_DigitalWrite/parameter_high_low.png", Texture.class);
            manager.load(workingdirectory + "Block/mouseover_links.png", Texture.class);
            manager.load(workingdirectory + "Block/mouseover_rechts.png", Texture.class);
            manager.load(workingdirectory + "Block/mouseover_mitte.png", Texture.class);
            manager.load(workingdirectory + "Block/market_links.png", Texture.class);
            manager.load(workingdirectory + "Block/market_rechts.png", Texture.class);
            manager.load(workingdirectory + "Block/market_mitte.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_PinMode/smallblock.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_DigitalWrite/smallblock.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_Wait/smallblock.png", Texture.class);




            group = "Switches";
            //Switch
            //Switch Dark
            manager.load(workingdirectory + "switchbackground.png", Texture.class);
            manager.load(workingdirectory + "switchinside.png", Texture.class);
            manager.load(workingdirectory + "switchbackgroundgreen.png", Texture.class);
            //Switch White
            manager.load(workingdirectory + "switchbackground_white.png", Texture.class);
            manager.load(workingdirectory + "switchinside.png", Texture.class);
            manager.load(workingdirectory + "switchbackground_whitegreen.png", Texture.class);
            group = "Setup";
            //Setup Help
            manager.load(workingdirectory + "ArduinoHilfe.png", Texture.class);
            manager.load(workingdirectory + "RaspberryPiHilfe.png", Texture.class);
            manager.load(workingdirectory + "EV3Hilfe.png", Texture.class);
            manager.load(workingdirectory + "PlatformHilfe.png", Texture.class);
            group = "Wire";
            //Wire
            manager.load(workingdirectory + "wire.png", Texture.class);
            group = "Wire-Node";
            //Wire Node
            manager.load(workingdirectory + "node.png", Texture.class);
            group = "Plugin";
            manager.load(workingdirectory + "pluginwaiting.png", Texture.class);
            group = "Blöcke";
            manager.load(workingdirectory + "TabBarBlockBilder/antikaeferblock.png", Texture.class);


            Thread loadimagesfromweb = new Thread() {
                @Override
                public void run() {
                    int load = 0;

                    if (StorePluginsVar.pluginEntries.size() > 10) {
                        load = 10;
                    } else {
                        load = StorePluginsVar.pluginEntries.size();
                    }
                    for (int i = 0; i < load; i++) {

                        byte[] download = new byte[0];
                        try {
                            download = DownloadFile.downloadBytes(StorePluginsVar.pluginEntries.get(i).getImage());
                        } catch (IOException e) {
                            DisplayErrors.error = e;
                        }
                        pixmap.add(new Pixmap(download, 0, download.length));
                        //storeimages.add(new Texture(pixmap));
                    }
                    finishpluginimageloading = true;
                }
            };


            loadimagesfromweb.start();


        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }


    }

    public static void save() {


        try {
            group = "";

            manager.load(workingdirectory + "Block/Parameter/WaitBlock/WaitParameter.png", Texture.class);
            img_WaitBlock_warteZeit_Parameter = manager.get(workingdirectory + "Block/Parameter/WaitBlock/WaitParameter.png");

            img_Tab = manager.get(workingdirectory + "TabBar/Tabimage.png");

            img_startbutton = manager.get(workingdirectory + "ButtonBar/startbutton.png");
            img_startbutton_mouseover = manager.get(workingdirectory + "ButtonBar/startbutton_mouseover.png");
            img_startbutton_pressed = manager.get(workingdirectory + "ButtonBar/startbutton_pressed.png");

            img_stopbutton = manager.get(workingdirectory + "ButtonBar/stopbutton.png");
            img_stopbutton_mouseover = manager.get(workingdirectory + "ButtonBar/stopbutton_mouseover.png");
            img_stopbutton_pressed = manager.get(workingdirectory + "ButtonBar/stopbutton_pressed.png");

            img_projectstructur = manager.get(workingdirectory + "ButtonBar/projektstrukturbutton.png");
            img_projectstructur_mouseover = manager.get(workingdirectory + "ButtonBar/projektstrukturbutton_mouseover.png");
            img_projectstructur_pressed = manager.get(workingdirectory + "ButtonBar/projektstrukturbutton_pressed.png");

            img_debugstart = manager.get(workingdirectory + "ButtonBar/debugbutton.png");
            img_debugstart_mouseover = manager.get(workingdirectory + "ButtonBar/debugbutton_mouseover.png");
            img_debugstart_pressed = manager.get(workingdirectory + "ButtonBar/debugbutton_pressed.png");


            img_mappe1 = manager.get(workingdirectory + "Bar/Mappe1.png", Texture.class);
            img_mappe2 = manager.get(workingdirectory + "Bar/Mappe2.png", Texture.class);
            img_mappe3 = manager.get(workingdirectory + "Bar/Mappe3.png", Texture.class);
            img_mappe4 = manager.get(workingdirectory + "Bar/Mappe4.png", Texture.class);
            img_mappe5 = manager.get(workingdirectory + "Bar/Mappe5.png", Texture.class);
            img_mappe6 = manager.get(workingdirectory + "Bar/Mappe6.png", Texture.class);


            //Block
            img_block_right = manager.get(workingdirectory + "Block/Blockrechts.png", Texture.class);
            img_block_left = manager.get(workingdirectory + "Block/Blocklinks.png", Texture.class);
            img_block = manager.get(workingdirectory + "Block/Blockmitte.png", Texture.class);
            img_block_mouseover = manager.get(workingdirectory + "block_mouseover.png", Texture.class);
            img_marked = manager.get(workingdirectory + "block_marked.png", Texture.class);
            connector = manager.get(workingdirectory + "connector.png", Texture.class);
            connector_offerd = manager.get(workingdirectory + "connector_offerd.png", Texture.class);

            WaitBlock_left = manager.get(workingdirectory + "Block/Block_Wait/links.png", Texture.class);
            WaitBlock_right = manager.get(workingdirectory + "Block/Block_Wait/rechts.png", Texture.class);
            WaitBlock_middle = manager.get(workingdirectory + "Block/Block_Wait/mitte.png", Texture.class);
            SetupBlock_left = manager.get(workingdirectory + "Block/Block_Setup/links.png", Texture.class);
            SetupBlock_right = manager.get(workingdirectory + "Block/Block_Setup/rechts.png", Texture.class);
            SetupBlock_middle = manager.get(workingdirectory + "Block/Block_Setup/mitte.png", Texture.class);
            LoopBlock_left = manager.get(workingdirectory + "Block/Block_Loop/links.png", Texture.class);
            LoopBlock_right = manager.get(workingdirectory + "Block/Block_Loop/rechts.png", Texture.class);
            LoopBlock_middle = manager.get(workingdirectory + "Block/Block_Loop/mitte.png", Texture.class);
            PinModeBlock_left = manager.get(workingdirectory + "Block/Block_PinMode/links.png", Texture.class);
            PinModeBlock_right = manager.get(workingdirectory + "Block/Block_PinMode/rechts.png", Texture.class);
            PinModeBlock_middle = manager.get(workingdirectory + "Block/Block_PinMode/mitte.png", Texture.class);
            Parameter_Pin = manager.get(workingdirectory + "Block/Block_PinMode/Parameter_Pin.png", Texture.class);
            Parameter_IO = manager.get(workingdirectory + "Block/Block_PinMode/Parameter_IO.png", Texture.class);
            DigitalWrite_left = manager.get(workingdirectory + "Block/Block_DigitalWrite/links.png", Texture.class);
            DigitalWrite_right = manager.get(workingdirectory + "Block/Block_DigitalWrite/rechts.png", Texture.class);
            DigitalWrite_middle = manager.get(workingdirectory + "Block/Block_DigitalWrite/mitte.png", Texture.class);
            Parameter_High_Low = manager.get(workingdirectory + "Block/Block_DigitalWrite/parameter_high_low.png", Texture.class);
            mouse_over_rechts = manager.get(workingdirectory + "Block/mouseover_rechts.png", Texture.class);
            mouseover_links = manager.get(workingdirectory + "Block/mouseover_links.png", Texture.class);
            mouse_over_mitte = manager.get(workingdirectory + "Block/mouseover_mitte.png", Texture.class);
            marked_rechts = manager.get(workingdirectory + "Block/market_rechts.png", Texture.class);
            marked_links = manager.get(workingdirectory + "Block/market_links.png", Texture.class);
            marked_mitte = manager.get(workingdirectory + "Block/market_mitte.png", Texture.class);
            PinModeBlock_smallimage = manager.get(workingdirectory + "Block/Block_PinMode/smallblock.png", Texture.class);
            DigitalWrite_smallimage = manager.get(workingdirectory + "Block/Block_DigitalWrite/smallblock.png", Texture.class);
            WaitBlock_smallimage = manager.get(workingdirectory + "Block/Block_Wait/smallblock.png", Texture.class);







            //Switch
            switch_background = manager.get(workingdirectory + "switchbackground.png", Texture.class);
            switch_inside = manager.get(workingdirectory + "switchinside.png", Texture.class);
            switch_background_green = manager.get(workingdirectory + "switchbackgroundgreen.png", Texture.class);
            //Switch White
            switch_background_white = manager.get(workingdirectory + "switchbackground_white.png", Texture.class);
            switch_inside_white = manager.get(workingdirectory + "switchinside.png", Texture.class);
            switch_background_green_white = manager.get(workingdirectory + "switchbackground_whitegreen.png", Texture.class);
            //Setup help
            help_arduino_boards = manager.get(workingdirectory + "ArduinoHilfe.png", Texture.class);
            help_raspberrypi_boards = manager.get(workingdirectory + "RaspberryPiHilfe.png", Texture.class);
            help_ev3 = manager.get(workingdirectory + "EV3Hilfe.png", Texture.class);
            help_platforms = manager.get(workingdirectory + "PlatformHilfe.png", Texture.class);
            //wire
            wire = manager.get(workingdirectory + "wire.png", Texture.class);
            //Wire Node
            wire_node = manager.get(workingdirectory + "node.png", Texture.class);
            //Plugin
            pluginwait = manager.get(workingdirectory + "pluginwaiting.png", Texture.class);

            aktion_mittlerermotor = manager.get(workingdirectory + "TabBarBlockBilder/antikaeferblock.png");

        } catch (Exception e) {
            e.printStackTrace();
            DisplayErrors.error = e;
        }


    }

    public static void unload() {


    }
}
