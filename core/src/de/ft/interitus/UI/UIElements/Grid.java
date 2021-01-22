/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements;

import de.ft.interitus.UI.UIElements.UIElements.UIElement;

import java.util.ArrayList;

public class Grid extends UIElement {

    private final ArrayList<ArrayList<UIElement>> content = new ArrayList<>();
    private int rows = 0;
    private boolean VerticalArrangement = false;

    public Grid(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
        this.content.add(new ArrayList<>());
    }

    public void addElement(UIElement element) {
        this.content.get(rows).add(element);
    }

    public void row() {
        this.content.add(new ArrayList<>());
        rows++;
    }

    @Override
    public void draw() {

        int height = 0;
        int widthJumping = 0;
        for (int row = 0; row < content.size(); row++) {
            int tempHeight = 0;
            widthJumping = 0;

            for (int column = 0; column < content.get(row).size(); column++) {
                if (content.get(row).get(column).getH() > tempHeight) {
                    tempHeight = content.get(row).get(column).getH();
                }
            }
            for (int column = 0; column < content.get(row).size(); column++) {
                int tempWidth = 0;
                for (int rowWidth = 0; rowWidth < content.size(); rowWidth++) {
                    if (content.get(row).get(column).getW() > tempWidth) {
                        break;
                    }
                }
                content.get(row).get(column).setX(this.x + widthJumping);
                if (VerticalArrangement) {
                    content.get(row).get(column).setY(this.y - tempHeight / 2 - content.get(row).get(column).getH() / 2 + tempHeight);
                } else {
                    content.get(row).get(column).setY(this.y - content.get(row).get(column).getH() + tempHeight);
                }
                if (this.h != 0) content.get(row).get(column).draw();
                widthJumping += content.get(row).get(column).getW();
            }
            height += tempHeight;
        }
        this.h = height;
        this.w = widthJumping;
    }

    @SuppressWarnings("unused")
    public boolean isVerticalArrangement() {
        return VerticalArrangement;
    }

    public void setVerticalArrangement(boolean verticalArrangement) {
        VerticalArrangement = verticalArrangement;
    }
}
