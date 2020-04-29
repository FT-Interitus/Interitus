package de.ft.interitus.events.global;

import java.util.EventObject;

public class GlobalErrorOccurredEvent extends EventObject {
    Exception e;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GlobalErrorOccurredEvent(Object source, Exception e) {

        super(source);
        this.e = e;
    }

    public Exception getE() {
        return e;
    }
}
