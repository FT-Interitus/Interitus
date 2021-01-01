/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.ZoomUi;

import de.ft.interitus.UI.Viewport;
import de.ft.interitus.events.UI.UIZoomEvent;

public class ZoomUI {
    public ZoomUI(){

        Viewport.zoomEvent.addListener(new UIZoomEvent() {
            @Override
            public void zoomStart() {
                fadeIn();
            }
        });

    }

    public void fadeIn(){

    }
    public void fadeOut(){

    }
}
