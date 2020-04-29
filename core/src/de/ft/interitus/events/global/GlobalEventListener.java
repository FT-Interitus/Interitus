package de.ft.interitus.events.global;

public interface GlobalEventListener {
    public void loadingdone(GlobalLoadingDoneEvent e);
    public void loadingstart(GlobalLoadingStartEvent e);
    public void erroroccurred(GlobalErrorOccurredEvent e);
}
