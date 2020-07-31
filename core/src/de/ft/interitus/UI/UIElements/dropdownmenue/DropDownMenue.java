package de.ft.interitus.UI.UIElements.dropdownmenue;

import com.badlogic.gdx.graphics.Color;
import de.ft.interitus.UI.UIElements.UIElements.UIElement;
import de.ft.interitus.utils.ShapeRenderer;
import de.ft.interitus.ProgrammingSpace;

import java.util.ArrayList;

public class DropDownMenue implements UIElement {
    ArrayList<DropDownElement>elements=new ArrayList<>();
    int x;
    int y;
    int w=100;
    int h=20;
    int radius=5;
    Color bordercolor;
    Color fillColor;

    public DropDownMenue(int x, int y, Color bordercolor, Color fillColor) {
        this.x = x;
        this.y = y;
        this.bordercolor = bordercolor;
        this.fillColor = fillColor;
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
        return w;
    }

    @Override
    public int getH() {
        return h;
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        this.x=x;
        this.y=y;
        this.h=h;
    }

    public void draw(){
        ProgrammingSpace.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        ProgrammingSpace.shapeRenderer.setColor(bordercolor);
        ProgrammingSpace.shapeRenderer.roundendrect(x,y,w,h,radius);
        ProgrammingSpace.shapeRenderer.setColor(fillColor);
        ProgrammingSpace.shapeRenderer.roundendrect(x+1,y+1,w-2,h-2,radius);
        ProgrammingSpace.shapeRenderer.end();
    }
}
