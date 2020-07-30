package de.ft.interitus.UI.UIElements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.ft.interitus.utils.ArrayList;

public class ButtonBar {
    ArrayList<Button> buttons = new ArrayList<>();
    int x, y, button_w, button_h;
    int button_abstand = 3;

    public ButtonBar() {
    }

    public ButtonBar(int x, int y, int button_w, int button_h, Button... buttons) {
        this.x = x;
        this.y = y;
        this.button_w = button_w;
        this.button_h = button_h;
        for (int i = 0; i < buttons.length; i++) {
            this.buttons.add(buttons[i]);
        }
    }

    public void setButtons(int x, int y, int button_w, int button_h, Button... buttons) {
        this.buttons.clear();
        this.x = x;
        this.y = y;
        this.button_w = button_w;
        this.button_h = button_h;
        for (int i = 0; i < buttons.length; i++) {
            this.buttons.add(buttons[i]);
        }
    }

    public void setButtons(int x, int y, int button_w, int button_h) {
        this.buttons.clear();
        this.x = x;
        this.y = y;
        this.button_w = button_w;
        this.button_h = button_h;

    }

    public void addButton(Button button) {
        buttons.add(button);
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public void draw(SpriteBatch batch) {
        for (int i = 0; i < this.buttons.size(); i++) {
            Button akutalbutton = this.buttons.get(i);
            akutalbutton.setBounds(this.x - this.button_w - (this.button_w * i) - button_abstand * i, this.y, this.button_w, this.button_h);
            akutalbutton.draw();
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

    public int getButton_w() {
        return button_w;
    }

    public void setButton_w(int button_w) {
        this.button_w = button_w;
    }
}
