/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIContents.ZoomUi;

import de.ft.interitus.Program;
import de.ft.interitus.ProgramingSpace;
import de.ft.interitus.UI.UIElements.Grid;
import de.ft.interitus.UI.UIElements.PlaceHolder;
import de.ft.interitus.UI.UIElements.UIElements.Button;
import de.ft.interitus.UI.UIElements.UIElements.UIElement;
import de.ft.interitus.UI.UIElements.UIElements.labels.TextLabel;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.Viewport;
import de.ft.interitus.UI.uiManagement.UI;
import de.ft.interitus.events.UI.UIZoomEvent;
import de.ft.interitus.loading.AssetLoader;

public class ZoomUI extends UI {
    Grid grid=new Grid(0,0,0,0);
    TextLabel textLabel=new TextLabel("TextLabel");
    Button plus=new Button();
    Button minus=new Button();
    Button reset =new Button();
    public ZoomUI(){
        plus.setImage(AssetLoader.PlusButton);
        plus.setBounds(0,0,16,16);
        plus.setWidthoverText(true);
        plus.setHeightoverText(true);

        minus.setImage(AssetLoader.MinusButton);
        minus.setBounds(0,0,16,16);
        minus.setWidthoverText(true);
        minus.setHeightoverText(true);

        reset.setImage(AssetLoader.ResetButton);
        reset.setBounds(0,0,80,30);
        reset.setWidthoverText(true);
        reset.setHeightoverText(true);


        grid.addElement(textLabel);
        grid.addElement(new PlaceHolder().setW(10));
        grid.addElement(plus);
        grid.addElement(new PlaceHolder().setW(10));
        grid.addElement(minus);
        grid.addElement(new PlaceHolder().setW(10));
        grid.addElement(reset);

        Viewport.zoomEvent.addListener(new UIZoomEvent() {
             @Override
           public void zoomStart() {
            ZoomUI.super.enabled = true;
            }
        });

    }

    @Override
    protected void draw() {

        grid.setVerticalVermitteln(true);
        grid.setPosition(500, UIVar.programmflaeche_y+UIVar.programmflaeche_h+grid.getH());
        grid.draw();
        textLabel.setText(Viewport.getZoomPercentage()+"%");
        if(plus.isjustPressednormal())  Viewport.increaseZoom(ProgramingSpace.cam);
        if(minus.isjustPressednormal()) Viewport.decreaseZoom(ProgramingSpace.cam);
        if(reset.isjustPressednormal()) Viewport.resetZoom(ProgramingSpace.cam);

    }



}
