package de.ft.interitus.events.global;

/**
 * Override only the methods you are interested in
 */
public class GlobalEventAdapter implements GlobalEventListener{
    @Override
    public void loadingdone(GlobalLoadingDoneEvent e) {

    }

    @Override
    public void loadingstart(GlobalLoadingStartEvent e) {

    }

    @Override
    public void erroroccurred(GlobalErrorOccurredEvent e) {

    }

    @Override
    public void filedroped(GlobalFileDropedEvent e, String[] filepaths) {

    }

    @Override
    public boolean closeprogramm(GlobalCloseEvent e) {
        return true;
    }
}
