/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.UI.UIElements.UIElements.UIElement;
import de.ft.interitus.WindowManager;

import java.util.ArrayList;

public class Grid extends UIElement {

    private int rows = 0;
    private boolean VerticalVermitteln = false;
    private ArrayList<ArrayList<UIElement>> content = new ArrayList<>();

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
        int widthsprung=0;
        for (int row = 0; row < content.size(); row++) {
            int tempheight = 0;
            widthsprung = 0;

            for(int column = 0;column < content.get(row).size(); column++){
                if (content.get(row).get(column).getH() > tempheight) {
                    tempheight = content.get(row).get(column).getH();
                }
            }
            for (int column = 0; column < content.get(row).size(); column++) {
                int tempwidth = 0;
                for (int roww = 0; roww < content.size(); roww++) {
                    if (content.get(row).get(column).getW() > tempwidth) {
                        tempwidth = content.get(row).get(column).getW();
                    }
                }
                content.get(row).get(column).setX(this.x + widthsprung);
                if (VerticalVermitteln) {
                    content.get(row).get(column).setY(this.y - tempheight/2 - content.get(row).get(column).getH() / 2 + tempheight);
                } else {
                    content.get(row).get(column).setY(this.y - content.get(row).get(column).getH() + tempheight);
                }
                if(this.h!=0) content.get(row).get(column).draw();
                widthsprung += content.get(row).get(column).getW();
            }
            height += tempheight;
        }
        this.h = height;
        this.w=widthsprung;
    }



    public void setVerticalVermitteln(boolean verticalVermitteln) {
        VerticalVermitteln = verticalVermitteln;
    }

    public boolean isVerticalVermitteln() {
        return VerticalVermitteln;
    }
}
