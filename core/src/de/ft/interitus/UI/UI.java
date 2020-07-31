package de.ft.interitus.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ft.interitus.UI.UIElements.dropdownmenue.DropDownElement;
import de.ft.interitus.UI.UIElements.dropdownmenue.DropDownMenue;
import de.ft.interitus.utils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;
import com.kotcrab.vis.ui.widget.VisTextField;
import de.ft.interitus.*;
import de.ft.interitus.Block.Block;
import de.ft.interitus.UI.UIElements.UIElements.Button;
import de.ft.interitus.UI.UIElements.UIElementBar;
import de.ft.interitus.UI.UIElements.TabBar.Tab;
import de.ft.interitus.UI.UIElements.TabBar.TabBar;
import de.ft.interitus.UI.UIElements.check.Check;
import de.ft.interitus.UI.UIElements.check.InputManager;
import de.ft.interitus.UI.editor.Editor;
import de.ft.interitus.UI.projectsettings.ProjectSettingsUI;
import de.ft.interitus.UI.settings.SettingsUI;
import de.ft.interitus.UI.setup.SetupWindow;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.datamanager.programmdata.Data;
import de.ft.interitus.datamanager.programmdata.experience.ExperienceManager;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.UI.UIOpenSettingsEvent;
import de.ft.interitus.events.UI.UiEventAdapter;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.network.bettertogether.SharedVar;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.animation.Animation;

import java.io.File;
import de.ft.interitus.utils.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static de.ft.interitus.UI.MenuBar.createSubMenu;

public class UI {
    static final Table root = new Table();
    public static Stage stage;
    public static Viewport UIviewport;
    public static OrthographicCamera UIcam;
    public static SpriteBatch UIbatch;
    public static SettingsUI set;
    public static ProjectSettingsUI proset;

    public static UIElementBar buttonbar;
    public static Button button_projectstructus;
    public static Button button_start;
    public static Button button_stop;
    public static Button button_debugstart;
    public static Button button_editor;
    public static TabBar tabbar;
    public static GlyphLayout glyphLayout = new GlyphLayout();
    public static BitmapFont font = new BitmapFont();
    public static Check check = new Check();
    protected static MenuItem recent;
    protected static MenuItem revert;
    protected static MenuItem redo;
    protected static MenuItem copy;
    protected static MenuItem paste;
     public static MenuBar menuBar;
    static ArrayList<VisTextField> textFielder = new ArrayList<>();
    private static boolean issettingsuiopend = false;
    private static boolean issetupuiopend = false;
    private static Block markedblock;
    private static int wishaniposition=-170-UIVar.abstandvonRand;
    boolean actiondone = false;
    static boolean isuilock = false;
    public static DropDownMenue runselection;

