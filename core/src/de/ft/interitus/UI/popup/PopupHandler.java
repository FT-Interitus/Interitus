/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.popup;

import com.badlogic.gdx.Input;
import de.ft.interitus.Program;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.WindowAPI;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.rightclick.RightClickOpenRequestEvent;
import de.ft.interitus.events.rightclick.RightClickPerformActionEvent;
import de.ft.interitus.utils.ArrayList;

public class PopupHandler {


    private static PopupMenue highestPriority = null;

    public static void drawPopUp() {

        if (!UIVar.isdialogeopend) {

            if (WindowAPI.isButtonJustPressed(Input.Buttons.RIGHT)) {

                highestPriority = null;
                ArrayList<PopupMenue> requestedMenues = EventVar.rightClickEventManager.openrequest(new RightClickOpenRequestEvent(Program.INSTANCE), WindowAPI.getX(), WindowAPI.getY());

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

                if (highestPriority != null) {
                    highestPriority.setShow(true);
                    highestPriority.setBounds(WindowAPI.getX(), WindowAPI.getHeight() - WindowAPI.getY());
                }

            }

            if (highestPriority != null && highestPriority.isShow()) {
                highestPriority.draw();


            } else {


                if (highestPriority != null) {
                    if (highestPriority.ispressed != -1) {

                        EventVar.rightClickEventManager.performAction(new RightClickPerformActionEvent(Program.INSTANCE), highestPriority, highestPriority.ispressed);
                        highestPriority.ispressed = -1;
                    }

                    highestPriority = null;
                }
            }
        }


    }


}
