package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.Generators.WireNodeGenerator;
import de.ft.interitus.Block.Wire;
import de.ft.interitus.Block.WireNode;

public class DefaultWireNodeGenerator implements WireNodeGenerator {
    @Override
    public WireNode generate(Wire wiresleft, int x, int y, int w, int h) {
        return new DefaultWireNode(wiresleft, x, y, w, h);
    }

    @Override
    public WireNode generate(Wire wire_left, Wire wire_right) {
        return new DefaultWireNode(wire_left, wire_right);
    }
}
