package de.ft.interitus.events.block;

public interface BlockEventListener extends java.util.EventListener {

    //TODO Block moved hovered marked Event...
    void createBlock(BlockCreateEvent e);

    void deleteBlock(BlockDeleteEvent e);
}
