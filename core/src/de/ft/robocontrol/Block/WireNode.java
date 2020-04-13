package de.ft.robocontrol.Block;

public class WireNode {
    private Wire wire_left;
    private Wire wire_right;
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
}
