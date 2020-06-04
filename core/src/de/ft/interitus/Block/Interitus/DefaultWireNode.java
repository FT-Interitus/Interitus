package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.Wire;
import de.ft.interitus.Block.WireNode;

public class DefaultWireNode extends WireNode {
    public DefaultWireNode(Wire wiresleft, int x, int y, int w, int h) {
        super(wiresleft, x, y, w, h);
    }

    public DefaultWireNode(Wire wire_left, Wire wire_right) {
        super(wire_left, wire_right);
    }
}
