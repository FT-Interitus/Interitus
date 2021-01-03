/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements;

import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.UIElements.UIElement;
import de.ft.interitus.utils.ArrayList;

public class Container extends UIElement {
    private ArrayList<UIElement>elements=new ArrayList<>();
    private boolean syncart=false;
    private int maxWidth=0;
    private int maxHeight=0;
    @Override
    public void draw() {
        int tempsync=0;
        for(UIElement e:elements) {
            if(e.getW() > maxWidth){
                maxWidth=e.getW();
                super.w=maxWidth;
            }
            if(e.getH() > maxHeight){
                maxHeight=e.getH();
                super.h=maxHeight;
            }
        }
            for(UIElement e:elements){
            if(!syncart){
                e.setH(maxHeight);
                e.setX(super.x+tempsync);
                e.setY(super.y);
                tempsync+=maxWidth;
            }else{
                e.setW(maxWidth);
                e.setY(super.y+tempsync);
                e.setX(super.x);
                tempsync+=maxHeight;
            }
            e.draw();
        }
    }
    public void addElement(UIElement element){
        if(element.getW() > maxWidth){
            maxWidth=element.getW();
            super.w=maxWidth;
        }
        if(element.getH() > maxHeight){
            maxHeight=element.getH();
            super.h=maxHeight;
        }
        elements.add(element);
    }
    public void HorizontalSync(){
        syncart=false;
    }
    public void VerticalSync(){
        syncart=true;
    }
}
