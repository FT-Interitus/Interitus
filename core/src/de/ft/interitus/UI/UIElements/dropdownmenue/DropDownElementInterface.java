/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.dropdownmenue;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.UI.UIElements.UIElements.Button;

public interface DropDownElementInterface {
    String getText();
    Texture getElementImage();
    Button getButton();
    DropDownElement getSelectedElement();
    void setText(String text);
    void setIdentifier(Object identifier);
    Object getIdentifier();
}
