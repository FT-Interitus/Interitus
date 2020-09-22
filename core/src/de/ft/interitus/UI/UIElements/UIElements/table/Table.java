/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.UIElements.table;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.Programm;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.UIElements.UIElements.UIElement;

import de.ft.interitus.utils.ArrayList;

public class Table implements UIElement {
    private int x;
    private int y;
    private int columnCount;
    private int rowCount;
    //private ArrayList<ArrayList<Element>> elements = new ArrayList<>();
    ArrayList<Row>rows=new ArrayList<>();
    ArrayList<Column>columns=new ArrayList<>();
    public Table(int x, int y, int columnCount, int rowCount) {
        this.x = x;
        this.y = y;
        this.columnCount = columnCount;
        this.rowCount = rowCount;
/*
        for(int i=0;i<rowCount;i++){
            elements.add(new ArrayList<>());

            for(int j=0;j<columnCount;j++) {

                elements.get(elements.size()-1).add(new Element());
            }


        }*/
        for(int i=0;i<columnCount;i++){
            columns.add(new Column(50));
        }
        for(int i=0;i<rowCount;i++){
            rows.add(new Row(20));
        }

    }

    public void addRow(Row row){
        rows.add(0,row);
        rowCount++;
    }
    public void addColumn(Column column){
        columns.add(column);
        columnCount++;
    }
    public Column getColumn(int i){
        return columns.get(i);
    }
    public Row getRow(int i){
        return rows.get(i);
    }

    public int getRowsHeight(){
        int b=0;
        for(int i=0;i< rows.size();i++){
            b+=rows.get(i).getHeight();
        }
        return b;
    }
    public int getColumnWidth(){
        int b=0;
        for(int i=0;i< columns.size();i++){
            b+=columns.get(i).getWeight();
        }
        return b;
    }
    @Override
    public void draw() {
        int a=0;
        for(int c=0;c<columnCount;c++){
            ProgrammingSpace.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            ProgrammingSpace.shapeRenderer.line(this.x+a,this.y,this.x+a,this.y+getRowsHeight());
            a+=columns.get(c).getWeight();
            ProgrammingSpace.shapeRenderer.line(this.x+a,this.y,this.x+a,this.y+getRowsHeight());
            ProgrammingSpace.shapeRenderer.end();
        }
        a=0;
        for(int r=0;r<rowCount;r++){
            ProgrammingSpace.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            ProgrammingSpace.shapeRenderer.line(this.x,this.y+a,this.x+getColumnWidth(),this.y+a);
            a+=rows.get(r).getHeight();
            ProgrammingSpace.shapeRenderer.line(this.x,this.y+a,this.x+getColumnWidth(),this.y+a);
            ProgrammingSpace.shapeRenderer.end();
        }



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
