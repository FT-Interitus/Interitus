package de.ft.interitus.events.global;

public interface GlobalEventListener {
    void loadingdone(GlobalLoadingDoneEvent e);

    void loadingstart(GlobalLoadingStartEvent e);

    void erroroccurred(GlobalErrorOccurredEvent e);
}
