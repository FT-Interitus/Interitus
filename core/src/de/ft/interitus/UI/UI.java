package de.ft.interitus.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;
import de.ft.interitus.*;
import de.ft.interitus.UI.inputfields.Button;
import de.ft.interitus.UI.inputfields.ButtonBar;
import de.ft.interitus.UI.inputfields.check.InputManager;
import de.ft.interitus.UI.settings.SettingsUI;
import de.ft.interitus.UI.setup.SetupWindow;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.data.programm.Data;
import de.ft.interitus.data.user.changes.SaveChanges;
import de.ft.interitus.data.user.experience.ExperienceManager;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.UI.UIOpenSettingsEvent;
import de.ft.interitus.events.UI.UiEventAdapter;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.network.bettertogether.SharedVar;
import de.ft.interitus.utils.RoundRectangle;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static de.ft.interitus.UI.MenuBar.createSubMenu;

public class UI {
    static final Table root = new Table();
    public static Stage stage;
    public static Viewport UIviewport;
    public static OrthographicCamera UIcam;
    public static SpriteBatch UIbatch;

    protected static MenuItem recent;
    protected static MenuItem revert;
    protected static MenuItem redo;
    protected static MenuItem copy;
    protected static MenuItem paste;
    protected static MenuBar menuBar;
    public static SettingsUI set;

    private static boolean issettingsuiopend = false;
    private static boolean issetupuiopend = false;
    private static int curserstate=0;
    Vector3 pos = new Vector3();

    private static boolean verticalrezising=false;
    private static boolean horizontalrezising=false;
    public static boolean curserveränderungsblockade=false;

    public static ButtonBar buttonbar;
    public static Button button_projectstructus;
    public static Button button_start;
    public static Button button_stop;
    public static Button button_debugstart;

    public static void userresize() {
        if (!curserveränderungsblockade){
            if (Gdx.input.getX() > UIVar.BlockBarW + UIVar.abstandvonRand && Gdx.input.getX() < UIVar.BlockBarW + UIVar.abstandvonRand * 2 && Gdx.graphics.getHeight() - Gdx.input.getY() > UIVar.abstandvonRand && Gdx.graphics.getHeight() - Gdx.input.getY() < UIVar.abstandvonRand + UIVar.BlockBarH) {
                if (curserstate != 0) {
                    Gdx.graphics.setSystemCursor(Cursor.SystemCursor.HorizontalResize);
                    curserstate = 0;
                }

            } else if (Gdx.graphics.getHeight() - Gdx.input.getY() > UIVar.abstandvonRand + UIVar.BlockBarH && Gdx.graphics.getHeight() - Gdx.input.getY() < UIVar.abstandvonRand * 2 + UIVar.BlockBarH) {
                if (curserstate != 2) {
                    Gdx.graphics.setSystemCursor(Cursor.SystemCursor.VerticalResize);
                    curserstate = 2;
                }
            } else if (curserstate != 1) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
                curserstate = 1;
            }
    }

        if(curserstate==0 && Gdx.input.isButtonJustPressed(0)){
            verticalrezising=true;
            curserveränderungsblockade=true;
        }
        if(!Gdx.input.isButtonPressed(0)){
            verticalrezising=false;
            curserveränderungsblockade=false;
        }
        if(verticalrezising){
                UIVar.unteneinteilung = Gdx.graphics.getWidth() - Gdx.input.getX() - UIVar.abstandvonRand / 2;
                 if(Gdx.graphics.getWidth()-UIVar.unteneinteilung<BlockTappedBar.tb.getTaps().size()*BlockTappedBar.tb.getTaps().get(0).getTab_button().getW()+ BlockTappedBar.tb.getButtonabstand()*BlockTappedBar.tb.getTaps().size()+20) {
                        UIVar.unteneinteilung=Gdx.graphics.getWidth()-(BlockTappedBar.tb.getTaps().size()*BlockTappedBar.tb.getTaps().get(0).getTab_button().getW()+ BlockTappedBar.tb.getButtonabstand()*BlockTappedBar.tb.getTaps().size()+20);
                 }
                 if(Gdx.graphics.getWidth()-UIVar.unteneinteilung>Gdx.graphics.getWidth()-UIVar.rechtseinraste){
                     UIVar.unteneinteilung=UIVar.rechtseinraste;

                 }

        }


