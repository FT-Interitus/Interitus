package de.ft.interitus.events.rightclick;

import de.ft.interitus.UI.popup.PopupMenue;

import java.util.EventObject;

public class RightClickCloseEvent extends EventObject {
    private final PopupMenue popupMenue;

    /**
     * Constructs a prototypical Event.
     *
     * @param source     the object on which the Event initially occurred
     * @param popupMenue the popup menu which was closed
     * @throws IllegalArgumentException if source is null
     */
    public RightClickCloseEvent(Object source, PopupMenue popupMenue) {
        super(source);
        this.popupMenue = popupMenue;
    }

    public PopupMenue getPopupMenue() {
        return popupMenue;
    }
}
