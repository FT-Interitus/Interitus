/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.events.UI;

import de.ft.interitus.UI.UIElements.UIElements.UIElement;

public abstract class UIToggleEvent {
    abstract public void toggled(boolean enabled, UIElement sender);

}
