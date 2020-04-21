package de.ft.interitus.input.popup;

import java.util.ArrayList;

public class PopupManager {
    private ArrayList<PopupMenue>popups=new ArrayList<>();
    private int shownpopup=-1;
    private int possiblepopup=-1;

    public PopupManager(PopupMenue... popup){
        popups.clear();
        for(int i=0;i<popup.length;i++){
            popups.add(popup[i]);
        }
    }

    public void draw(){

        if(shownpopup!=-1) {
            popups.get(shownpopup).draw();
            
        }
}

public void showPopup(int _shownpopup){
        shownpopup=_shownpopup;

    for(int i=0;i<popups.size();i++){
        if(i==shownpopup){
            popups.get(i).setShow(true);
        }else{
            popups.get(i).setShow(false);
        }
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
