/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.UIElements.UIElement;
import de.ft.interitus.WindowManager;


public class FormattingFrame extends UIElement {
    private UIElement element=null;
    private Color fillColor=new Color(0, 0, 0, 1);
    private Color BorderColor=new Color(0,0,0,1);
    private int BorderRadius=0;
    private int BorderThickness=0;
    public FormattingFrame(UIElement element){
        this.element=element;
    }
    @Override
    public void draw() {
        super.w=this.element.w+BorderThickness*2;
        super.h=this.element.h+BorderThickness*2;
        element.setPosition(super.x+BorderThickness,super.y+BorderThickness);

        WindowManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        WindowManager.shapeRenderer.setColor(BorderColor);
        WindowManager.shapeRenderer.roundendrect(super.x,super.y,super.w,super.h,this.BorderRadius);

        WindowManager.shapeRenderer.setColor(fillColor);
        WindowManager.shapeRenderer.roundendrect(this.element.x,this.element.y,this.element.w,this.element.h,this.BorderRadius);
        WindowManager.shapeRenderer.end();
        element.draw();
    }

    public void setElement(UIElement element) {
        this.element = element;
    }

    public UIElement getElement() {
        return element;
    }

    public void setBorderColor(Color borderColor) {
        BorderColor = borderColor;
    }

    public void setBorderRadius(int borderRadius) {
        BorderRadius = borderRadius;
    }

    public void setBorderThickness(int borderThickness) {
        BorderThickness = borderThickness;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getBorderColor() {
        return BorderColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public int getBorderRadius() {
        return BorderRadius;
    }

    public int getBorderThickness() {
        return BorderThickness;
    }
}
