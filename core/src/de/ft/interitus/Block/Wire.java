/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.WindowManager;

public class Wire {
    private Block leftConnection = null;
    private Block rightConnection = null;
    private boolean visible = false;

    public Wire() {


    }

    public Wire(Block leftConnection) {
        this.leftConnection = leftConnection;
        if(leftConnection!=null) leftConnection.setWire_right(this);

    }

    public Wire(Block leftConnection,Block rightConnection) {

        this.leftConnection = leftConnection;
        this.rightConnection = rightConnection;
        if(leftConnection!=null) leftConnection.setWire_right(this);
        if(rightConnection!=null) rightConnection.setWire_left(this);

    }

    public Wire(Block leftConnection,Block rightConnection,boolean visibility) {

        this.leftConnection = leftConnection;
        this.rightConnection = rightConnection;
        if(leftConnection!=null) leftConnection.setWire_right(this);
        if(rightConnection!=null) rightConnection.setWire_left(this);
        this.visible = visibility;

    }


    public void setLeftConnection(Block leftConnection) {
        if(leftConnection!=null) leftConnection.setWire_right(this);
        this.leftConnection = leftConnection;
    }

    public void setRightConnection(Block rightConnection) {
        if(rightConnection!=null) rightConnection.setWire_left(this);
        this.rightConnection = rightConnection;
    }

    public Block getLeftConnection() {
        return leftConnection;
    }


    public Block getRightConnection() {
        return rightConnection;
    }

    public void draw() {


        WindowManager.BlockshapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        WindowManager.BlockshapeRenderer.setColor(1f,0f,0f,0f);
        WindowManager.BlockshapeRenderer.line(this.leftConnection.getwireconnector_right(),this.rightConnection.getWireConnector_left());
        WindowManager.BlockshapeRenderer.end();
        if(!visible) return;




    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void delete() {

        if(this.rightConnection!=null) rightConnection.setWire_left(null);
        if(this.leftConnection!=null) leftConnection.setWire_right(null);

        this.rightConnection = null;
        this.leftConnection = null;


    }
}
