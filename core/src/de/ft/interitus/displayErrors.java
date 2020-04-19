package de.ft.interitus;

import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.UI.UI;

public class displayErrors {
   public static Exception error;

   public static void checkerror() {
       if(error!=null) {


           Dialogs.showErrorDialog(UI.stage,"Ein Fehler ist aufgetreten!",error);

           error = null;


       }
   }


}
