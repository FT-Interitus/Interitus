/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.TabBar;

import de.ft.interitus.UI.UIElements.UIElements.Button;

public class Tab {
    private Button TabButton;

    private int index = -1;
    private Button CloseButton;

    public Tab() {
        TabButton = new Button();
        CloseButton = new Button();
    }

    public Button getTabButton() {
        return TabButton;
    }

    public void setTabButton(Button tabButton) {
        TabButton = tabButton;
    }

    public Button getCloseButton() {
        return CloseButton;
    }

    public void setCloseButton(Button closeButton) {
        CloseButton = closeButton;
    }



    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
