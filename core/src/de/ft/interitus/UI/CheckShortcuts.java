package de.ft.interitus.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.shortcut.SpecialKeys;
import de.ft.interitus.UI.newproject.NewProjectWindow;
import de.ft.interitus.UI.shortcut.ShortCut;
import de.ft.interitus.UI.shortcut.ShortCutChecker;
import de.ft.interitus.UI.shortcut.SpecialKeys;
import de.ft.interitus.UI.shortcut.shortcuts.GlobalShortcuts;
import de.ft.interitus.Var;
import de.ft.interitus.data.user.DataSaver;
import de.ft.interitus.data.user.LoadSave;
import de.ft.interitus.data.user.changes.DataManager;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class CheckShortcuts {
    public static boolean blockshortcuts = false;


    public static boolean einmalausführen=true;

    public static ArrayList<ShortCut>shortCuts=new ArrayList<>();
    public static ArrayList<ShortCutChecker>shortCutsChecker=new ArrayList<>();

    public static void loadArryList(){
        shortCuts.clear();
        //Add global Shortcuts
        shortCuts.addAll(GlobalShortcuts.retunrarray());
        shortCutsChecker.add(new GlobalShortcuts());
        //Add Block shortcuts


       // ...

        //plugins Adden sich selbst zu diesen Array Listen kein Laden erforderlich

    }

    public static void check() {
        if(einmalausführen)
            loadArryList();


       for(int i=0;i<shortCutsChecker.size();i++) {
           shortCutsChecker.get(i).check();
       }

        if (!blockshortcuts) {

            //TODO Changeable in the Settings   <- Deswegen habe ich erstmal nur Datei gemacht
            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {






            }


        }


    }
}
