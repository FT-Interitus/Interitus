/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.rightclick;

import de.ft.interitus.UI.popup.PopupMenue;

import java.util.EventListener;

public interface RightClickEventListener extends EventListener {

    PopupMenue openrequest(RightClickOpenRequestEvent e,float Pos_X,float Pos_Y);

}
