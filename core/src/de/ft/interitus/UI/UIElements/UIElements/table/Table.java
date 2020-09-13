package de.ft.interitus.UI.UIElements.UIElements.table;

import de.ft.interitus.UI.UIElements.UIElements.UIElement;

import java.util.ArrayList;

public class Table implements UIElement {
    private int x;
    private int y;
    private int columnCount;
    private int rowCount;
    private ArrayList<ArrayList<Element>> elements = new ArrayList<>();
    public Table(int x, int y, int columnCount, int rowCount) {
        this.x = x;
        this.y = y;
        this.columnCount = columnCount;
        this.rowCount = rowCount;

        for(int i=0;i<rowCount;i++){
            elements.add(new ArrayList<>());

            for(int j=0;j<columnCount;j++) {

                elements.get(elements.size()-1).add(new Element());
            }


        }

    }

    @Override
    public void draw() {



    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getW() {
        return 100;
    }

    @Override
    public int getH() {
        return 100;
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
    }



    @Override
    public void setAlpha(float alpha) {

    }
}
