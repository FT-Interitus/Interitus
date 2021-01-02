/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.Program;

import java.lang.reflect.ParameterizedType;


public class AssetLoader {


    public static Pixmap backcursor;

    public static String group = "";
    public static String workingdirectory = "";

    public static Texture block_left;
    public static Texture block_middle;
    public static Texture block_right;

    public static Texture green_bar_left;
    public static Texture green_bar_middle;
    public static Texture green_bar_right;

    public static Texture orange_bar_left;
    public static Texture orange_bar_middle;
    public static Texture orange_bar_right;

    public static Texture yellow_bar_left;
    public static Texture yellow_bar_middle;
    public static Texture yellow_bar_right;

    public static Texture red_bar_left;
    public static Texture red_bar_middle;
    public static Texture red_bar_right;

    public static Texture blue_bar_left;
    public static Texture blue_bar_middle;
    public static Texture blue_bar_right;

    public static Texture turquoise_bar_left;
    public static Texture turquoise_bar_middle;
    public static Texture turquoise_bar_right;


    //Arduino Device Images
    public static Texture arduinonanoimage;
    public static Texture arduinounoimage;
    public static Texture arduinomegaimage;


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



    //Wire
    public static Texture wire;
    //Wire Nod
    public static Texture wire_node;

    //Plugin Warte Bild
    public static Texture pluginwait;





    public static Texture img_mappe1;
    public static Texture img_mappe2;
    public static Texture img_mappe3;
    public static Texture img_mappe4;
    public static Texture img_mappe5;
    public static Texture img_mappe6;


    public static BitmapFont welcomefont;//font
    public static BitmapFont defaultfont;//font

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

    public static Texture img_editor;
    public static Texture img_editor_mouseover;
    public static Texture img_editor_pressed;

    public static Texture img_addrunconfig;
    public static Texture img_addrunconfig_mouseover;
    public static Texture img_addrunconfig_pressed;

    public static Texture waitforfinishbuild;

    ///////////////////////////-BlockParameter Bilder-/////////////////////////////

    //////////////////////////-TabBar-//////////////////////////////
    public static Texture img_Tab;



    public static Texture WaitBlock_description_image;


    public static Texture PinModeBlock_description_image;


    public static Texture DigitalWrite_description_image;

    public static Texture if_description;
    public static Texture loop_description;
    public static Texture setup_description;
    public static Texture math_description;
    public static Texture input_description;
    public static Texture output_description;
    public static Texture zufall_description;
    public static Texture vergleich_description;
    public static Texture bereich_description;
    public static Texture Start_description;
    public static Texture StatusLight_description;




    ////ParameterBilder :
    public static Texture Parameter_High_Low;
    public static Texture Parameter_Pin;
    public static Texture Parameter_IO;
    public static Texture Parameter_wait;
    public static Texture Parameter_first;
    public static Texture Parameter_second;
    public static Texture Parameter_third;
    public static Texture Parameter_fourth;
    public static Texture Parameter_isequal;
    public static Texture Parameter_minus;
    public static Texture Parameter_plus;
    public static Texture Parameter_Mal;
    public static Texture Parameter_Geteilt;
    public static Texture Parameter_squareroot;
    public static Texture Parameter_if;
    public static Texture Parameter_abs;
    public static Texture Parameter_fkt;
    public static Texture Parameter_a;
    public static Texture Parameter_b;
    public static Texture Parameter_c;
    public static Texture Parameter_d;
    public static Texture Parameter_randomdice;
    public static Texture Parameter_lowerlimit;
    public static Texture Parameter_upperlimit;
    public static Texture Parameter_digital;
    public static Texture Parameter_analog;
    public static Texture Parameter_farbe;



    public static Texture mouseover_left;
    public static Texture mouse_over_right;
    public static Texture mouse_over_mitte;

    public static Texture marked_left;
    public static Texture marked_right;
    public static Texture marked_mitte;

    public static Texture Plug_IntParameter;
    public static Texture Plug_StringParameter;
    public static Texture Plug_BooleanParameter;

    public static Texture aufklapppfeil;

    //UI
    public static Texture close_notification;
    public static Texture close_notification_mouseover;
    public static Texture information;

    public static Texture Tabbarhomeicon;


    public static AssetManager manager = new AssetManager();

    public static BitmapFont ParameterFont;

    public static Object save(String file, Class Type) {
        return manager.get(workingdirectory + file, Type);
    }


