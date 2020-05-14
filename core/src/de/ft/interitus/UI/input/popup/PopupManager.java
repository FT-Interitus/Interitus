package de.ft.interitus.UI.input.popup;

import java.util.ArrayList;

public class PopupManager {
    ArrayList<PopupMenue> popups = new ArrayList<>();
    private int possiblepopup = -1;

    public PopupManager(PopupMenue... popup) {
        popups.clear();
        for (int i = 0; i < popup.length; i++) {
            popups.add(popup[i]);
        }
    }

    public int getPossiblepopup() {
        return possiblepopup;
    }

    public void setPossiblepopup(int possiblepopup) {
        this.possiblepopup = possiblepopup;
    }

    public void draw() {
        if (possiblepopup != -1) {
            popups.get(possiblepopup).draw();
            popups.get(possiblepopup).rechtsKlickControlle();
        }
    }

    public void setPopups(PopupMenue... popup) {
        popups.clear();
        for (int i = 0; i < popup.length; i++) {
            popups.add(popup[i]);
        }
    }

    public void addPopup(PopupMenue newpopup) {
        popups.add(newpopup);
    }

    public PopupMenue getPopup(int popup) {
        return popups.get(popup);
    }

}
