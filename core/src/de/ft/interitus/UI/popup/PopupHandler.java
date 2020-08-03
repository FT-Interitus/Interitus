/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.popup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import de.ft.interitus.Programm;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.rightclick.RightClickOpenRequestEvent;

import java.util.ArrayList;

public class PopupHandler {


    private static PopupMenue highestPriority = null;

    public static void drawPopUp() {

        if (!UIVar.isdialogeopend) {

            if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {

                    highestPriority = null;
                    ArrayList<PopupMenue> requestedMenues = EventVar.rightClickEventManager.openrequest(new RightClickOpenRequestEvent(Programm.INSTANCE), Gdx.input.getX(), Gdx.input.getY());

                    for (PopupMenue popupMenu : requestedMenues) {
                        if (popupMenu != null) {

                            if (highestPriority == null) {
                                highestPriority = popupMenu;
                                continue;
                            }

                            if (highestPriority.getPriority() < popupMenu.getPriority()) {
                                highestPriority = popupMenu;

                            }

                        }
                    }

                    if(highestPriority!=null){
                        highestPriority.setShow(true);
                        highestPriority.setBounds(Gdx.input.getX(),Gdx.input.getY()); //TODO Bounds stimmen nicht
                    }

            }

            if(highestPriority!=null&&highestPriority.isShow()) {
                highestPriority.draw();

            }else{
                if(highestPriority!=null) {

                    highestPriority = null;
                }
            }
        }


    }


}
