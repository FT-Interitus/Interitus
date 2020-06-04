package de.ft.interitus.Block;

public interface WireNodeGenerator {
    WireNode generate(Wire wiresleft, int x, int y, int w, int h);
    WireNode generate(Wire wire_left, Wire wire_right);
}
