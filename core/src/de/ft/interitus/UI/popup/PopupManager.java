package de.ft.interitus.UI.popup;

import com.badlogic.gdx.Gdx;
import de.ft.interitus.UI.UIVar;

import de.ft.interitus.utils.ArrayList;

public class PopupManager {
    ArrayList<PopupMenue> popups = new ArrayList<>();
    private int possiblepopup = -1;

    public PopupManager(PopupMenue... popup) {
        popups.clear();
        for (int i = 0; i < popup.length; i++) {
            popups.add(popup[i]);
        }
    }

    public boolean isPopupopen() {
        boolean blub = false;
        for (int i = 0; i < popups.size(); i++) {
            if (popups.get(i).isShow()) {
                blub = true;
            }
        }
        return blub;
    }

    public int getPossiblepopup() {
        return possiblepopup;
    }

    public void setPossiblepopup(int possiblepopup) {
        this.possiblepopup = possiblepopup;
    }

    public void draw() {
        if (Gdx.input.isButtonPressed(1)) {
            if (!UIVar.isdialogeopend) {
                for (int i = 0; i < popups.size(); i++) {
                    if (popups.get(i) != popups.get(possiblepopup)) {
                        popups.get(i).setShow(false);
                    }
                }
                popups.get(possiblepopup).setShow(true);
            }
        }
        if (possiblepopup != -1) {
            popups.get(possiblepopup).rechtsKlickControlle();
            popups.get(possiblepopup).draw();
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
