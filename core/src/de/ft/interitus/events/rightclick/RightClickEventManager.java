package de.ft.interitus.events.rightclick;

import de.ft.interitus.events.block.BlockEventListener;

import java.util.Vector;

public class RightClickEventManager implements RightClickEventListener {

    protected Vector listener = new Vector();
    @Override
    public void openrightclickwindow(RightClickOpenEvent e) {
        for(int i=0; i < listener.size(); i++)
            ((RightClickEventListener)listener.elementAt(i)).
                    openrightclickwindow(e);
    }

    @Override
    public void buttonclickedinwindow(RightClickButtonSelectEvent e) {
        for(int i=0; i < listener.size(); i++)
            ((RightClickEventListener)listener.elementAt(i)).
                    buttonclickedinwindow(e);
    }

    public void removeListener(BlockEventListener l) {
        listener.remove(l);
    }


    public void addListener(BlockEventListener a) {
        if(! listener.contains(a))
            listener.addElement(a);
    }
}
