/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.*;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Selectable;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.Settings;
import de.ft.interitus.UI.ManualConfig.ManualConfigUI;
import de.ft.interitus.UI.Notification.Notification;
import de.ft.interitus.UI.Notification.NotificationManager;
import de.ft.interitus.UI.UIElements.UIElementBar;
import de.ft.interitus.UI.UIElements.UIElements.*;
import de.ft.interitus.UI.UIElements.UIElements.TabBar.TabBar;
import de.ft.interitus.UI.UIElements.UIElements.quickinfo.QuickInfo;
import de.ft.interitus.UI.UIElements.dropdownmenue.DropDownMenue;
import de.ft.interitus.UI.editor.Editor;
import de.ft.interitus.UI.projectsettings.ProjectSettingsUI;
import de.ft.interitus.UI.settings.SettingsUI;
import de.ft.interitus.UI.setup.SetupWindow;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.UI.uiManagement.UIManager;
import de.ft.interitus.Var;
import de.ft.interitus.WindowManager;
import de.ft.interitus.datamanager.programmdata.Data;
import de.ft.interitus.datamanager.programmdata.experience.ExperienceManager;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.global.GlobalCompilingStartEvent;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.network.bettertogether.SharedVar;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.ArrayList;
import de.ft.interitus.utils.ShapeRenderer;

import java.io.File;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import static de.ft.interitus.UI.MenuBar.createProjectsSubMenu;

public class UI {
    public static final ManualConfigUI MANUALCONFIG = new ManualConfigUI();
    public final static GlyphLayout glyphLayout = new GlyphLayout();
    public final static BitmapFont font = new BitmapFont();
    static final Table root = new Table();
    static final ArrayList<Widget> textFielder = new ArrayList<>();
    public static QuickInfo blockbarquickinfo;
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
    public static Button button_addrunconfig;
    public static TabBar tabbar;
    public static VisLabel blocknamelabel;
    public static VisLabel blocksettingslabel;
    public static MenuBar menuBar;
    public static DropDownMenue runselection;
    public static MenuItem recent;
    public static MenuItem revert;
    public static MenuItem redo;
    public static MenuItem copy;
    public static MenuItem paste;
    static boolean isuilock = false;
    static VisTextField settingstextfield;
    private static boolean issettingsuiopend = false;
    private static boolean issetupuiopend = false;
    private static Block markedblock;
    private static int wishaniposition = -170 - UIVar.abstandvonRand;
    private static Thread compile_thread;
    private static final TextInput testtextInput = (TextInput) new TextInput("Das ist ein TestTextInput").setPosition(500, 500);
    private static final TextInput testtextInput2 = (TextInput) new TextInput("Das ist ein TestTextInput2").setPosition(500, 400);

    private static final RadioButton radioButton = (RadioButton) new RadioButton().setPosition(100, 100);
    private static final RadioButton radioButton1 = (RadioButton) new RadioButton().setPosition(100, 140);
    private static final RadioButton radioButton2 = (RadioButton) new RadioButton().setPosition(100, 180);
    private static final CheckBox checkBox = (CheckBox) new CheckBox(true, "Hallo").setPosition(100, 200);
    private static final RadioButtonGroup group = new RadioButtonGroup();

    static {
        group.add(radioButton);
        group.add(radioButton1);
        group.add(radioButton2);
    }

    //private static final Animation animation = new Animation();

