/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.dropdownmenue;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import de.ft.interitus.Settings;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.UIElements.Button;
import de.ft.interitus.UI.UIElements.UIElements.UIElement;
import de.ft.interitus.UI.WindowAPI;
import de.ft.interitus.WindowManager;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.utils.ArrayList;
import de.ft.interitus.utils.ShapeRenderer;


public class DropDownMenue extends UIElement {
    private final ArrayList<DropDownElementInterface> elements = new ArrayList<>();
    private final int RADIUS = 5;
    private final Color bordercolor;
    private final GlyphLayout glyphLayout = new GlyphLayout();
    private final Button popupbutton = new Button();

    private boolean opened = false;
    private DropDownElementInterface selectedElement = null;
    private String defaultText;
    private int longestText = 0;


    public DropDownMenue(int x, int y, Color bordercolor, String defaultText) {
        this.x = x;
        this.y = y;
        this.bordercolor = bordercolor;

        popupbutton.setVisible(false);
        this.defaultText = defaultText;
    }

    private void longestText() {

        for (DropDownElementInterface element : elements) {
            glyphLayout.setText(UI.font, element.getText());
            if (glyphLayout.width > this.longestText) {
                this.longestText = (int) glyphLayout.width;
            }
        }
    }

    public void addelement(DropDownElementInterface e) {
        elements.add(e);
    }


    public DropDownElementInterface getSelectedElement() {
        return selectedElement;
    }

    public void setSelectedElement(DropDownElementInterface selectedElement) {
        this.selectedElement = selectedElement;
    }

    @Override
    public void draw() {
        WindowManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        WindowManager.shapeRenderer.setColor(bordercolor);
        WindowManager.shapeRenderer.roundendrect(x, y, w, h, RADIUS);
        WindowManager.shapeRenderer.setColor(Settings.theme.ClearColor());
        WindowManager.shapeRenderer.roundendrect(x + 1, y + 1, w - 2, h - 2, RADIUS);
        WindowManager.shapeRenderer.end();
        UI.UIbatch.begin();
        UI.UIbatch.setColor(1, 1, 1, 1);

        if (selectedElement != null) {
            glyphLayout.setText(UI.font, selectedElement.getText());
            this.w = (int) glyphLayout.width + 16 + 20 + 10;
            UI.UIbatch.draw(selectedElement.getElementImage(), x + 5, y + 2, 16, 16);
            UI.font.draw(UI.UIbatch, glyphLayout, x + 16 + 10, y + h / 2f + glyphLayout.height / 2);
            UI.UIbatch.draw(AssetLoader.aufklapppfeil, x + 16 + 10 + glyphLayout.width + 5, y + 5);
        } else {
            glyphLayout.setText(UI.font, defaultText);
            this.w = (int) glyphLayout.width + 16 + 20 + 10;
            UI.font.draw(UI.UIbatch, glyphLayout, x + 16 + 10, y + h / 2f + glyphLayout.height / 2);
        }
        UI.UIbatch.end();

        popupbutton.setBounds(this.x, this.y, this.w, this.h);
        if (popupbutton.isjustPressed()) {
            opened = !opened;
        }

        if (opened) {
            DropDownElementInterface actualelement;

            WindowManager.shapeRenderer.begin(com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled);
            WindowManager.shapeRenderer.setColor(Settings.theme.PopUpColor());
            longestText();
            WindowManager.shapeRenderer.rect(this.x, this.y - this.h * elements.size(), longestText + 16 + 20 + 10, this.h * elements.size());
            WindowManager.shapeRenderer.end();

            for (int i = 0; i < elements.size(); i++) {

                actualelement = elements.get(i);
                int actualY = this.y - this.h - (i * this.h);
                UI.UIbatch.begin();

                glyphLayout.setText(UI.font, actualelement.getText());
                UI.UIbatch.draw(actualelement.getElementImage(), x + 5, actualY + 2, 16, 16);
                UI.font.draw(UI.UIbatch, glyphLayout, x + 16 + 10, actualY + h / 2f + glyphLayout.height / 2);
                elements.get(i).getButton().setBounds(x, actualY, longestText + 16 + 20 + 10, h);
                if (elements.get(i).getButton().isPresseded()) {
                    selectedElement = elements.get(i);
                }
                UI.UIbatch.end();


            }
        }


        if (WindowAPI.isButtonPressed(0) && !popupbutton.isPresseded()) {
            opened = false;
        }

    }


    public ArrayList<DropDownElementInterface> getElements() {
        return elements;
    }

    public void clear() {
        selectedElement = null;
        elements.clear();
    }


    public String getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;
    }
}
