package de.ft.interitus.input.popup;

import java.util.ArrayList;

public class PopupManager {
    ArrayList<PopupMenue>popups=new ArrayList<>();
    private int shownpopup=-1;

    public PopupManager(PopupMenue... popup){
        popups.clear();
        for(int i=0;i<popup.length;i++){
            popups.add(popup[i]);
        }
    }

    public void draw(){
for(int i=0;i<popups.size();i++){
    popups.get(i).draw();
    popups.get(i).rechtsKlickControlle();
}
    }
    public void setPopups(PopupMenue... popup){
        popups.clear();
        for(int i=0;i<popup.length;i++){
            popups.add(popup[i]);
        }
    }
    public void addPopup(PopupMenue newpopup){
        popups.add(newpopup);
    }
    public PopupMenue getPopup(int popup){
        return popups.get(popup);
    }

}
