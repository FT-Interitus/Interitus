/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.dropdownmenue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.Settings;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.UIElements.Button;
import de.ft.interitus.UI.UIElements.UIElements.UIElement;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.utils.ShapeRenderer;

import java.util.ArrayList;

public class DropDownMenue implements UIElement {
    ArrayList<DropDownElementInterface> elements = new ArrayList<>();
    int x;
    int y;
    int w = 100;
    int h = 20;
    int radius = 5;
    boolean opened = false;
    Color bordercolor;
    Color fillColor;
    DropDownElementInterface selectedElement = null;
    GlyphLayout glyphLayout = new GlyphLayout();
    Button aufklappbutton;
    String defaultText;
    private int longestText = 0;


    public DropDownMenue(int x, int y, Color bordercolor, Color fillColor, String defaultText) {
        this.x = x;
        this.y = y;
        this.bordercolor = bordercolor;
        this.fillColor = fillColor;
        aufklappbutton = new Button();
        aufklappbutton.setVisible(false);
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

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getW() {
        return w;
    }

    @Override
    public int getH() {
        return h;
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }

    public DropDownElementInterface getSelectedElement() {
        return selectedElement;
    }

    public void setSelectedElement(DropDownElement selectedElement) {
        this.selectedElement = selectedElement;
    }

    public void draw() {
        ProgrammingSpace.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        ProgrammingSpace.shapeRenderer.setColor(bordercolor);
        ProgrammingSpace.shapeRenderer.roundendrect(x, y, w, h, radius);
        ProgrammingSpace.shapeRenderer.setColor(fillColor);
        ProgrammingSpace.shapeRenderer.roundendrect(x + 1, y + 1, w - 2, h - 2, radius);
        ProgrammingSpace.shapeRenderer.end();
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

        aufklappbutton.setBounds(this.x, this.y, this.w, this.h);
        if (aufklappbutton.isjustPressednormal()) {
            opened = !opened;
        }

        if (opened) {
            DropDownElementInterface actualelement;

            ProgrammingSpace.shapeRenderer.begin(com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled);
            ProgrammingSpace.shapeRenderer.setColor(Settings.theme.PopUpColor());
            longestText();
            ProgrammingSpace.shapeRenderer.rect(this.x, this.y - this.h * elements.size(), longestText + 16 + 20 + 10, this.h * elements.size());
            ProgrammingSpace.shapeRenderer.end();

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


        if (Gdx.input.isButtonPressed(0) && !aufklappbutton.isPresseded()) {
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
