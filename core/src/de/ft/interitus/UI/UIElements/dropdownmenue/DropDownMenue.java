package de.ft.interitus.UI.UIElements.dropdownmenue;

import de.ft.interitus.utils.ShapeRenderer;
import de.ft.interitus.ProgrammingSpace;

import java.awt.*;
import java.util.ArrayList;

public class DropDownMenue {
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

    public void draw(){
        ProgrammingSpace.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        ProgrammingSpace.shapeRenderer.setColor(1,0,0,1);
        ProgrammingSpace.shapeRenderer.roundendrect(x,y,w,h,radius);
        ProgrammingSpace.shapeRenderer.setColor(0,1,0,1);
        ProgrammingSpace.shapeRenderer.roundendrect(x+1,y+1,w-2,h-2,radius);
        ProgrammingSpace.shapeRenderer.end();
    }
}
