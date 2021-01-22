/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.WindowManager;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.Unproject;


public class Wire {
    private Block leftConnection = null;
    private Block rightConnection = null;
    private boolean visible = false;

    public Wire() {

        visible = true;

    }

    public Wire(Block leftConnection) {
        this.leftConnection = leftConnection;
        if (leftConnection != null) {
            leftConnection.setWire_right(this);
            this.visible = true;
            assert ProjectManager.getActProjectVar() != null;
            ProjectManager.getActProjectVar().movingWire = this;
        }

    }

    public Wire(Block leftConnection, Block rightConnection) {

        this.leftConnection = leftConnection;
        this.rightConnection = rightConnection;
        if (leftConnection != null) leftConnection.setWire_right(this);
        if (rightConnection != null) rightConnection.setWire_left(this);

    }

    public Wire(Block leftConnection, Block rightConnection, boolean visibility) {

        this.leftConnection = leftConnection;
        this.rightConnection = rightConnection;
        if (leftConnection != null) leftConnection.setWire_right(this);
        if (rightConnection != null) rightConnection.setWire_left(this);
        this.visible = visibility;

    }

    /**
     * @param leftConnection
     * @deprecated Use Constructor
     */
    public void setLeftConnection(Block leftConnection) {
        if (leftConnection != null) leftConnection.setWire_right(this);
        this.leftConnection = leftConnection;
    }

    public void setRightConnection(Block rightConnection) {
        assert ProjectManager.getActProjectVar() != null;
        if (rightConnection != null) {
            rightConnection.setWire_left(this);
            this.visible = true;
        } else {
            this.visible = false;
        }
        this.rightConnection = rightConnection;
        if (ProjectManager.getActProjectVar().movingWire == this) ProjectManager.getActProjectVar().movingWire = null;

    }

    public Block getLeftConnection() {
        return leftConnection;
    }


    public Block getRightConnection() {
        return rightConnection;
    }

    public void draw() {

        if (!visible) return;
        assert ProjectManager.getActProjectVar() != null;
        if (ProjectManager.getActProjectVar().movingWire != this) {
            WindowManager.BlockshapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            WindowManager.BlockshapeRenderer.setColor(1f, 0f, 0f, 0f);
            WindowManager.BlockshapeRenderer.line(this.leftConnection.getWireConnectorRight(), this.rightConnection.getWireConnectorLeft());
            WindowManager.BlockshapeRenderer.end();
        } else {
            WindowManager.BlockshapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            WindowManager.BlockshapeRenderer.setColor(1f, 0f, 0f, 0f);
            WindowManager.BlockshapeRenderer.line(this.leftConnection.getWireConnectorRight(), Unproject.unproject());
            WindowManager.BlockshapeRenderer.end();
        }


    }

    public boolean isVisible() {
        return visible;
    }


    public void delete() {

        if (this.rightConnection != null) rightConnection.setWire_left(null);
        if (this.leftConnection != null) leftConnection.setWire_right(null);

        this.rightConnection = null;
        this.leftConnection = null;
        assert ProjectManager.getActProjectVar() != null;
        if (ProjectManager.getActProjectVar().movingWire == this) ProjectManager.getActProjectVar().movingWire = null;
        ProjectManager.getActProjectVar().showLeftDocker = false;

    }
}
