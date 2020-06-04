package de.ft.interitus.Block;

public interface WireGenerator {
   Wire generate(final VisibleObjects left_connection, final Block right_connection);
    Wire generate(final VisibleObjects left_connection);
}
