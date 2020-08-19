/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.BlockDropDownMenue;

import de.ft.interitus.UI.UIElements.UIElements.UIElement;

import java.util.ArrayList;

public class BlockDropDownMenue implements UIElement {
    private int x=0;
    private int y=0;
    private int w=0;
    private int h=0;

    private ArrayList<BlockDropDownItem>bddi=new ArrayList<>();
    private BlockDropDownItem selectedItem = null;

    public BlockDropDownMenue(int x, int y, int w, int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
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
        return this.w;
    }

    @Override
    public int getH() {
        return this.h;
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
    }

    @Override
    public void draw() {

    }

    @Override
    public void setAlpha(float alpha) {

    }
}
