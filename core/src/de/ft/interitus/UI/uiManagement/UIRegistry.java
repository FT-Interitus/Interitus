/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.uiManagement;

import de.ft.interitus.UI.UIContents.BlockSettings;
import de.ft.interitus.UI.UIContents.ZoomUi.ZoomUI;

public class UIRegistry {
    public final ZoomUI zoomUi = new ZoomUI();
    final BlockSettings blockSettings = new BlockSettings();

    public void init() {//This is also the draw order!!!!!!
        UIManager.addUI(zoomUi);
        UIManager.addUI(blockSettings);

    }
}
