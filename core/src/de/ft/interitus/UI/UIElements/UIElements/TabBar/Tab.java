/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.UIElements.TabBar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.UI.UIElements.UIElements.Button;
import de.ft.interitus.WindowManager;


public class Tab {
    private Button TabButton;
    private int index = -1;
    private boolean closeable = true;
    private Button CloseButton;
    private Color tabcolor = new Color(100f / 255, 100f / 255f, 100f / 255f, 1f);
    private Color mouseovertabcolor = new Color(140f / 255, 140f / 255f, 140f / 255f, 1f);
    private Color selected = new Color(163f / 255f, 163f / 255f, 163f / 255f, 1);
    private final int CloseButtonDiameter = 10;

    public Tab() {
        TabButton = new Button();
        CloseButton = new Button();
    }

    public void draw(boolean selectedTabindex, int y) {
        WindowManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if (getTabButton().isMouseover()) {
            WindowManager.shapeRenderer.setColor(getMouseovertabcolor());
        } else {
            WindowManager.shapeRenderer.setColor(getTabcolor());

        }
        if (selectedTabindex) {
            WindowManager.shapeRenderer.setColor(getSelected());
        }
        WindowManager.shapeRenderer.rect(getTabButton().getX(), y, getW(), getTabButton().getH());
        if (selectedTabindex) {
            WindowManager.shapeRenderer.setColor(86f / 255f, 138f / 255f, 242f / 255f, 1);
            WindowManager.shapeRenderer.rect(getTabButton().getX(), y, getW(), 3);
        }
        WindowManager.shapeRenderer.end();

        if (isCloseable()) {
            getCloseButton().setX((int) (getTabButton().getX() + getTabButton().getW() + 1f));
            getCloseButton().setY(getTabButton().getY() + (getTabButton().getH() / 2 - CloseButtonDiameter / 2));
            getCloseButton().setH(CloseButtonDiameter);
            getCloseButton().setW(CloseButtonDiameter);
        } else {
            getCloseButton().setW(0);
        }

        getTabButton().draw();

        if (isCloseable()) getCloseButton().draw();
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

    public Color getTabcolor() {
        return tabcolor;
    }

    public void setTabcolor(Color tabcolor) {
        this.tabcolor = tabcolor;
    }

    public Color getMouseovertabcolor() {
        return mouseovertabcolor;
    }

    public void setMouseovertabcolor(Color mouseovertabcolor) {
        this.mouseovertabcolor = mouseovertabcolor;
    }

    public Color getSelected() {
        return selected;
    }

    public void setSelected(Color selected) {
        this.selected = selected;
    }

    public int getW() {
        return TabButton.getW() + CloseButton.getW() + 7;
    }

    public int getX() {
        return TabButton.getX();
    }

    public void setX(int x) {
        TabButton.setX(x);
        CloseButton.setX(TabButton.getW() + TabButton.getX());
    }

    public void setH(int h) {
        TabButton.setH(h);
        //CloseButton.setH(h);
    }

    public void setY(int y) {
        TabButton.setY(y);
    }

    public boolean isCloseable() {
        return closeable;
    }

    public Tab setCloseable(boolean closeable) {
        this.closeable = closeable;
        return this;
    }
}

