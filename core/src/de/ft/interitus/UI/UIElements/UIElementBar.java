package de.ft.interitus.UI.UIElements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.ft.interitus.UI.UIElements.UIElements.Button;
import de.ft.interitus.UI.UIElements.UIElements.UIElement;
import de.ft.interitus.utils.ArrayList;

public class UIElementBar {
    private final ArrayList<UIElement> buttons = new ArrayList<>();
    private int x, y, button_h;
    private int button_abstand = 4;

    public UIElementBar() {
    }

    public UIElementBar(int x, int y, int button_h, UIElement... elemente) {
        this.x = x;
        this.y = y;
        this.button_h = button_h;
        for (int i = 0; i < elemente.length; i++) {
            this.buttons.add(elemente[i]);
        }
    }

    public void setButtons(int x, int y, int button_h, UIElement... elements) {
        this.buttons.clear();
        this.x = x;
        this.y = y;
        this.button_h = button_h;
        for (int i = 0; i < elements.length; i++) {
            this.buttons.add(elements[i]);
        }
    }

    public void setButtons(int x, int y, int button_h) {
        this.buttons.clear();
        this.x = x;
        this.y = y;
        this.button_h = button_h;

    }

    public void addButton(UIElement button) {
        buttons.add(button);
    }

    public ArrayList<UIElement> getButtons() {
        return buttons;
    }

    public void draw(SpriteBatch batch) {
        /*for (int i = 0; i < this.buttons.size(); i++) {
            UIElement akutalbutton = this.buttons.get(i);
            akutalbutton.setBounds(this.x - this.button_w - (this.button_w * i) - button_abstand * i, this.y, this.button_w, this.button_h);
            akutalbutton.draw();
        }*/
        int aktualXPosition = this.x;
        for (int i = 0; i < this.buttons.size(); i++) {
            aktualXPosition -= this.buttons.get(i).getW();
            aktualXPosition -= button_abstand;
            this.buttons.get(i).setBounds(aktualXPosition, this.y, this.buttons.get(i).getW(), button_h);
            this.buttons.get(i).draw();

        }
    }

    public int getButton_abstand() {
        return button_abstand;
    }

    public void setButton_abstand(int button_abstand) {
        this.button_abstand = button_abstand;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getButton_h() {
        return button_h;
    }

    public void setButton_h(int button_h) {
        this.button_h = button_h;
    }


}
