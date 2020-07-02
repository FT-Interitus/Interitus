package de.ft.interitus.events.block;

public interface BlockEventListener extends java.util.EventListener {

    //TODO Block moved hovered marked Event...
    void createBlock(BlockCreateEvent e);

    void deleteBlock(BlockDeleteEvent e);
    void killmovingwires(BlockKillMovingWiresEvent e);

    //TODO Disable ALl Thread from Blocks if Block is open Important
}
