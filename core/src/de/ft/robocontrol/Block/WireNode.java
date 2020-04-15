package de.ft.robocontrol.Block;

import com.badlogic.gdx.math.Frustum;
import de.ft.robocontrol.ProgrammingSpace;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WireNode implements VisibleObjects {
    private ArrayList<Wire> wire_left;
    private ArrayList<Wire> wire_right;
    private int x;
    private int y;
    private int w;
    private int h;
    private Frustum frustum;
    public WireNode(ArrayList<Wire> wiresleft,int x,int y,int w,int h) { //Wenn eine neue Node gesetzt wird

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.wire_left = (ArrayList<Wire>) wiresleft.clone();

    }

    public WireNode(Wire wire_left,Wire wire_right) {

    }

    public ArrayList<Wire> getWire_right() {
        return wire_right;
    }

    public ArrayList<Wire> getWire_left() {
        return wire_left;
    }

    public void setWire_right(ArrayList<Wire> wire_right) {
        this.wire_right = wire_right;
    }

    public void setWire_left(ArrayList<Wire> wire_left) {
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
    public float getX_entrance() {
        return x;
    }

    @Override
    public float getY_entrance() {
        return y;
    }

    @Override
    public float getW_entrance() {
        return w;
    }

    @Override
    public float getH_entrance() {
        return h;
    }

    @Override
    public float getX_exit() {
        return x;
    }

    @Override
    public float getY_exit() {
        return y;
    }

    @Override
    public float getW_exit() {
        return w;
    }

    @Override
    public float getH_exit() {
        return h;
    }




}
