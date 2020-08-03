/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.rightclick;

import de.ft.interitus.UI.popup.PopupMenue;

import java.util.EventListener;

public interface RightClickEventListener extends EventListener {

    /***
     *
     *
     *
     * @param e
     * @param Pos_X Have to be unprojected if you need
     * @param Pos_Y Have to be unprojected if you need
     * @return the Menu which you want to open
     */
    PopupMenue openrequest(RightClickOpenRequestEvent e,float Pos_X,float Pos_Y);

}
