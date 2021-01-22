/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.dropdownmenue;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.UI.UIElements.UIElements.Button;

public class DropDownElement implements DropDownElementInterface {
    Button button;
    private Texture ElementImage;
    private String Text;
    private Object identifier;

    public DropDownElement(Texture elementImage, String text, Object identifier) {
        ElementImage = elementImage;
        Text = text;
        button = new Button();
        this.identifier = identifier;
        button.setVisible(false);
    }

    public Button getButton() {
        return button;
    }

    public Texture getElementImage() {
        return ElementImage;
    }

    public void setElementImage(Texture elementImage) {
        ElementImage = elementImage;
    }

    @Override
    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public Object getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Object identifier) {
        this.identifier = identifier;
    }

    @Override
    public DropDownElement getSelectedElement() {
        return this;
    }


}
