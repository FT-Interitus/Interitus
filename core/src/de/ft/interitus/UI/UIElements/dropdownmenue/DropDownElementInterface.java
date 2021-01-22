/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.dropdownmenue;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.UI.UIElements.UIElements.Button;

public interface DropDownElementInterface {
    String getText();

    void setText(String text);

    Texture getElementImage();

    Button getButton();

    DropDownElement getSelectedElement();

    Object getIdentifier();

    void setIdentifier(Object identifier);
}
