/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.rightclick;

import java.util.EventListener;

public interface RightClickEventListener extends EventListener {

    void openrightclickwindow(RightClickOpenEvent e);

    void closerightclickwindow(RightClickCloseEvent e);

    void buttonclickedinwindow(RightClickButtonSelectEvent e);
}
