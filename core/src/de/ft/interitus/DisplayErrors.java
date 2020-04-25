package de.ft.interitus;

import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.UI.UI;

public class DisplayErrors {
   public static Exception error;
   public static String customErrorstring = "Ein Fehler ist aufgetreten!";

   public static void checkerror() {
       if(error!=null) {


           Dialogs.showErrorDialog(UI.stage,customErrorstring,error);

           error = null;
           customErrorstring = "Ein Fehler ist aufgetreten!";


       }
   }


}
