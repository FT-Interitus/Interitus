package de.ft.interitus;

import com.badlogic.gdx.Gdx;
import de.ft.interitus.Block.BlockVar;
import de.ft.interitus.UI.inputfields.check.Check;
import de.ft.interitus.UI.popup.PopupManager;
import de.ft.interitus.UI.popup.PopupMenue;

public class RechtsKlick {
    public static Check check = new Check();

    public static int mouseoverblockindex;
    public static PopupManager popupmanager = new PopupManager();

    public static void Init() {
        popupmanager.addPopup(new PopupMenue("ein popup"));
        popupmanager.addPopup(new PopupMenue("Löschen", "Fixieren", "Umbenennen", "Befreien"));
        popupmanager.addPopup(new PopupMenue("Löschen", "Node einfügen"));
    }

    public static void Rechtsklickupdate() {
        boolean mob = false;
        boolean mow = false;

        if(!Var.isdialogeopend) {

            if (Gdx.input.isButtonPressed(1)) {
                for (int i = 0; i < BlockVar.visibleblocks.size(); i++) {

                    if (check.isMouseover(BlockVar.visibleblocks.get(i).getX(), BlockVar.visibleblocks.get(i).getY(), BlockVar.visibleblocks.get(i).getW(), BlockVar.visibleblocks.get(i).getH()) && BlockVar.mousehoveredwire == null) {
                        mouseoverblockindex = i;
                        mob = true;

                    }
                }

                if (BlockVar.mousehoveredwire != null) {
                    mow = true;

                }


                if (mob == false) { //Ist der Mauszeiger auf einem Block
                    if (mow) { //Ist die Maus auf einer Wire
                        //ProgrammingSpace.popupmanager.getPopup(1).setShow(false);
                        popupmanager.setPossiblepopup(2);
                    } else {
                        mouseoverblockindex = -1;
                        //ProgrammingSpace.popupmanager.getPopup(1).setShow(false);
                        popupmanager.setPossiblepopup(0);
                    }
                } else {


                    //ProgrammingSpace.popupmanager.getPopup(0).setShow(false);
                    popupmanager.setPossiblepopup(1);

                }

            }
        }

    }
}


