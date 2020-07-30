package de.ft.interitus.events.global;

import java.util.EventObject;

public class GlobalErrorOccurredEvent extends EventObject {
    Throwable e;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GlobalErrorOccurredEvent(Object source, Throwable e) {

        super(source);
        this.e = e;
    }

    public Throwable getE() {
        return e;
    }
}