    public static void UpdateDragUI(ShapeRenderer renderer, boolean flaeche, SpriteBatch batch, float delta) {

        if (!UIVar.uilocked) {

            BlockTappedBar.userresize();

        }
        // Var.w=WindowAPI.getWidth();
        //Var.h=WindowAPI.getHeight();
        renderer.begin(ShapeRenderer.ShapeType.Filled);


        renderer.setColor(Settings.theme.ProgramSpaceColor());


        if (flaeche == true) {/////////////   \/  \/  \/  \/  die programmierfläche wird gedrawd
            UIVar.programmflaeche_h = WindowAPI.getHeight() - (UIVar.untenhohe + UIVar.abstandvonRand) - UIVar.abstandvonRand - (int) menuBar.getTable().getHeight() - UIVar.buttonbarzeile_h - UIVar.abstandvonRand;
            UIVar.programmflaeche_y = UIVar.untenhohe + UIVar.abstandvonRand;
            renderer.roundendrect(UIVar.abstandvonRand, UIVar.programmflaeche_y, WindowAPI.getWidth() - UIVar.abstandvonRand * 2, UIVar.programmflaeche_h, UIVar.radius);
        } else {

            renderer.setColor(Settings.theme.BlocksColor());

            UIVar.BlockBarX = UIVar.abstandvonRand;
            UIVar.BlockBarY = UIVar.abstandvonRand;
            UIVar.BlockBarW = WindowAPI.getWidth() - UIVar.abstandvonRand * 2 - UIVar.unteneinteilung;
            UIVar.BlockBarH = UIVar.untenhohe - UIVar.abstandvonRand;//  \/  \/ blockbar wird gedrawed
            renderer.roundendrect(UIVar.abstandvonRand, UIVar.abstandvonRand, UIVar.BlockBarW, UIVar.BlockBarH, UIVar.radius);

            renderer.setColor(Settings.theme.DeviceConnectionColor());

            renderer.roundendrect(WindowAPI.getWidth() - UIVar.unteneinteilung, UIVar.abstandvonRand, UIVar.unteneinteilung - UIVar.abstandvonRand, UIVar.untenhohe - UIVar.abstandvonRand, UIVar.radius);
        }
        renderer.end();

        // markedblock != ProjectManager.getActProjectVar().marked_block
        if ((markedblock != null && ProjectManager.getActProjectVar().marked_blocks.size() == 0) || (ProjectManager.getActProjectVar().marked_blocks.size() > 0 && markedblock != ProjectManager.getActProjectVar().marked_blocks.get(0))) {
            UIVar.isBlockSettingsopen = false;
            for (int i = 0; i < textFielder.size(); i++) {
                try {
                    textFielder.get(i).removeListener(textFielder.get(i).getListeners().get(0));
                } catch (IndexOutOfBoundsException ignore) {

                }
                textFielder.get(i).remove();
            }
            textFielder.clear();
        }

/**
 * If you move the Cursor in a TextField the program should not move itself
 */
        var lock = false;
        for (Widget visTextField : textFielder) {
            if (visTextField.hasKeyboardFocus()) {
                lock = true;
            }
        }
        if (settingstextfield != null) {
            if (settingstextfield.hasKeyboardFocus()) {
                lock = true;
            }

        }

        UIVar.moveprogrammlock = lock;


        if (ProjectManager.getActProjectVar().marked_blocks.size() == 1 && markedblock != null && markedblock.getBlockType().getBlockParameter() != null && ((markedblock == null && ProjectManager.getActProjectVar().marked_blocks.size() == 0) || (ProjectManager.getActProjectVar().marked_blocks.size() > 0 && markedblock == ProjectManager.getActProjectVar().marked_blocks.get(0)))) {


            UIVar.blockeinstellungen_w = 170;
            UIVar.blockeinstellungen_h = UIVar.programmflaeche_h - UIVar.abstandvonRand * 2;
            UIVar.blockeinstellungen_x = WindowAPI.getWidth() - UIVar.abstandvonRand * 2 - UIVar.blockeinstellungen_w - wishaniposition;
            UIVar.blockeinstellungen_y = UIVar.programmflaeche_y + UIVar.abstandvonRand;

            if (wishaniposition < 0) {
                wishaniposition += ((0 - wishaniposition) * delta * 6);
            }


            renderer.begin(ShapeRenderer.ShapeType.Filled);
            renderer.setColor(81f / 255f, 97f / 255f, 114f / 255f, 1f);
            renderer.rect(UIVar.blockeinstellungen_x, UIVar.blockeinstellungen_y, UIVar.blockeinstellungen_w, UIVar.blockeinstellungen_h);
            renderer.end();
            UIbatch.begin();


            if (!UIVar.isBlockSettingsopen) {
                blocknamelabel = new VisLabel(markedblock.getBlockType().getBlockModes().get(markedblock.getBlockType().actBlockModiIndex).getname());
                UI.stage.addActor(blocknamelabel);
            }

            if (!UIVar.isBlockSettingsopen && markedblock.getBlockType().blockModis.get(markedblock.getBlockType().actBlockModiIndex).getblocksettings() != null) {
                blocksettingslabel = new VisLabel("Einstellungen");
                UI.stage.addActor(blocksettingslabel);

            }
            if (blocksettingslabel != null) {
                blocksettingslabel.setPosition(UIVar.blockeinstellungen_x + 5, UIVar.blockeinstellungen_y + UIVar.blockeinstellungen_h - 57);

            }

            if (blocknamelabel != null) {
                glyphLayout.setText(AssetLoader.defaultfont, blocknamelabel.getText());
                blocknamelabel.setPosition(UIVar.blockeinstellungen_x + UIVar.blockeinstellungen_w / 2 - blocknamelabel.getWidth() / 2, UIVar.blockeinstellungen_y + UIVar.blockeinstellungen_h - 25);
            }

            int nachuntenrutscher = 10;
            try {
                if (markedblock.getBlockType().blockModis.get(markedblock.getBlockType().actBlockModiIndex).getblocksettings() != null) {

                    if (!UIVar.isBlockSettingsopen) {
                        settingstextfield = new VisTextField(markedblock.getBlockType().blockModis.get(markedblock.getBlockType().actBlockModiIndex).getblocksettings().getSettings());

                        settingstextfield.setWidth(UIVar.blockeinstellungen_w - 40);

                        settingstextfield.addListener(new ChangeListener() {
                            @Override
                            public void changed(ChangeEvent event, Actor actor) {

                                markedblock.getBlockType().blockModis.get(markedblock.getBlockType().actBlockModiIndex).getblocksettings().setSettings(settingstextfield.getText());

                            }
                        });

                        UI.stage.addActor(settingstextfield);
                    }
                    nachuntenrutscher = 75 + 10;
                    if (settingstextfield != null) {
                        settingstextfield.setPosition(UIVar.blockeinstellungen_x + 5, UIVar.blockeinstellungen_y + UIVar.blockeinstellungen_h - 59 - settingstextfield.getHeight());
                    }

                }


            } catch (Exception e) {
                e.printStackTrace();
            }


            for (int i = 0; i < markedblock.getBlockType().getBlockParameter().size(); i++) {
                try {
                    if (!UIVar.isBlockSettingsopen) {

                        if (markedblock.getBlockType().getBlockParameter().get(i).getParameterType().isOutput()) {


                            continue;
                        }


                        if (markedblock.getBlockType().getBlockParameter().get(i).getParameterType().isDropdown() && !(markedblock.getBlockType().getBlockParameter().get(i).getDataWires().size() > 0)) {
                            VisSelectBox<Selectable> selectBox = new VisSelectBox<>();
                            Selectable[] selectables = new Selectable[markedblock.getBlockType().getBlockParameter().get(i).getParameterType().getSelectables().size()];
                            markedblock.getBlockType().getBlockParameter().get(i).getParameterType().getSelectables().toArray(selectables);
                            selectBox.setItems(selectables);

                            if (Arrays.asList(markedblock.getBlockType().getBlockParameter().get(i).getParameterType().getSelectables()).contains(markedblock.getBlockType().getBlockParameter().get(i).getParameter())) {

                                selectBox.setSelected((markedblock.getBlockType().getBlockParameter().get(i).getParameterType().getSelected()));

                            } else {
                                selectBox.setSelected(selectBox.getItems().get(0));
                            }


                            selectBox.addListener(new ChangeListener() {
                                @Override
                                public void changed(ChangeEvent event, Actor actor) {
                                    ProjectManager.getActProjectVar().marked_blocks.get(0).getBlockType().getBlockParameter().get(textFielder.indexOf(actor)).getParameterType().setSelected(((VisSelectBox<Selectable>) actor).getSelected());
                                    ProjectManager.getActProjectVar().marked_blocks.get(0).getBlockType().getBlockParameter().get(textFielder.indexOf(actor)).setParameter(((VisSelectBox<Selectable>) actor).getSelected().toString());

                                }
                            });

                            textFielder.add(selectBox);

                        } else {

                            if (markedblock.getBlockType().getBlockParameter().get(i).getDataWires().size() > 0) {
                                textFielder.add(new VisTextField("Leitung verbunden"));
                                ((VisTextField) textFielder.getLastObject()).setDisabled(true);

                            } else {
                                textFielder.add(new VisTextField(markedblock.getBlockType().getBlockParameter().get(i).getParameter().toString()));

                            }
                            textFielder.get(textFielder.size() - 1).addListener(new ChangeListener() {
                                @Override
                                public void changed(ChangeEvent event, Actor actor) {
                                    ProjectManager.getActProjectVar().marked_blocks.get(0).getBlockType().getBlockParameter().get(textFielder.indexOf(actor)).setParameter(((VisTextField) actor).getText());
                                }
                            });

                        }

                        UI.stage.addActor(textFielder.get(i));
                    }

                    textFielder.get(i).setWidth(UIVar.blockeinstellungen_w - 40);
                    textFielder.get(i).setPosition(UIVar.blockeinstellungen_x + 5, UIVar.blockeinstellungen_y + UIVar.blockeinstellungen_h - 20 - UIVar.abstandText - glyphLayout.height - (i * (glyphLayout.height + UIVar.abstandzwischenparametern + UIVar.abstandText + textFielder.get(i).getHeight()) + 3) - textFielder.get(i).getHeight() - nachuntenrutscher);
                    glyphLayout.setText(font, markedblock.getBlockType().getBlockParameter().get(i).getParameterName());

                    font.draw(UIbatch, glyphLayout, UIVar.blockeinstellungen_x + 5, UIVar.blockeinstellungen_y + UIVar.blockeinstellungen_h - 20 - (i * (glyphLayout.height + textFielder.get(i).getHeight() + UIVar.abstandzwischenparametern + UIVar.abstandText) + 3) - nachuntenrutscher);

                    if (markedblock.getBlockType().getBlockParameter().get(i).getUnit() != null) {
                        glyphLayout.setText(font, markedblock.getBlockType().getBlockParameter().get(i).getUnit());
                        font.draw(UIbatch, glyphLayout, UIVar.blockeinstellungen_x + 5 + UIVar.blockeinstellungen_w - 30, (UIVar.blockeinstellungen_y + UIVar.blockeinstellungen_h - 20 - UIVar.abstandText - glyphLayout.height - (i * (glyphLayout.height + UIVar.abstandzwischenparametern + UIVar.abstandText + textFielder.get(i).getHeight()) + 3) - textFielder.get(i).getHeight() / 3f) - nachuntenrutscher);

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
            if (ProjectManager.getActProjectVar().marked_blocks.size() == 0) {
                markedblock = null;
            } else {
                markedblock = ProjectManager.getActProjectVar().marked_blocks.get(0);
            }
            wishaniposition = -UIVar.blockeinstellungen_w - UIVar.abstandvonRand;

            if (settingstextfield != null) {
                try {
                    settingstextfield.removeListener(settingstextfield.getListeners().get(0));
                    settingstextfield.remove();
                } catch (Exception ignored) {


                }
            }

            if (blocknamelabel != null) {
                blocknamelabel.remove();
            }

            if (blocksettingslabel != null) {
                blocksettingslabel.remove();
            }

            if (textFielder.size() > 0) {
                UIVar.isBlockSettingsopen = false;
                for (int i = 0; i < textFielder.size(); i++) {
                    try {
                        textFielder.get(i).removeListener(textFielder.get(i).getListeners().get(0));
                    } catch (Exception e) {

                    }
                    textFielder.get(i).remove();
                }
                textFielder.clear();
            }
        }
        runselection.draw();

    }

    public static void init() {


        UI.UIbatch = new SpriteBatch();
        UI.UIcam = new OrthographicCamera(WindowAPI.getWidth(), WindowAPI.getHeight());
        UI.UIviewport = new ScreenViewport(UI.UIcam);

        stage = new Stage(UIviewport, UIbatch);


        root.setFillParent(true);
        stage.addActor(root);

        WindowManager.inputManager.addProcessor(stage);
        WindowManager.inputManager.updateMultiplexer();

        set = new SettingsUI();
        proset = new ProjectSettingsUI();

        menuBar = new MenuBar();

        root.add(menuBar.getTable()).expandX().fillX().row();
        root.add().expand().fill().row();


        de.ft.interitus.UI.MenuBar.createMenus();


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

    public static void InitAssets() {
        UIManager.uiRegistry.init();//Initializes the UIs of the UIManagement system
        /////////////////
        /////////Button bar composition//////////////////////
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
        button_editor.setImage(AssetLoader.img_editor);
        button_editor.setImage_mouseover(AssetLoader.img_editor_mouseover);
        button_editor.setImage_pressed(AssetLoader.img_editor_pressed);
        button_editor.setIsworking(false);
        button_editor.setWorking_animation(new Animation(AssetLoader.waitforfinishbuild, 60, 64, 64, 7));
        button_editor.getWorking_animation().startAnimation();
        button_editor.setW(20);

        button_addrunconfig = new Button();
        button_addrunconfig.setImage(AssetLoader.img_addrunconfig);
        button_addrunconfig.setImage_mouseover(AssetLoader.img_addrunconfig_mouseover);
        button_addrunconfig.setImage_pressed(AssetLoader.img_addrunconfig_pressed);
        button_addrunconfig.setIsworking(false);
        button_addrunconfig.setWorking_animation(new Animation(AssetLoader.waitforfinishbuild, 60, 64, 64, 7));
        button_addrunconfig.getWorking_animation().startAnimation();
        button_addrunconfig.setW(20);


        runselection = new DropDownMenue(100, 100, new Color(94f / 255f, 96f / 255f, 96f / 255f, 1), "");


        buttonbar = new UIElementBar(0, 0, 20);
        buttonbar.addButton(button_projectstructus);
        buttonbar.addButton(button_editor);
        buttonbar.addButton(button_stop);
        buttonbar.addButton(button_debugstart);
        buttonbar.addButton(button_start);
        buttonbar.addButton(runselection);
        buttonbar.addButton(button_addrunconfig);


        tabbar = new TabBar();


        blockbarquickinfo = new QuickInfo(0, 0, "").setAttachedToMouse(true).setSelfCheck(true);
        BlockTappedBar.tb.setListener(new de.ft.interitus.UI.ChangeListener() {
            @Override
            public void change() {
                blockbarquickinfo.disableall();
            }
        });


        Button testbutton1 = new Button();
        testbutton1.setW(30);
        testbutton1.setH(100);
        testbutton1.setImage(AssetLoader.arduinonanoimage);
        Button testbutton2 = new Button();
        testbutton2.setW(70);
        testbutton2.setH(50);
        Button testbutton3 = new Button();
        testbutton3.setW(50);
        testbutton3.setH(50);


    }

    public static void update(float delta) {


        // Program.logger.config("Pos:" + (WindowAPI.getHeight()-menuBar.getTable().getHeight()-20-(UIVar.abstandvonRand*2)));

        // Program.logger.config((UIVar.programmflaeche_h + UIVar.programmflaeche_y));
        tabbar.setBounds(UIVar.abstandvonRand, (int) (WindowAPI.getHeight() - menuBar.getTable().getHeight() - 20 - (UIVar.abstandvonRand * 2)), 300, 20);
        tabbar.draw();

        if (UIVar.uilocked != isuilock) {
            isuilock = UIVar.uilocked;

            if (UIVar.uilocked) {
                if (WindowManager.inputManager.contains(stage)) {
                    WindowManager.inputManager.remove(stage);
                }
            } else {
                if (!WindowManager.inputManager.contains(stage)) {
                    WindowManager.inputManager.addProcessor(stage);
                }
            }

        }


        if (!UIVar.uilocked) {
            stage.act(Math.min(WindowAPI.getDeltaTime(), 1 / 60f));
        }

        root.setPosition(UIcam.position.x - ((float) WindowAPI.getWidth()) / 2, UIcam.position.y - ((float) WindowAPI.getHeight()) / 2);
        stage.draw();

        recent.setSubMenu(createProjectsSubMenu(Data.filename.size(), GetStringArray(Data.filename)));

        //root.setPosition(0,0);
        if (Var.inProgram) {
            buttonbar.setX(WindowAPI.getWidth() - 10);
            buttonbar.setY((int) (WindowAPI.getHeight() - menuBar.getTable().getHeight() - 20 - (UIVar.abstandvonRand)));

            buttonbar.draw();


            if (button_projectstructus.isjustPressed()) {

                proset.show();

            }


            if (UI.button_start.isjustPressed() && runselection.getSelectedElement() != null) {
                //  UI.button_start.setDisable(true);


                compile_thread = new Thread() {
                    @Override
                    public void run() {

                        EventVar.globalEventManager.compilingstarted(new GlobalCompilingStartEvent(this), ProjectManager.getActProjectVar().projectType.getCompiler());

                        if (!ProjectManager.getActProjectVar().projectType.getCompiler().compileandrun()) {
                            NotificationManager.sendNotification(new Notification(AssetLoader.information, "Ein Fehler ist aufgetreten", "Während des Compilierens ist\nein Fehler aufgetreten!"));
                        }
                    }
                };

                compile_thread.start();

            }


            if (UI.button_editor.isjustPressed()) {

                // UI.button_editor.setIsworking(true); // TODO: 02.08.20 Build Project
                Editor.open();
            }

            if (UI.button_stop.isjustPressed()) {
                ProjectManager.getActProjectVar().projectType.getCompiler().Interrupt();
            }

            if (UI.button_addrunconfig.isjustPressed()) {
                MANUALCONFIG.show();
            }


            blockbarquickinfo.update();

        }

        UIManager.draw();

        testtextInput.draw();
        testtextInput2.draw();

        radioButton.draw();
        radioButton1.draw();
        radioButton2.draw();


        checkBox.draw();
    }


    private static String[] GetStringArray(ArrayList<String> arr) {

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


    public static void onSwitchToWelcome() {
        if (settingstextfield != null) {
            try {
                settingstextfield.removeListener(settingstextfield.getListeners().get(0));
                settingstextfield.remove();
            } catch (Exception ignored) {


            }
        }

        if (blocknamelabel != null) {
            blocknamelabel.remove();
        }

        if (blocksettingslabel != null) {
            blocksettingslabel.remove();
        }

        if (textFielder.size() > 0) {
            UIVar.isBlockSettingsopen = false;
            for (int i = 0; i < textFielder.size(); i++) {
                try {
                    textFielder.get(i).removeListener(textFielder.get(i).getListeners().get(0));
                } catch (Exception e) {

                }
                textFielder.get(i).remove();
            }
            textFielder.clear();
        }
    }


}
