/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements;

import de.ft.interitus.UI.UIElements.UIElements.UIElement;
import de.ft.interitus.utils.ArrayList;

import java.util.Arrays;

public class UIElementBar {
    private final ArrayList<UIElement> buttons = new ArrayList<>();
    private int x, y, button_h;
    private int button_distance = 4;

    public UIElementBar() {
    }

    public UIElementBar(int x, int y, int button_h, UIElement... elemente) {
        this.x = x;
        this.y = y;
        this.button_h = button_h;
        this.buttons.addAll(Arrays.asList(elemente));
    }

    public void setButtons(int x, int y, int button_h, UIElement... elements) {
        this.buttons.clear();
        this.x = x;
        this.y = y;
        this.button_h = button_h;
        this.buttons.addAll(Arrays.asList(elements));
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

    public void draw() {

        int aktualXPosition = this.x;
        for (UIElement button : this.buttons) {
            aktualXPosition -= button.getW();
            aktualXPosition -= button_distance;
            button.setBounds(aktualXPosition, this.y, button.getW(), button_h);
            button.draw();

        }

    }

    public int getButton_distance() {
        return button_distance;
    }

    public void setButton_distance(int button_distance) {
        this.button_distance = button_distance;
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