    public static void updatedragui(ShapeRenderer renderer, boolean flaeche, SpriteBatch batch) {
        if(!UIVar.uilocked) {
            BlockTappedBar.userresize();
        }
        // Var.w=Gdx.graphics.getWidth();
        //Var.h=Gdx.graphics.getHeight();
        renderer.begin(ShapeRenderer.ShapeType.Filled);


        renderer.setColor(Settings.theme.ProgrammSpaceColor());


        if (flaeche == true) {/////////////   \/  \/  \/  \/  die programmierfläche wird gedrawd
            UIVar.programmflaeche_h = Gdx.graphics.getHeight() - (UIVar.untenhohe + UIVar.abstandvonRand) - UIVar.abstandvonRand - (int) menuBar.getTable().getHeight() - UIVar.buttonbarzeile_h - UIVar.abstandvonRand;
            UIVar.programmflaeche_y = UIVar.untenhohe + UIVar.abstandvonRand;
            renderer.roundendrect(UIVar.abstandvonRand, UIVar.programmflaeche_y, Gdx.graphics.getWidth() - UIVar.abstandvonRand * 2, UIVar.programmflaeche_h, UIVar.radius);
        } else {

            renderer.setColor(Settings.theme.BlocksColor());

            UIVar.BlockBarX = UIVar.abstandvonRand;
            UIVar.BlockBarY = UIVar.abstandvonRand;
            UIVar.BlockBarW = Gdx.graphics.getWidth() - UIVar.abstandvonRand * 2 - UIVar.unteneinteilung;
            UIVar.BlockBarH = UIVar.untenhohe - UIVar.abstandvonRand;//  \/  \/ blockbar wird gedrawed
            renderer.roundendrect( UIVar.abstandvonRand, UIVar.abstandvonRand, UIVar.BlockBarW, UIVar.BlockBarH, UIVar.radius);

            renderer.setColor(Settings.theme.DeviceConnectionColor());

            renderer.roundendrect( Gdx.graphics.getWidth() - UIVar.unteneinteilung, UIVar.abstandvonRand, UIVar.unteneinteilung - UIVar.abstandvonRand, UIVar.untenhohe - UIVar.abstandvonRand, UIVar.radius);
        }
        renderer.end();




        if(markedblock!=ProjectManager.getActProjectVar().markedblock) {
            UIVar.isBlockSettingsopen = false;
            for (int i = 0; i < textFielder.size(); i++) {
                textFielder.get(i).removeListener(textFielder.get(i).getListeners().get(0));
                textFielder.get(i).remove();
            }
            textFielder.clear();
        }

/**
 * If you move the Cursor in a TextField the program should not move itself
 */
        var lock = false;
        for (VisTextField visTextField : textFielder) {
            if (visTextField.hasKeyboardFocus()) {
                lock = true;
            }
        }
        UIVar.moveprogrammlock = lock;



        if (markedblock != null && markedblock.getBlocktype().getBlockParameter() != null&&markedblock==ProjectManager.getActProjectVar().markedblock) {


            UIVar.blockeinstellungen_w = 170;
            UIVar.blockeinstellungen_h = UIVar.programmflaeche_h - UIVar.abstandvonRand * 2;
            UIVar.blockeinstellungen_x = Gdx.graphics.getWidth() - UIVar.abstandvonRand * 2 - UIVar.blockeinstellungen_w - wishaniposition;
            UIVar.blockeinstellungen_y = UIVar.programmflaeche_y + UIVar.abstandvonRand;

            if(wishaniposition<0){
                wishaniposition+=(0-wishaniposition)*0.1f;
            }


            renderer.begin(ShapeRenderer.ShapeType.Filled);
            renderer.setColor(81f / 255f, 97f / 255f, 114f / 255f, 1f);
            renderer.rect(UIVar.blockeinstellungen_x, UIVar.blockeinstellungen_y, UIVar.blockeinstellungen_w, UIVar.blockeinstellungen_h);
            renderer.end();
            UIbatch.begin();


            for (int i = 0; i < markedblock.getBlocktype().getBlockParameter().size(); i++) {
                try {
                    if (!UIVar.isBlockSettingsopen) {
                        textFielder.add(new VisTextField(markedblock.getBlocktype().getBlockParameter().get(i).getParameter().toString()));
                        textFielder.get(textFielder.size() - 1).addListener(new ChangeListener() {
                            @Override
                            public void changed(ChangeEvent event, Actor actor) {
                                ProjectManager.getActProjectVar().markedblock.getBlocktype().getBlockParameter().get(textFielder.indexOf(actor)).setParameter(((VisTextField) actor).getText());
                            }
                        });

                        UI.stage.addActor(textFielder.get(i));
                    }

                    textFielder.get(i).setWidth(UIVar.blockeinstellungen_w - 40);
                    textFielder.get(i).setPosition(UIVar.blockeinstellungen_x + 5, UIVar.blockeinstellungen_y + UIVar.blockeinstellungen_h - 20 - UIVar.abstandText - glyphLayout.height - (i * (glyphLayout.height + UIVar.abstandzwischenparametern + UIVar.abstandText + textFielder.get(i).getHeight()) + 3) - textFielder.get(i).getHeight());
                    glyphLayout.setText(font, markedblock.getBlocktype().getBlockParameter().get(i).getParameterName());

                    font.draw(UIbatch, glyphLayout, UIVar.blockeinstellungen_x + 5, UIVar.blockeinstellungen_y + UIVar.blockeinstellungen_h - 20 - (i * (glyphLayout.height + textFielder.get(i).getHeight() + UIVar.abstandzwischenparametern + UIVar.abstandText) + 3));

                    if (markedblock.getBlocktype().getBlockParameter().get(i).getUnit() != null) {
                        glyphLayout.setText(font, markedblock.getBlocktype().getBlockParameter().get(i).getUnit());
                        font.draw(UIbatch, glyphLayout, UIVar.blockeinstellungen_x + 5 + UIVar.blockeinstellungen_w - 30, UIVar.blockeinstellungen_y + UIVar.blockeinstellungen_h - 20 - UIVar.abstandText - glyphLayout.height - (i * (glyphLayout.height + UIVar.abstandzwischenparametern + UIVar.abstandText + textFielder.get(i).getHeight()) + 3) - textFielder.get(i).getHeight() / 3f);

                    }
                } catch (Exception e) {
                    if (batch.isDrawing()) {
                        batch.end();
                    }
                }
            }
            UIVar.isBlockSettingsopen = true;
            UIbatch.end();

        } else {
            markedblock = ProjectManager.getActProjectVar().markedblock;
            wishaniposition=-UIVar.blockeinstellungen_w-UIVar.abstandvonRand;
            if (textFielder.size() > 0) {
                UIVar.isBlockSettingsopen = false;
                for (int i = 0; i < textFielder.size(); i++) {
                    textFielder.get(i).removeListener(textFielder.get(i).getListeners().get(0));
                    textFielder.get(i).remove();
                }
                textFielder.clear();
            }
        }
        runselection.draw();


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
        proset = new ProjectSettingsUI();

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
                                if (!(new File(Data.path.get(i)).exists())) { //TODO new vermeiden
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


                        if (SharedVar.amiclinet) {
                            de.ft.interitus.UI.MenuBar.fileMenu.setVisible(false);
                        }

                        ///////////////////////////
                    }
                }, 0, 500);
            }
        };


        UIthread.start();


    }

    public static void initnachassetsloading() {
        /////////////////Button Bar zusammensetzung//////////////////////
        button_debugstart = new Button();
        button_debugstart.setImage(AssetLoader.img_debugstart);
        button_debugstart.setImage_mouseover(AssetLoader.img_debugstart_mouseover);
        button_debugstart.setImage_pressed(AssetLoader.img_debugstart_pressed);
        button_debugstart.setW(20);

        button_projectstructus = new Button();
        button_projectstructus.setImage(AssetLoader.img_projectstructur);
        button_projectstructus.setImage_mouseover(AssetLoader.img_projectstructur_mouseover);
        button_projectstructus.setImage_pressed(AssetLoader.img_projectstructur_pressed);
        button_projectstructus.setW(20);

        button_start = new Button();
        button_start.setImage(AssetLoader.img_startbutton);
        button_start.setImage_mouseover(AssetLoader.img_startbutton_mouseover);
        button_start.setImage_pressed(AssetLoader.img_startbutton_pressed);
        button_start.setW(20);

        button_stop = new Button();
        button_stop.setImage(AssetLoader.img_stopbutton);
        button_stop.setImage_mouseover(AssetLoader.img_stopbutton_mouseover);
        button_stop.setImage_pressed(AssetLoader.img_stopbutton_pressed);
        button_stop.setW(20);

        button_editor = new Button();
        button_editor.setImage(AssetLoader.img_stopbutton);
        button_editor.setImage_mouseover(AssetLoader.img_stopbutton_mouseover);
        button_editor.setImage_pressed(AssetLoader.img_stopbutton_pressed);
        button_editor.setIsworking(false);
        button_editor.setWorking_animation(new Animation(AssetLoader.waitforfinishbuild,60, 64,64,7));
        button_editor.getWorking_animation().startAnimation();
        button_editor.setW(20);


        runselection=new DropDownMenue(100,100,new Color(94f/255f,96f/255f,96f/255f,1),Settings.theme.ClearColor());
        runselection.addelement(new DropDownElement(AssetLoader.WaitBlock_smallimage,"ArduinoLauncher"));
        runselection.addelement(new DropDownElement(AssetLoader.WaitBlock_smallimage,"Element_1"));
        runselection.addelement(new DropDownElement(AssetLoader.WaitBlock_smallimage,"Element_2"));
        runselection.addelement(new DropDownElement(AssetLoader.WaitBlock_smallimage,"Element_3"));
        runselection.addelement(new DropDownElement(AssetLoader.WaitBlock_smallimage,"Element_4"));




        buttonbar = new UIElementBar(0, 0,  20);
        buttonbar.addButton(button_projectstructus);
        buttonbar.addButton(button_editor);
        buttonbar.addButton(button_stop);
        buttonbar.addButton(button_debugstart);
        buttonbar.addButton(button_start);
        buttonbar.addButton(runselection);



        tabbar = new TabBar();



    }

    public static void update() {

        if (Var.openprojects.size() != tabbar.getTabbs().size()) { //TODO nicht die ganze Bar neu erstellen
            tabbar.setTabs();

            for (int i = 0; i < Var.openprojects.size(); i++) {
                Tab temptab = new Tab();
                temptab.getTabButton().setImage(AssetLoader.img_Tab);
                temptab.getTabButton().setW(300);
                temptab.getTabButton().widthoverText = true;
                temptab.getTabButton().setText(Var.openprojects.get(i).filename);
                temptab.setIndex(i);
                tabbar.addTab(temptab);
            }

        }



        if (tabbar.getSelectedTab() != null) {
            if (tabbar.getSelectedTab().getIndex() != -1) {
                if (Var.openprojectindex != tabbar.getSelectedTab().getIndex()) {
                    ProjectManager.change(tabbar.getSelectedTab().getIndex());
                }
            }
        }
        tabbar.setBounds(UIVar.abstandvonRand, UIVar.programmflaeche_h + UIVar.programmflaeche_y, 300, 20);
        tabbar.draw();

        if(UIVar.uilocked!=isuilock) {
            isuilock = UIVar.uilocked;

            if(UIVar.uilocked) {
                if(InputManager.contains(stage)) {
                    InputManager.remove(stage);
                }
            }else{
                if(!InputManager.contains(stage)) {
                    InputManager.addProcessor(stage);
                }
            }

        }





        if(!UIVar.uilocked) {
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        }

        //root.setPosition(0,0);


        root.setPosition(UIcam.position.x - ((float) Gdx.graphics.getWidth()) / 2, UIcam.position.y - ((float) Gdx.graphics.getHeight()) / 2);
        stage.draw();

        recent.setSubMenu(createSubMenu(Data.filename.size(), GetStringArray(Data.filename)));

        buttonbar.setX(Gdx.graphics.getWidth() - 10);
        buttonbar.setY(UIVar.programmflaeche_y + UIVar.programmflaeche_h + UIVar.abstandvonRand);
        buttonbar.draw(UIbatch);


        if (button_projectstructus.isjustPressednormal()) {

            proset.show();

        }


        if(UI.button_start.isjustPressednormal()){
          //  UI.button_start.setDisable(true);

            Thread compile_thread = new Thread()  {
                @Override
                public void run() {

                    ProjectManager.getActProjectVar().projectType.getCompiler().compileandrun();
                }
            };

            compile_thread.start();

        }

        if(UI.button_editor.isjustPressednormal()) {


            Editor.open();
        }


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
