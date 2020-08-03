/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.VisibleObjects;
import de.ft.interitus.Block.Wire;

public class DefaultWire extends Wire {

    public DefaultWire(VisibleObjects left_connection, Block right_connection) {
        super(left_connection, right_connection);
    }

    public DefaultWire(VisibleObjects left_connection) {
        super(left_connection);
    }
}
