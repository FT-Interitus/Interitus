package de.ft.interitus.Block.Generators;

import de.ft.interitus.Block.Wire;
import de.ft.interitus.Block.WireNode;

public interface WireNodeGenerator {
    WireNode generate(Wire wiresleft, int x, int y, int w, int h);

    WireNode generate(Wire wire_left, Wire wire_right);
}
