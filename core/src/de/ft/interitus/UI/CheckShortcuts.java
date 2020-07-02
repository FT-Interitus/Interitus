package de.ft.interitus.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import de.ft.interitus.UI.shortcut.ShortCut;
import de.ft.interitus.UI.shortcut.ShortCutChecker;
import de.ft.interitus.UI.shortcut.shortcuts.BlockShortcuts;
import de.ft.interitus.UI.shortcut.shortcuts.GlobalShortcuts;
import de.ft.interitus.plugin.PluginGateway;

import java.util.ArrayList;

public class CheckShortcuts {
    public static boolean blockshortcuts = false;


    public static ArrayList<ShortCut> shortCuts = new ArrayList<>();
    public static ArrayList<ShortCutChecker> shortCutsChecker = new ArrayList<>();

    public static void loadArrayList() {

        //Add global Shortcuts
        //GlobalShortcuts.generateshortcuts();
        shortCuts.addAll(GlobalShortcuts.retunrarray());
        shortCutsChecker.add(new GlobalShortcuts());
        //Add Block shortcuts
        shortCuts.addAll(BlockShortcuts.retunrarray());
        shortCutsChecker.add(new BlockShortcuts());

        // ...

        //plugins
        shortCuts.addAll(PluginGateway.pluginshortCuts);
        shortCutsChecker.addAll(PluginGateway.pluginshortCutsChecker);

    }

    public static void check() {


        for (int i = 0; i < shortCutsChecker.size(); i++) {
            shortCutsChecker.get(i).check();
        }

        if (!blockshortcuts) {

            //TODO Changeable in the Settings   <- Deswegen habe ich erstmal nur Datei gemacht
            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {


            }


        }


    }
}
