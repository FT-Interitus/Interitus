package de.ft.interitus.events.block;

import de.ft.interitus.Block.Block;

import java.util.Vector;

public class BlockEventManager implements BlockEventListener {

    protected Vector listener = new Vector();

    public void removeListener(BlockEventListener l) {
        listener.remove(l);
    }


    public void addListener(BlockEventListener a) {
        if (!listener.contains(a))
            listener.addElement(a);
    }

    @Override
    public void createBlock(BlockCreateEvent e) {
        for (int i = 0; i < listener.size(); i++)
            ((BlockEventListener) listener.elementAt(i)).
                    createBlock(e);
    }

    @Override
    public void deleteBlock(BlockDeleteEvent e) {
        for (int i = 0; i < listener.size(); i++)
            ((BlockEventListener) listener.elementAt(i)).
                    deleteBlock(e);
    }

    @Override
    public void killmovingwires(BlockKillMovingWiresEvent e) {
        for (int i = 0; i < listener.size(); i++)
            ((BlockEventListener) listener.elementAt(i)).
                    killmovingwires(e);
    }

    @Override
    public void setNeighbor(BlockNeighborSetEvent e, Block block, Block neightbour,boolean right) {
        for (int i = 0; i < listener.size(); i++)
            ((BlockEventListener) listener.elementAt(i)).
                    setNeighbor(e,block, neightbour,right);
    }


}
