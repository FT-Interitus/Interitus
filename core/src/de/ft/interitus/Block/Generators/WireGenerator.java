package de.ft.interitus.Block.Generators;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.VisibleObjects;
import de.ft.interitus.Block.Wire;

public interface WireGenerator {
    Wire generate(final VisibleObjects left_connection, final Block right_connection);

    Wire generate(final VisibleObjects left_connection);
}
