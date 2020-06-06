package de.ft.interitus.events.UI;

import java.util.EventObject;

public class UIOpenSettingsEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public UIOpenSettingsEvent(Object source) {
        super(source);
    }
}
