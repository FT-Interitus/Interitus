/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIContents.ZoomUi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import de.ft.interitus.ProgramingSpace;
import de.ft.interitus.UI.UIElements.FormattingFrame;
import de.ft.interitus.UI.UIElements.Grid;
import de.ft.interitus.UI.UIElements.PlaceHolder;
import de.ft.interitus.UI.UIElements.UIElements.Button;
import de.ft.interitus.UI.UIElements.UIElements.TabBar.Tab;
import de.ft.interitus.UI.UIElements.UIElements.labels.TextLabel;
import de.ft.interitus.UI.UIElements.check.CheckMouse;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.Viewport;
import de.ft.interitus.UI.uiManagement.UI;
import de.ft.interitus.UI.uiManagement.UIManager;
import de.ft.interitus.Var;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.UI.UIZoomEvent;
import de.ft.interitus.events.global.GlobalEventAdapter;
import de.ft.interitus.events.global.GlobalTabClickEvent;
import de.ft.interitus.loading.AssetLoader;

public class ZoomUI extends UI {
    private final Grid grid = new Grid(0, 0, 0, 0);
    private final TextLabel textLabel = new TextLabel("", 50);
    private final Button plus = new Button();
    private final Button minus = new Button();
    private final Button reset = new Button();

    private final FormattingFrame formattingFrame = new FormattingFrame(grid);

    public ZoomUI() {
        plus.setImage(AssetLoader.PlusButton);
        plus.setBounds(0, 0, 16, 16);
        plus.setWidthoverText(true);
        plus.setHeightoverText(true);

        minus.setImage(AssetLoader.MinusButton);
        minus.setBounds(0, 0, 16, 16);
        minus.setWidthoverText(true);
        minus.setHeightoverText(true);

        reset.setImage(AssetLoader.ResetButton);
        reset.setBounds(0, 0, 80, 30);
        reset.setWidthoverText(true);
        reset.setHeightoverText(true);


        grid.addElement(textLabel);
        grid.addElement(new PlaceHolder().setW(10));
        grid.addElement(plus);
        grid.addElement(new PlaceHolder().setW(10));
        grid.addElement(minus);
        grid.addElement(new PlaceHolder().setW(10));
        grid.addElement(reset);
        grid.row();
        grid.addElement(new PlaceHolder().setW(-100).setH(1));



        grid.setVerticalArrangement(true);


        Viewport.zoomEvent.addListener(new UIZoomEvent() {
            @Override
            public void zoomStart() {
                ZoomUI.super.enabled = true;
            }
        });

        formattingFrame.setBorderThickness(2);
        formattingFrame.setBorderRadius(5);
        formattingFrame.setFillColor(new Color(41f / 255f, 42f / 255, 45f / 255f, 1f));
        formattingFrame.setBorderColor(new Color(112f / 255f, 112f / 255, 112f / 255f, 1f));
    }

    @Override
    protected void draw() {
        textLabel.setText(Viewport.getZoomPercentage() + "%");
        if (plus.isjustPressed()) Viewport.increaseZoom(ProgramingSpace.cam);
        if (minus.isjustPressed()) Viewport.decreaseZoom(ProgramingSpace.cam);
        if (reset.isjustPressed()) Viewport.resetZoom(ProgramingSpace.cam);

        formattingFrame.setPosition(Gdx.graphics.getWidth() - UIVar.abstandvonRand - formattingFrame.w - 5, UIVar.programmflaeche_y + UIVar.programmflaeche_h - formattingFrame.h - 5);
        formattingFrame.draw();

        if(Gdx.input.isButtonJustPressed(0)&&!CheckMouse.wasMousePressed(UIManager.uiRegistry.zoomUi.getFormattingFrame().x,UIManager.uiRegistry.zoomUi.getFormattingFrame().y,UIManager.uiRegistry.zoomUi.getFormattingFrame().w,UIManager.uiRegistry.zoomUi.getFormattingFrame().h)) {
            super.enabled = false;
        }


    }

    public FormattingFrame getFormattingFrame() {
        return formattingFrame;
    }
}
