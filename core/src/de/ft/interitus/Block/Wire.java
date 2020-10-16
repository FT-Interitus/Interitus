/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

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
