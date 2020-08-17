/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.tappedbar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import de.ft.interitus.Block.TapBarBlockItem;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.Programm;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.UIElements.quickinfo.QuickInfoContent;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.ProjectManager;


public class BlockTappedBar {
    public static TappedBar tb = new TappedBar(100, 100);
    public static boolean curserveränderungsblockade = false; //If a Block is over the Block Space
    static TapContent ActionBlocks = new TapContent(AssetLoader.img_mappe1);
    static TapContent Programm_Sequence = new TapContent(AssetLoader.img_mappe2);
    static TapContent Sensors = new TapContent(AssetLoader.img_mappe3);
    static TapContent Data_Operation = new TapContent(AssetLoader.img_mappe4);
    static TapContent Specials = new TapContent(AssetLoader.img_mappe5);
    static TapContent OwnBlocks = new TapContent(AssetLoader.img_mappe6);
    private static int curserstate = 0;
    private static boolean verticalrezising = false;
    private static boolean horizontalrezising = false;

    public static void init() {


        ActionBlocks.clear();
        Programm_Sequence.clear();
        Sensors.clear();
        Data_Operation.clear();
        Specials.clear();
        OwnBlocks.clear();

        UI.blockbarquickinfo.disableall();
        UI.blockbarquickinfo.getSelfCheckList().clear();
        for (int i = 0; i < ProjectManager.getActProjectVar().projectType.getProjectblocks().size(); i++) {
            try {
                if (ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i).getBlockCategoration() != null) {
                    switch (ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i).getBlockCategoration()) {
                        case ActionBlocks:
                            ActionBlocks.addItem(new TapBarBlockItem(ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i), ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i).getSmallImage()));
                            break;
                        case Programm_Sequence:
                            Programm_Sequence.addItem(new TapBarBlockItem(ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i), ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i).getSmallImage()));
                            break;
                        case Sensors:
                            Sensors.addItem(new TapBarBlockItem(ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i), ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i).getSmallImage()));
                            break;
                        case Data_Operation:
                            Data_Operation.addItem(new TapBarBlockItem(ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i), ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i).getSmallImage()));
                            break;
                        case Specials:
                            Specials.addItem(new TapBarBlockItem(ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i), ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i).getSmallImage()));
                            break;
                        case OwnBlocks:
                            Programm.logger.severe("Unallowed Block was registered from Plugin " + ProjectManager.getActProjectVar().projectType.getPluginRegister().getName());
                            break;

                    }
                    UI.blockbarquickinfo.addSelfCheckField(new QuickInfoContent(0,0,500,500,ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i).getName(),ProjectManager.getActProjectVar().projectType.getProjectblocks().get(i).getName()));
                }
            } catch (Exception e) {
                e.printStackTrace();
                DisplayErrors.customErrorstring = "Fehler beim Laden der Blöcke!";
                DisplayErrors.error = e;
            }
        }


        tb.setContent(ActionBlocks, Programm_Sequence, Sensors, Data_Operation, Specials, OwnBlocks);
    }


    public static void userresize() {
        if (!curserveränderungsblockade) {
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
                if (!ProjectManager.getActProjectVar().removeblock) {
                    Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
                }
                curserstate = 1;
            }
        }

        if (curserstate == 0 && Gdx.input.isButtonJustPressed(0)) {
            verticalrezising = true;
            curserveränderungsblockade = true;
        }
        if (!Gdx.input.isButtonPressed(0)) {
            verticalrezising = false;
            curserveränderungsblockade = false;
        }
        if (verticalrezising) {
            UIVar.unteneinteilung = Gdx.graphics.getWidth() - Gdx.input.getX() - UIVar.abstandvonRand / 2;
            if (Gdx.graphics.getWidth() - UIVar.unteneinteilung < BlockTappedBar.tb.getTaps().size() * BlockTappedBar.tb.getTaps().get(0).getTab_button().getW() + BlockTappedBar.tb.getButtonabstand() * BlockTappedBar.tb.getTaps().size() + 20) {
                UIVar.unteneinteilung = Gdx.graphics.getWidth() - (BlockTappedBar.tb.getTaps().size() * BlockTappedBar.tb.getTaps().get(0).getTab_button().getW() + BlockTappedBar.tb.getButtonabstand() * BlockTappedBar.tb.getTaps().size() + 20);
            }
            if (Gdx.graphics.getWidth() - UIVar.unteneinteilung > Gdx.graphics.getWidth() - UIVar.rechtseinraste) {
                UIVar.unteneinteilung = UIVar.rechtseinraste;

            }

        }


        if (curserstate == 2 && Gdx.input.isButtonJustPressed(0)) {
            horizontalrezising = true;
            curserveränderungsblockade = true;
        }
        if (!Gdx.input.isButtonPressed(0)) {
            horizontalrezising = false;
            curserveränderungsblockade = false;
        }
        if (horizontalrezising) {
            UIVar.untenhohe = Gdx.graphics.getHeight() - Gdx.input.getY() - UIVar.abstandvonRand / 2;
            if (UIVar.untenhohe < UIVar.untenkante) {
                UIVar.untenhohe = UIVar.untenkante;
            }
            if (UIVar.untenhohe > Gdx.graphics.getHeight() / 2) {
                UIVar.untenhohe = Gdx.graphics.getHeight() / 2;
            }
        }


    }

}
