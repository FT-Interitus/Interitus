package de.ft.interitus.UI.UIElements.dropdownmenue;

import com.badlogic.gdx.graphics.Texture;

public class DropDownElement {
    private Texture ElementImage;
    private String Text;
    public DropDownElement(Texture elementImage, String text) {
        ElementImage = elementImage;
        Text = text;
    }

    public Texture getElementImage() {
        return ElementImage;
    }

    public void setElementImage(Texture elementImage) {
        ElementImage = elementImage;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
