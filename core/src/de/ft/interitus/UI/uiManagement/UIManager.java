/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.uiManagement;

import de.ft.interitus.utils.ArrayList;

public class UIManager {
    public static UIRegistry uiRegistry =new UIRegistry();
    public static ArrayList<UI> UIs=new ArrayList<>();

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
