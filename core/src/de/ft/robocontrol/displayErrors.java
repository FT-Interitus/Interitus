package de.ft.robocontrol;

import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.robocontrol.UI.UI;

public class displayErrors {
   public static Exception error;

   public static void checkerror() {
       if(error!=null) {
           //TODO popup

           Dialogs.showErrorDialog(UI.stage,"Ein Fehler ist aufgetreten!",error);

           error = null;


       }
   }


}
