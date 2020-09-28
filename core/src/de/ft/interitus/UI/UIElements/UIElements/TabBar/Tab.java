/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.UIElements.TabBar;

import com.badlogic.gdx.graphics.Color;
import de.ft.interitus.UI.UIElements.UIElements.Button;


public class Tab {
    private Button TabButton;

    private int index = -1;
    private Button CloseButton;
    private Color tabcolor=new Color(100f/255, 100f/255f, 100f/255f,1f);
    private Color mouseovertabcolor=new Color(140f/255, 140f/255f, 140f/255f,1f);
    private Color selected=new Color(163f/255f, 163f/255f, 163f/255f,1);

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

    public void setTabcolor(Color tabcolor) {
        this.tabcolor = tabcolor;
    }

    public Color getTabcolor() {
        return tabcolor;
    }

    public void setMouseovertabcolor(Color mouseovertabcolor) {
        this.mouseovertabcolor = mouseovertabcolor;
    }

    public Color getMouseovertabcolor() {
        return mouseovertabcolor;
    }

    public Color getSelected() {
        return selected;
    }

    public void setSelected(Color selected) {
        this.selected = selected;
    }
}