        if(curserstate==2 && Gdx.input.isButtonJustPressed(0)){
            horizontalrezising=true;
            curserveränderungsblockade=true;
        }
        if(!Gdx.input.isButtonPressed(0)){
            horizontalrezising=false;
            curserveränderungsblockade=false;
        }
        if(horizontalrezising){
            UIVar.untenhohe=Gdx.graphics.getHeight()-Gdx.input.getY()-UIVar.abstandvonRand/2;
            if(UIVar.untenhohe<UIVar.untenkante){
                    UIVar.untenhohe=UIVar.untenkante;
            }
            if(UIVar.untenhohe>Gdx.graphics.getHeight()/2){
                UIVar.untenhohe=Gdx.graphics.getHeight()/2;
            }
        }



    }

    public static void updatedragui(ShapeRenderer renderer, boolean flaeche, SpriteBatch batch) {
        userresize();
       // Var.w=Gdx.graphics.getWidth();
        //Var.h=Gdx.graphics.getHeight();
        renderer.begin(ShapeRenderer.ShapeType.Filled);


            renderer.setColor(Settings.theme.ProgrammSpaceColor());





        if (flaeche == true) {/////////////   \/  \/  \/  \/  die programmierfläche wird gedrawd
            UIVar.programmflaeche_h=Gdx.graphics.getHeight() - (UIVar.untenhohe + UIVar.abstandvonRand)  - UIVar.abstandvonRand - (int)menuBar.getTable().getHeight() - UIVar.buttonbarzeile_h - UIVar.abstandvonRand;
            UIVar.programmflaeche_y=UIVar.untenhohe + UIVar.abstandvonRand;
            RoundRectangle.abgerundetesRechteck(renderer, UIVar.abstandvonRand, UIVar.programmflaeche_y, Gdx.graphics.getWidth() - UIVar.abstandvonRand * 2, UIVar.programmflaeche_h, UIVar.radius);
        } else {

                renderer.setColor(Settings.theme.BlocksColor());

            UIVar.BlockBarX=UIVar.abstandvonRand;
            UIVar.BlockBarY=UIVar.abstandvonRand;
            UIVar.BlockBarW=Gdx.graphics.getWidth() - UIVar.abstandvonRand * 2 - UIVar.unteneinteilung;
            UIVar.BlockBarH=UIVar.untenhohe - UIVar.abstandvonRand;//  \/  \/ blockbar wird gedrawed
            RoundRectangle.abgerundetesRechteck(renderer, UIVar.abstandvonRand, UIVar.abstandvonRand, UIVar.BlockBarW, UIVar.BlockBarH, UIVar.radius);

                renderer.setColor(Settings.theme.DeviceConnectionColor());

            RoundRectangle.abgerundetesRechteck(renderer, Gdx.graphics.getWidth() - UIVar.unteneinteilung, UIVar.abstandvonRand, UIVar.unteneinteilung - UIVar.abstandvonRand, UIVar.untenhohe - UIVar.abstandvonRand, UIVar.radius);
        }
        renderer.end();







    }

    public static void init() {
        UI.UIbatch = new SpriteBatch();
        UI.UIcam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        UI.UIviewport = new ScreenViewport(UI.UIcam);

        stage = new Stage(UIviewport, UIbatch);



        root.setFillParent(true);
        stage.addActor(root);

        InputManager.addProcessor(stage);
        InputManager.updateMultiplexer();

        set = new SettingsUI();

        menuBar = new MenuBar();


        root.add(menuBar.getTable()).expandX().fillX().row();
        root.add().expand().fill().row();


        de.ft.interitus.UI.MenuBar.createMenus();


        EventVar.uiEventManager.addListener(new UiEventAdapter() {
            @Override
            public void UIOpenSettingsEvent(UIOpenSettingsEvent e) {
               set.show();
            }
        });






        Thread UIthread = new Thread() {

            @Override
            public void run() {
                Timer time = new Timer();
                time.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {

                        try {

                            ////////////recent//////////////////////////


                            for (int i = 0; i < Data.path.size(); i++) {
                                if (!(new File(Data.path.get(i)).exists())) {
                                    Data.path.remove(i);
                                    Data.filename.remove(i);
                                }
                            }

                            recent.setDisabled(Data.path.size() == 0);


                            ///////////////////////////////////


                            /////////////revert//////////////



                        } catch (Exception e) {
                            DisplayErrors.error = e;
                            e.printStackTrace(); //for debug to find errors
                        }


                        //Check íf Settings is open///////////
                        if (!issettingsuiopend && SettingsUI.isopend()) {

                            issettingsuiopend = true;
                            ExperienceManager.settingstimetemp = (double) System.currentTimeMillis() / (double) 3600000;

                        }

                        if (issettingsuiopend && !SettingsUI.isopend()) {

                            issettingsuiopend = false;
                            ExperienceManager.settingsthistime = ExperienceManager.settingsthistime + (double) System.currentTimeMillis() / (double) 3600000 - ExperienceManager.settingstimetemp;
                        }

                        ////////////////////////////////

                        //Check if Setup is open///

                        if (!issetupuiopend && SetupWindow.isopend()) {

                            issetupuiopend = true;
                            ExperienceManager.setuptimetemp = (double) System.currentTimeMillis() / (double) 3600000;

                        }

                        if (issetupuiopend && !SetupWindow.isopend()) {

                            issetupuiopend = false;
                            ExperienceManager.setupthistime = ExperienceManager.setupthistime + (double) System.currentTimeMillis() / (double) 3600000 - ExperienceManager.setuptimetemp;
                        }


                        ///////////////////Check if shortcut is pressed//////////


                        if(SharedVar.amiclinet) {
                            de.ft.interitus.UI.MenuBar.fileMenu.setVisible(false);
                        }

                        ///////////////////////////
                    }
                }, 0, 500);
            }
        };


        UIthread.start();


    }

    public static void initnachassetsloading(){
        /////////////////Button Bar zusammensetzung//////////////////////
        button_debugstart=new Button();
        button_debugstart.setImage(AssetLoader.img_debugstart);
        button_debugstart.setImage_mouseover(AssetLoader.img_debugstart_mouseover);
        button_debugstart.setImage_pressed(AssetLoader.img_debugstart_pressed);
        button_projectstructus=new Button();
        button_projectstructus.setImage(AssetLoader.img_projectstructur);
        button_projectstructus.setImage_mouseover(AssetLoader.img_projectstructur_mouseover);
        button_projectstructus.setImage_pressed(AssetLoader.img_projectstructur_pressed);
        button_start=new Button();
        button_start.setImage(AssetLoader.img_startbutton);
        button_start.setImage_mouseover(AssetLoader.img_startbutton_mouseover);
        button_start.setImage_pressed(AssetLoader.img_startbutton_pressed);
        button_stop=new Button();
        button_stop.setImage(AssetLoader.img_stopbutton);
        button_stop.setImage_mouseover(AssetLoader.img_stopbutton_mouseover);
        button_stop.setImage_pressed(AssetLoader.img_stopbutton_pressed);
        buttonbar=new ButtonBar(0,0,20,20);
        buttonbar.addButton(button_projectstructus);
        buttonbar.addButton(button_stop);
        buttonbar.addButton(button_debugstart);
        buttonbar.addButton(button_start);

    }

    public static void update() {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));

        //root.setPosition(0,0);


        root.setPosition(UIcam.position.x - ((float) Gdx.graphics.getWidth()) / 2, UIcam.position.y - ((float) Gdx.graphics.getHeight()) / 2);
        stage.draw();

        recent.setSubMenu(createSubMenu(Data.filename.size(), GetStringArray(Data.filename)));

        buttonbar.setX(Gdx.graphics.getWidth()-10);
        buttonbar.setY(UIVar.programmflaeche_y+UIVar.programmflaeche_h+UIVar.abstandvonRand);
        buttonbar.draw(UIbatch);

    }


    public static String[] GetStringArray(ArrayList<String> arr) {

        // declaration and initialise String Array
        String[] str = new String[arr.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {


            // Assign each value to String array
            str[j] = arr.get(j);
        }

        return str;
    }

    public static void updateView(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
