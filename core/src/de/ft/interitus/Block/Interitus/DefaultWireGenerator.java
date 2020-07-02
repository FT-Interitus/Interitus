package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Generators.WireGenerator;
import de.ft.interitus.Block.VisibleObjects;
import de.ft.interitus.Block.Wire;

public class DefaultWireGenerator implements WireGenerator {

    @Override
    public Wire generate(VisibleObjects left_connection, Block right_connection) {
        return new DefaultWire(left_connection, right_connection);
    }

    @Override
    public Wire generate(VisibleObjects left_connection) {
        return new DefaultWire(left_connection);
    }
}
