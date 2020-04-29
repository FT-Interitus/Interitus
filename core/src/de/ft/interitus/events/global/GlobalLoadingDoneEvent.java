package de.ft.interitus.events.global;

import java.util.EventObject;

public class GlobalLoadingDoneEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GlobalLoadingDoneEvent(Object source) {
        super(source);
    }
}
