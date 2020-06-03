package de.ft.interitus.events.global;

import java.util.EventObject;

public class GlobalFileDropedEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GlobalFileDropedEvent(Object source) {
        super(source);
    }
}
