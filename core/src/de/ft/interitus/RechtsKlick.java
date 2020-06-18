package de.ft.interitus;

import com.badlogic.gdx.Gdx;
import de.ft.interitus.UI.UIElements.check.Check;
import de.ft.interitus.UI.popup.PopupManager;
import de.ft.interitus.UI.popup.PopupMenue;
import de.ft.interitus.projecttypes.ProjectManager;

public class RechtsKlick {
    public static Check check = new Check();
    static boolean mob = false;
   static boolean mow = false;
    public static int mouseoverblockindex;
    public static PopupManager popupmanager = new PopupManager();

    public static void Init() {
        popupmanager.addPopup(new PopupMenue("ein popup"));
        popupmanager.addPopup(new PopupMenue("Löschen", "Fixieren", "Umbenennen", "Befreien"));
        popupmanager.addPopup(new PopupMenue("Löschen", "Node einfügen"));
    }

    public static void Rechtsklickupdate() {


        if(!Var.isdialogeopend) {

            if (Gdx.input.isButtonPressed(1)) {
                for (int i = 0; i < ProjectManager.getActProjectVar().visibleblocks.size(); i++) {

                    if (check.isMouseover(ProjectManager.getActProjectVar().visibleblocks.get(i).getX(), ProjectManager.getActProjectVar().visibleblocks.get(i).getY(), ProjectManager.getActProjectVar().visibleblocks.get(i).getW(), ProjectManager.getActProjectVar().visibleblocks.get(i).getH()) && ProjectManager.getActProjectVar().mousehoveredwire == null) {
                        mouseoverblockindex = i;
                        mob = true;

                    }
                }

                if (ProjectManager.getActProjectVar().mousehoveredwire != null) {
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


