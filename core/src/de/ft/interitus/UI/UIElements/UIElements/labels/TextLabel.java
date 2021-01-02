/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.UIElements.labels;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.UIElements.UIElement;
import de.ft.interitus.WindowManager;

public class TextLabel implements UIElement {
    String text="";
    GlyphLayout glyphLayout=new GlyphLayout();

    int abstandvonrand=0;
    int x=0;
    int y=0;
    int w=0;
    int h=0;

    public TextLabel(){

    }
    public TextLabel(String text){
        this.text=text;
    }

    public void setText(){
        this.text=text;
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        this.x=x;
        this.y=y;
        this.h=h;
        this.w=w;
    }

    @Override
    public void draw() {
        glyphLayout.setText(WindowManager.font, this.text);
        UI.UIbatch.begin();
        this.w=(int)(glyphLayout.width+abstandvonrand*2);
        this.h=(int)(glyphLayout.height+abstandvonrand*2);

        WindowManager.font.draw(UI.UIbatch, glyphLayout, this.x + this.w / 2 - glyphLayout.width / 2 + abstandvonrand, this.y + this.h / 2 + glyphLayout.height/2);
        UI.UIbatch.end();
    }

    @Override
    public void setAlpha(float alpha) {

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
    public Object setX(int x) {
        this.x=x;
        return this;
    }

    @Override
    public Object setY(int y) {
        this.y=y;
        return this;
    }

    @Override
    public Object setW(int w) {
        this.w=w;
        return this;
    }

    @Override
    public Object setH(int h) {
        this.h=h;
        return this;
    }

    @Override
    public Object setPosition(int x, int y) {
        this.x=x;
        this.y=y;
        return this;
    }
}
