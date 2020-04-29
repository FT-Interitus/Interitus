package de.ft.interitus.events.block;

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
}
