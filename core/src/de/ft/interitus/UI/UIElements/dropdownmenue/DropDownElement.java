package de.ft.interitus.UI.UIElements.dropdownmenue;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.UI.UIElements.UIElements.Button;

public class DropDownElement {
    private Texture ElementImage;
    private String Text;
    Button button;
    public DropDownElement(Texture elementImage, String text) {
        ElementImage = elementImage;
        Text = text;
        button=new Button();
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

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
