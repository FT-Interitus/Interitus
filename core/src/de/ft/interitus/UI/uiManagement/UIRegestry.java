/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.uiManagement;

import de.ft.interitus.UI.UIContents.BlockSettings;
import de.ft.interitus.UI.UIContents.ZoomUi.ZoomUI;
import de.ft.interitus.events.UI.UIZoomEvent;

public class UIRegestry {
    final UI zoomUi = new ZoomUI();
    final UI blockSettings = new BlockSettings();

    public void init(){//This is also the draw order!!!!!!
        UIManager.addUI(zoomUi);
        UIManager.addUI(blockSettings);

    }
}
