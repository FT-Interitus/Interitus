/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.uiManagement;

import com.badlogic.gdx.graphics.Color;
import de.ft.interitus.utils.ArrayList;

public class UIManager {
    public static UIRegistry uiRegistry =new UIRegistry();
    public static ArrayList<UI> UIs=new ArrayList<>();
    public static Color primaryColor = new Color(0f/255f, 150f/255f, 136f/255f,1f);
    public static Color disabledColor = new Color(221f / 255f, 221f / 255f, 221f / 255f, 1f);

    public static void draw(){
        for(UI ui : UIs){
            if(ui.enabled)ui.draw();
        }
    }
    public static void addUI(UI ui){
        UIs.add(ui);
    }
    public static void removeUI(UI ui){
        UIs.remove(ui);
    }
}
