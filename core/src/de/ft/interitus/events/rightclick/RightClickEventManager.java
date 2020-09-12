/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.rightclick;

import de.ft.interitus.UI_old.popup.PopupMenue;

import java.util.ArrayList;
import java.util.Vector;

public class RightClickEventManager  {
    protected Vector listener = new Vector();
    private final ArrayList<PopupMenue> requests = new ArrayList<>();

    public void removeListener(RightClickEventListener l) {
        listener.remove(l);
    }


    public void addListener(RightClickEventListener a) {
        if (!listener.contains(a))
            listener.addElement(a);
    }


    public ArrayList<PopupMenue> openrequest(RightClickOpenRequestEvent e, float Pos_X, float Pos_Y) {

        requests.clear();

        for (int i = 0; i < listener.size(); i++)
            requests.add(((RightClickEventListener) listener.elementAt(i)).openrequest(e, Pos_X, Pos_Y));

        return requests;
    }

    
    public void performAction(RightClickPerformActionEvent e, PopupMenue popupMenue, int Buttonindex) {

        for (int i = 0; i < listener.size(); i++)
           ((RightClickEventListener) listener.elementAt(i)).performAction(e, popupMenue, Buttonindex);
    }
}
