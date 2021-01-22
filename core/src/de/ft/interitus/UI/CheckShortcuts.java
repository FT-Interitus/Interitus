/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;

import de.ft.interitus.Program;
import de.ft.interitus.UI.shortcut.ShortCut;
import de.ft.interitus.UI.shortcut.ShortCutChecker;
import de.ft.interitus.UI.shortcut.shortcuts.BlockShortcuts;
import de.ft.interitus.UI.shortcut.shortcuts.GlobalShortcuts;
import de.ft.interitus.utils.ArrayList;

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
        //  ProgramRegistry.addShortCuts();

        Program.logger.config("Shortcuts loaded");
    }

    public static void check() {


        for (int i = 0; i < shortCutsChecker.size(); i++) {
            shortCutsChecker.get(i).check();
        }


    }
}
