package de.ft.interitus.events.UI;


import java.util.Vector;

public class UIEventManager implements UIEventListener {
    protected Vector listener = new Vector();


    public void removeListener(UIEventListener l) {
        listener.remove(l);
    }


    public void addListener(UIEventListener a) {
        if (!listener.contains(a))
            listener.addElement(a);
    }

    @Override
    public void UILoadEvent(UILoadEvent e) {
        for (int i = 0; i < listener.size(); i++)
            ((UIEventListener) listener.elementAt(i)).
                    UILoadEvent(e);
    }
}
