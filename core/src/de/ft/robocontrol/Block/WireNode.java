package de.ft.robocontrol.Block;

import com.badlogic.gdx.math.Frustum;
import de.ft.robocontrol.ProgrammingSpace;

public class WireNode implements VisibleObjects {
    private Wire wire_left;
    private Wire wire_right;
    private int x;
    private int y;
    private int w;
    private int h;
    private Frustum frustum;
    public WireNode(Wire wire_left) { //Wenn eine neue Node gesetzt wird

        this.wire_left = wire_left;

    }

    public WireNode(Wire wire_left,Wire wire_right) {

    }

    public Wire getWire_right() {
        return wire_right;
    }

    public Wire getWire_left() {
        return wire_left;
    }

    public void setWire_right(Wire wire_right) {
        this.wire_right = wire_right;
    }

    public void setWire_left(Wire wire_left) {
        this.wire_left = wire_left;
    }

    @Override
    public boolean isVisible() {
        frustum = ProgrammingSpace.cam.frustum;
       return frustum.boundsInFrustum(x,y,0,w,h,0);

    }

    @Override
    public boolean amiablock() {
        return false;
    }

    @Override
    public Block getblock() {
        return null;
    }

    @Override
    public int getX_entrance() {
        return 0;
    }

    @Override
    public int getY_entrance() {
        return 0;
    }

    @Override
    public int getW_entrance() {
        return 0;
    }

    @Override
    public int getH_entrance() {
        return 0;
    }

    @Override
    public int getX_exit() {
        return 0;
    }

    @Override
    public int getY_exit() {
        return 0;
    }

    @Override
    public int getW_exit() {
        return 0;
    }

    @Override
    public int getH_exit() {
        return 0;
    }
}