    public static void load() {


        try {
            group = "ParameterBilder";
            manager.load(workingdirectory + "Block/Parameter/WaitParameter.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/Parameter_Pin.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/Parameter_IO.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/parameter_high_low.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/istgleich.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/erstens.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/zweitens.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/drittens.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/viertens.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/Minus.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/squareroot.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/Plus.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/if.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/MalKreuz.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/Geteilt.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/absolutevalue.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/a.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/b.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/c.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/d.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/advancedFunktion.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/randomdice.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/lowerlimit.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/upperlimit.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/digital.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/analog.png", Texture.class);
            manager.load(workingdirectory + "Block/Parameter/farb_parameter.png", Texture.class);




            // manager.load(new AssetDescriptor(Objects.requireNonNull(Programm.INSTANCE.getClass().getClassLoader().getResource("../statistics/test.png")).getFile(),Texture.class));


            //Programm.INSTANCE.getClass().getResource()







            group = "Schriftarten";
            try {
                //schriftart
                FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/comicsans.ttf"));
                FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
                parameter.size = 50;
                welcomefont = generator.generateFont(parameter); // font size 12 pixels

                FreeTypeFontGenerator generator1 = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/defaultfont.ttf"));
                FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
                parameter1.size = 15;

                defaultfont = generator1.generateFont(parameter1);

                FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/agency_fb.ttf"));
                FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
                parameter2.color=new Color(0f,0f,0f,1f);
                parameter2.size = 16;

                ParameterFont = generator2.generateFont(parameter2);

                generator.dispose(); // don't forget to dispose to avoid memory leaks!
            } catch (Exception e) {
                Program.logger.severe("Error while loading Font");
            }


            group = "Plugs";
            manager.load(workingdirectory + "Block/ParameterStecker/ZahlParameterStecker.png", Texture.class);
            manager.load(workingdirectory + "Block/ParameterStecker/TextParameterStecker.png", Texture.class);
            manager.load(workingdirectory + "Block/ParameterStecker/BooleanParameterStecker.png", Texture.class);


            group = "TabBar";

            backcursor = new Pixmap(Gdx.files.internal("backcursor.png"));


            manager.load(workingdirectory + "TabBar/Tabimage.png", Texture.class);


            manager.load(workingdirectory + "arduinomegaminiimage.png", Texture.class);
            manager.load(workingdirectory + "arduinonanominiimage.png", Texture.class);
            manager.load(workingdirectory + "arduinounominiimage.png", Texture.class);

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

            manager.load(workingdirectory + "ButtonBar/editorbutton.png", Texture.class);
            manager.load(workingdirectory + "ButtonBar/editorbutton_mouseover.png", Texture.class);
            manager.load(workingdirectory + "ButtonBar/editorbutton_pressed.png", Texture.class);

            manager.load(workingdirectory + "ButtonBar/addrunconfig.png", Texture.class);
            manager.load(workingdirectory + "ButtonBar/addrunconfig_mouseover.png", Texture.class);
            manager.load(workingdirectory + "ButtonBar/addrunconfig_pressed.png", Texture.class);

            manager.load(workingdirectory + "loadinganimation.png", Texture.class);

            group = "mappen";
            manager.load(workingdirectory + "Bar/Mappe1.png", Texture.class);
            manager.load(workingdirectory + "Bar/Mappe2.png", Texture.class);
            manager.load(workingdirectory + "Bar/Mappe3.png", Texture.class);
            manager.load(workingdirectory + "Bar/Mappe4.png", Texture.class);
            manager.load(workingdirectory + "Bar/Mappe5.png", Texture.class);
            manager.load(workingdirectory + "Bar/Mappe6.png", Texture.class);


            group = "Blöcke";

            manager.load(workingdirectory + "Block/blockmask/mouseover_links.png", Texture.class);
            manager.load(workingdirectory + "Block/blockmask/mouseover_links.png", Texture.class);
            manager.load(workingdirectory + "Block/blockmask/mouseover_links.png", Texture.class);

            manager.load(workingdirectory + "Block/blockmask/block_right.png", Texture.class);
            manager.load(workingdirectory + "Block/blockmask/block_middle.png", Texture.class);
            manager.load(workingdirectory + "Block/blockmask/block_left.png", Texture.class);

            manager.load(workingdirectory + "Block/color_bar/green_left.png", Texture.class);
            manager.load(workingdirectory + "Block/color_bar/green_middle.png", Texture.class);
            manager.load(workingdirectory + "Block/color_bar/green_right.png", Texture.class);

            manager.load(workingdirectory + "Block/color_bar/orange_left.png", Texture.class);
            manager.load(workingdirectory + "Block/color_bar/orange_middle.png", Texture.class);
            manager.load(workingdirectory + "Block/color_bar/orange_right.png", Texture.class);

            manager.load(workingdirectory + "Block/color_bar/yellow_left.png", Texture.class);
            manager.load(workingdirectory + "Block/color_bar/yellow_middle.png", Texture.class);
            manager.load(workingdirectory + "Block/color_bar/yellow_right.png", Texture.class);

            manager.load(workingdirectory + "Block/color_bar/red_left.png", Texture.class);
            manager.load(workingdirectory + "Block/color_bar/red_middle.png", Texture.class);
            manager.load(workingdirectory + "Block/color_bar/red_right.png", Texture.class);

            manager.load(workingdirectory + "Block/color_bar/blue_left.png", Texture.class);
            manager.load(workingdirectory + "Block/color_bar/blue_middle.png", Texture.class);
            manager.load(workingdirectory + "Block/color_bar/blue_right.png", Texture.class);

            manager.load(workingdirectory + "Block/color_bar/turquoise_left.png", Texture.class);
            manager.load(workingdirectory + "Block/color_bar/turquoise_middle.png", Texture.class);
            manager.load(workingdirectory + "Block/color_bar/turquoise_right.png", Texture.class);



            manager.load(workingdirectory + "connector.png", Texture.class);
            manager.load(workingdirectory + "connector_offerd.png", Texture.class);
            //////////////////////////////////////////////////////////////////////////////////////////////////////////

            manager.load(workingdirectory + "Block/blockmask/mouseover_links.png", Texture.class);
            manager.load(workingdirectory + "Block/blockmask/mouseover_rechts.png", Texture.class);
            manager.load(workingdirectory + "Block/blockmask/mouseover_mitte.png", Texture.class);
            manager.load(workingdirectory + "Block/blockmask/market_links.png", Texture.class);
            manager.load(workingdirectory + "Block/blockmask/market_rechts.png", Texture.class);
            manager.load(workingdirectory + "Block/blockmask/market_mitte.png", Texture.class);

            manager.load(workingdirectory + "Block/Block_Wait/beschreibungsbild.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_PinMode/beschreibungsbild.png", Texture.class);
            manager.load(workingdirectory + "Block/Block_DigitalWrite/beschreibungsbild.png", Texture.class);
            manager.load(workingdirectory + "Block/description_image/if_description.png", Texture.class);
            manager.load(workingdirectory + "Block/description_image/loop_description.png", Texture.class);
            manager.load(workingdirectory + "Block/description_image/setup_description.png", Texture.class);
            manager.load(workingdirectory + "Block/description_image/math_description.png", Texture.class);
            manager.load(workingdirectory + "Block/description_image/input_description.png", Texture.class);
            manager.load(workingdirectory + "Block/description_image/output_description.png", Texture.class);
            manager.load(workingdirectory + "Block/description_image/zufall_description.png", Texture.class);
            manager.load(workingdirectory + "Block/description_image/vergleich_description.png", Texture.class);
            manager.load(workingdirectory + "Block/description_image/bereich_description.png", Texture.class);
            manager.load(workingdirectory + "Block/description_image/Start_description.png", Texture.class);
            manager.load(workingdirectory + "Block/description_image/StatusLight_description.png", Texture.class);






            manager.load(workingdirectory + "aufklapppfeil.png", Texture.class);


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

            group = "Wire";
            //Wire
            manager.load(workingdirectory + "wire.png", Texture.class);
            group = "Wire-Node";
            //Wire Node
            manager.load(workingdirectory + "node.png", Texture.class);
            group = "Plugin";
            manager.load(workingdirectory + "pluginwaiting.png", Texture.class);
            group = "UI";
            manager.load(workingdirectory+"UI/close_notification.png",Texture.class);
            manager.load(workingdirectory+"UI/close_notification_mouseover.png",Texture.class);

            manager.load(workingdirectory+"UI/information.png",Texture.class);

            manager.load(workingdirectory+"Icon/TabBar.png",Texture.class);


        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }


    }

