package de.ft.robocontrol.roboconnection;

import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.util.dialog.OptionDialogAdapter;
import de.ft.robocontrol.UI.ConnectionWindow;
import de.ft.robocontrol.UI.Devicemanagmenttab;
import de.ft.robocontrol.UI.UI;
import de.ft.robocontrol.UI.setup.SetupWindow;
import de.ft.robocontrol.data.VerbindungsSpeicher;

public class KnownDeviceManager {
    public static void addnewdevice() {

        Dialogs.showOptionDialog(UI.stage, "Neue Verbindung gefunden", "Möchtest das Gerät konfigurieren?", Dialogs.OptionDialogType.YES_NO, new OptionDialogAdapter() {
            @Override
            public void yes () {
                //Dialogs.showOKDialog(UI.stage, "Neue Verbindung Konfigurieren", "Du wirst jetzt durch einige einstellungen begleitet");

               // ConnectionWindow.tabbedPane.insert(0,  new Devicemanagmenttab(VerbindungsSpeicher.verbundungen.get(VerbindungsSpeicher.verbundungen.size()-1).name));
                SetupWindow sw=new SetupWindow();
                sw.show();
            }

            @Override
            public void no () {
                Dialogs.showOKDialog(UI.stage, "Abgebrochen", "Verbindung nicht hinzugefügt");
            }


        });
    }
}
