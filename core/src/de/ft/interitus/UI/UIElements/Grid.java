/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.UI.UIElements.UIElements.UIElement;
import de.ft.interitus.WindowManager;

import java.util.ArrayList;

public class Grid implements UIElement {
    private int x;
    private int y;
    private int width;
    private int height;
    private int rows=0;
    private ArrayList<ArrayList<UIElement>>content=new ArrayList<>();
    public Grid(int x,int y,int width,int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.content.add(new ArrayList<>());
    }
    public void addElement(UIElement element){
        this.content.get(rows).add(element);
    }
    public void row(){
        this.content.add(new ArrayList<>());
        rows++;
    }
    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getW() {
        return this.width;
    }

    @Override
    public int getH() {
        return this.height;
    }

    @Override
    public Object setX(int x) {
        this.x=x;
        return null;
    }

    @Override
    public Object setY(int y) {
        this.y=y;
        return null;

    }

    @Override
    public Object setW(int w) {
        this.width=w;
        return null;

    }

    @Override
    public Object setH(int h) {
        this.height=h;
        return null;
    }

    @Override
    public Object setPosition(int x, int y) {
        this.x=x;
        this.y=y;
        return null;

    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        this.x=x;
        this.y=y;
        this.width=w;
        this.height=h;
    }

    @Override
    public void draw() {

        int height=0;
        for(int row = 0; row < content.size(); row++){
            int tempheight=0;
            int widthsprung=0;


                for(int column=0;column<content.get(row).size();column++){
                    int tempwidth=0;
                    for(int roww = 0; roww < content.size(); roww++){
                        if(content.get(row).get(column).getW()>tempwidth){
                            tempwidth=content.get(row).get(column).getW();
                        }
                    }

                        if(content.get(row).get(column).getH()>tempheight){
                    tempheight=content.get(row).get(column).getH();
                }
                content.get(row).get(column).setX(this.x+widthsprung);
                content.get(row).get(column).setY(this.y-content.get(row).get(column).getH()-height);
                content.get(row).get(column).draw();
                widthsprung+=content.get(row).get(column).getW();
            }
            height+=tempheight;
        }
    }

    @Override
    public void setAlpha(float alpha) {

    }
}