    public static void save() {


        try {
            group = "";

            //ParameterBilder
            group = "ParameterBilder";
            Parameter_wait = manager.get(workingdirectory + "Block/Parameter/WaitParameter.png", Texture.class);
            Parameter_High_Low = manager.get(workingdirectory + "Block/Parameter/parameter_high_low.png", Texture.class);
            Parameter_Pin = manager.get(workingdirectory + "Block/Parameter/Parameter_Pin.png", Texture.class);
            Parameter_IO = manager.get(workingdirectory + "Block/Parameter/Parameter_IO.png", Texture.class);
            Parameter_isequal = manager.get(workingdirectory + "Block/Parameter/istgleich.png", Texture.class);
            Parameter_first = manager.get(workingdirectory + "Block/Parameter/erstens.png", Texture.class);
            Parameter_second = manager.get(workingdirectory + "Block/Parameter/zweitens.png", Texture.class);
            Parameter_third = manager.get(workingdirectory + "Block/Parameter/drittens.png", Texture.class);
            Parameter_fourth = manager.get(workingdirectory + "Block/Parameter/viertens.png", Texture.class);
            Parameter_minus = manager.get(workingdirectory + "Block/Parameter/Minus.png", Texture.class);
            Parameter_squareroot = manager.get(workingdirectory + "Block/Parameter/squareroot.png", Texture.class);
            Parameter_plus = manager.get(workingdirectory + "Block/Parameter/Plus.png", Texture.class);
            Parameter_if = manager.get(workingdirectory + "Block/Parameter/if.png", Texture.class);
            Parameter_Mal = manager.get(workingdirectory + "Block/Parameter/MalKreuz.png", Texture.class);
            Parameter_Geteilt = manager.get(workingdirectory + "Block/Parameter/Geteilt.png", Texture.class);
            Parameter_abs = manager.get(workingdirectory + "Block/Parameter/absolutevalue.png", Texture.class);
           Parameter_fkt = manager.get(workingdirectory + "Block/Parameter/advancedFunktion.png", Texture.class);
            Parameter_a= manager.get(workingdirectory + "Block/Parameter/a.png", Texture.class);
            Parameter_b= manager.get(workingdirectory + "Block/Parameter/b.png", Texture.class);
            Parameter_c= manager.get(workingdirectory + "Block/Parameter/c.png", Texture.class);
            Parameter_d= manager.get(workingdirectory + "Block/Parameter/d.png", Texture.class);
            Parameter_randomdice= manager.get(workingdirectory + "Block/Parameter/randomdice.png", Texture.class);
           Parameter_lowerlimit = manager.get(workingdirectory + "Block/Parameter/lowerlimit.png", Texture.class);
            Parameter_upperlimit = manager.get(workingdirectory + "Block/Parameter/upperlimit.png", Texture.class);
            Parameter_digital = manager.get(workingdirectory + "Block/Parameter/digital.png", Texture.class);
            Parameter_analog = manager.get(workingdirectory + "Block/Parameter/analog.png", Texture.class);
            Parameter_farbe = manager.get(workingdirectory + "Block/Parameter/farb_parameter.png", Texture.class);





            Plug_IntParameter = manager.get(workingdirectory + "Block/ParameterStecker/ZahlParameterStecker.png", Texture.class);
            Plug_StringParameter = manager.get(workingdirectory + "Block/ParameterStecker/TextParameterStecker.png", Texture.class);
            Plug_BooleanParameter = manager.get(workingdirectory + "Block/ParameterStecker/BooleanParameterStecker.png", Texture.class);


            arduinomegaimage = manager.get(workingdirectory + "arduinomegaminiimage.png", Texture.class);
            arduinonanoimage = manager.get(workingdirectory + "arduinonanominiimage.png", Texture.class);
            arduinounoimage = manager.get(workingdirectory + "arduinounominiimage.png", Texture.class);


            img_Tab = manager.get(workingdirectory + "TabBar/Tabimage.png", Texture.class);

            img_startbutton = manager.get(workingdirectory + "ButtonBar/startbutton.png", Texture.class);
            img_startbutton_mouseover = manager.get(workingdirectory + "ButtonBar/startbutton_mouseover.png", Texture.class);
            img_startbutton_pressed = manager.get(workingdirectory + "ButtonBar/startbutton_pressed.png", Texture.class);

            img_stopbutton = manager.get(workingdirectory + "ButtonBar/stopbutton.png", Texture.class);
            img_stopbutton_mouseover = manager.get(workingdirectory + "ButtonBar/stopbutton_mouseover.png", Texture.class);
            img_stopbutton_pressed = manager.get(workingdirectory + "ButtonBar/stopbutton_pressed.png", Texture.class);

            img_projectstructur = manager.get(workingdirectory + "ButtonBar/projektstrukturbutton.png", Texture.class);
            img_projectstructur_mouseover = manager.get(workingdirectory + "ButtonBar/projektstrukturbutton_mouseover.png", Texture.class);
            img_projectstructur_pressed = manager.get(workingdirectory + "ButtonBar/projektstrukturbutton_pressed.png", Texture.class);

            img_debugstart = manager.get(workingdirectory + "ButtonBar/debugbutton.png", Texture.class);
            img_debugstart_mouseover = manager.get(workingdirectory + "ButtonBar/debugbutton_mouseover.png", Texture.class);
            img_debugstart_pressed = manager.get(workingdirectory + "ButtonBar/debugbutton_pressed.png", Texture.class);

            img_editor = manager.get(workingdirectory + "ButtonBar/editorbutton.png", Texture.class);
            img_editor_mouseover = manager.get(workingdirectory + "ButtonBar/editorbutton_mouseover.png", Texture.class);
            img_editor_pressed = manager.get(workingdirectory + "ButtonBar/editorbutton_pressed.png", Texture.class);

            img_addrunconfig = manager.get(workingdirectory + "ButtonBar/addrunconfig.png", Texture.class);
            img_addrunconfig_mouseover = manager.get(workingdirectory + "ButtonBar/addrunconfig_mouseover.png", Texture.class);
            img_addrunconfig_pressed = manager.get(workingdirectory + "ButtonBar/addrunconfig_pressed.png", Texture.class);


            waitforfinishbuild = manager.get(workingdirectory + "loadinganimation.png", Texture.class);


            img_mappe1 = manager.get(workingdirectory + "Bar/Mappe1.png", Texture.class);
            img_mappe2 = manager.get(workingdirectory + "Bar/Mappe2.png", Texture.class);
            img_mappe3 = manager.get(workingdirectory + "Bar/Mappe3.png", Texture.class);
            img_mappe4 = manager.get(workingdirectory + "Bar/Mappe4.png", Texture.class);
            img_mappe5 = manager.get(workingdirectory + "Bar/Mappe5.png", Texture.class);
            img_mappe6 = manager.get(workingdirectory + "Bar/Mappe6.png", Texture.class);



            connector = manager.get(workingdirectory + "connector.png", Texture.class);
            connector_offerd = manager.get(workingdirectory + "connector_offerd.png", Texture.class);

          block_right =  manager.get(workingdirectory + "Block/blockmask/block_right.png", Texture.class);
           block_middle = manager.get(workingdirectory + "Block/blockmask/block_middle.png", Texture.class);
          block_left = manager.get(workingdirectory + "Block/blockmask/block_left.png", Texture.class);

          green_bar_left =  manager.get(workingdirectory + "Block/color_bar/green_left.png", Texture.class);
            green_bar_middle =  manager.get(workingdirectory + "Block/color_bar/green_middle.png", Texture.class);
            green_bar_right =  manager.get(workingdirectory + "Block/color_bar/green_right.png", Texture.class);

            orange_bar_left = manager.get(workingdirectory + "Block/color_bar/orange_left.png", Texture.class);
            orange_bar_middle = manager.get(workingdirectory + "Block/color_bar/orange_middle.png", Texture.class);
            orange_bar_right = manager.get(workingdirectory + "Block/color_bar/orange_right.png", Texture.class);

            yellow_bar_left = manager.get(workingdirectory + "Block/color_bar/yellow_left.png", Texture.class);
            yellow_bar_middle = manager.get(workingdirectory + "Block/color_bar/yellow_middle.png", Texture.class);
            yellow_bar_right = manager.get(workingdirectory + "Block/color_bar/yellow_right.png", Texture.class);

            red_bar_left = manager.get(workingdirectory + "Block/color_bar/red_left.png", Texture.class);
            red_bar_middle = manager.get(workingdirectory + "Block/color_bar/red_middle.png", Texture.class);
            red_bar_right = manager.get(workingdirectory + "Block/color_bar/red_right.png", Texture.class);

            blue_bar_left = manager.get(workingdirectory + "Block/color_bar/blue_left.png", Texture.class);
            blue_bar_middle = manager.get(workingdirectory + "Block/color_bar/blue_middle.png", Texture.class);
            blue_bar_right = manager.get(workingdirectory + "Block/color_bar/blue_right.png", Texture.class);

            turquoise_bar_left = manager.get(workingdirectory + "Block/color_bar/turquoise_left.png", Texture.class);
            turquoise_bar_middle = manager.get(workingdirectory + "Block/color_bar/turquoise_middle.png", Texture.class);
            turquoise_bar_right = manager.get(workingdirectory + "Block/color_bar/turquoise_right.png", Texture.class);



            mouse_over_right = manager.get(workingdirectory + "Block/blockmask/mouseover_rechts.png", Texture.class);
            mouseover_left = manager.get(workingdirectory + "Block/blockmask/mouseover_links.png", Texture.class);
            mouse_over_mitte = manager.get(workingdirectory + "Block/blockmask/mouseover_mitte.png", Texture.class);

            marked_right = manager.get(workingdirectory + "Block/blockmask/market_rechts.png", Texture.class);
            marked_left = manager.get(workingdirectory + "Block/blockmask/market_links.png", Texture.class);
            marked_mitte = manager.get(workingdirectory + "Block/blockmask/market_mitte.png", Texture.class);

            WaitBlock_description_image = manager.get(workingdirectory + "Block/Block_Wait/beschreibungsbild.png", Texture.class);
            PinModeBlock_description_image = manager.get(workingdirectory + "Block/Block_PinMode/beschreibungsbild.png", Texture.class);
            DigitalWrite_description_image = manager.get(workingdirectory + "Block/Block_DigitalWrite/beschreibungsbild.png", Texture.class);

            if_description = manager.get(workingdirectory + "Block/description_image/if_description.png", Texture.class);
            loop_description = manager.get(workingdirectory + "Block/description_image/loop_description.png", Texture.class);
            setup_description = manager.get(workingdirectory + "Block/description_image/setup_description.png", Texture.class);
            math_description = manager.get(workingdirectory + "Block/description_image/math_description.png", Texture.class);
            input_description = manager.get(workingdirectory + "Block/description_image/input_description.png", Texture.class);
            output_description = manager.get(workingdirectory + "Block/description_image/output_description.png", Texture.class);
            zufall_description = manager.get(workingdirectory + "Block/description_image/zufall_description.png", Texture.class);
            vergleich_description = manager.get(workingdirectory + "Block/description_image/vergleich_description.png", Texture.class);
            bereich_description = manager.get(workingdirectory + "Block/description_image/bereich_description.png", Texture.class);
            Start_description = manager.get(workingdirectory + "Block/description_image/Start_description.png", Texture.class);
            StatusLight_description = manager.get(workingdirectory + "Block/description_image/StatusLight_description.png", Texture.class);








            aufklapppfeil = manager.get(workingdirectory + "aufklapppfeil.png", Texture.class);


            //Switch
            switch_background = manager.get(workingdirectory + "switchbackground.png", Texture.class);
            switch_inside = manager.get(workingdirectory + "switchinside.png", Texture.class);
            switch_background_green = manager.get(workingdirectory + "switchbackgroundgreen.png", Texture.class);
            //Switch White
            switch_background_white = manager.get(workingdirectory + "switchbackground_white.png", Texture.class);
            switch_inside_white = manager.get(workingdirectory + "switchinside.png", Texture.class);
            switch_background_green_white = manager.get(workingdirectory + "switchbackground_whitegreen.png", Texture.class);

            //wire
            wire = manager.get(workingdirectory + "wire.png", Texture.class);
            //Wire Node
            wire_node = manager.get(workingdirectory + "node.png", Texture.class);
            //Plugin
            pluginwait = manager.get(workingdirectory + "pluginwaiting.png", Texture.class);
            //UI
           close_notification =  manager.get(workingdirectory+"UI/close_notification.png",Texture.class);
            close_notification_mouseover =  manager.get(workingdirectory+"UI/close_notification_mouseover.png",Texture.class);

            information =  manager.get(workingdirectory+"UI/information.png",Texture.class);

            Tabbarhomeicon=manager.get(workingdirectory+"Icon/TabBar.png",Texture.class);
        } catch (Exception e) {
            e.printStackTrace();
            DisplayErrors.error = e;
        }


    }


}
