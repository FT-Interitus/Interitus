package de.ft.interitus.events.rightclick;

import de.ft.interitus.input.popup.PopupMenue;

import java.util.EventObject;

public class RightClickOpenEvent extends EventObject {
    PopupMenue popupMenue;
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public RightClickOpenEvent(Object source, PopupMenue popupMenue) {
        super(source);
        this.popupMenue = popupMenue;
    }

    public PopupMenue getPopupMenue() {
        return popupMenue;
    }
}
