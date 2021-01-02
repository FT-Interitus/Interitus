/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIContents.ZoomUi;

import de.ft.interitus.Program;
import de.ft.interitus.UI.UIElements.Grid;
import de.ft.interitus.UI.UIElements.UIElements.Button;
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
    Button zurücksetzen=new Button();
    public ZoomUI(){
        plus.setImage(AssetLoader.PlusButton);
        plus.setBounds(0,0,16,16);
        plus.setWidthoverText(true);
        plus.setHeightoverText(true);

        minus.setImage(AssetLoader.MinusButton);
        minus.setBounds(0,0,16,16);
        minus.setWidthoverText(true);
        minus.setHeightoverText(true);

        zurücksetzen.setImage(AssetLoader.ResetButton);
        zurücksetzen.setBounds(0,0,80,30);
        zurücksetzen.setWidthoverText(true);
        zurücksetzen.setHeightoverText(true);


        grid.addElement(textLabel);
        grid.addElement(plus);
        grid.addElement(minus);
        grid.addElement(zurücksetzen);

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
        grid.setPosition(500, UIVar.programmflaeche_y+UIVar.programmflaeche_h);
        Program.logger.config(String.valueOf(plus.getW()));
        grid.draw();

    }



}
