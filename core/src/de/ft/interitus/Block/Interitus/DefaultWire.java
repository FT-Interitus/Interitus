package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.*;

public class DefaultWire extends Wire {

    public DefaultWire(VisibleObjects left_connection, Block right_connection) {
        super(left_connection, right_connection);
    }

    public DefaultWire(VisibleObjects left_connection) {
        super(left_connection);
    }
}
