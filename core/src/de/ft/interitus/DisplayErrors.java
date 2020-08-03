/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus;

import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.UI.UI;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.global.GlobalErrorOccurredEvent;

public class DisplayErrors {
    public static Throwable error;
    public static String customErrorstring = "Ein Fehler ist aufgetreten!";
    public static String errorStringwithoutException = "";

    public static void checkerror() {
        if (error != null) {


            Dialogs.showErrorDialog(UI.stage, customErrorstring, error);

            EventVar.globalEventManager.erroroccurred(new GlobalErrorOccurredEvent(Programm.INSTANCE, error));

            error = null;
            customErrorstring = "Ein Fehler ist aufgetreten!";


        }
        if (errorStringwithoutException != "") {
            Dialogs.showErrorDialog(UI.stage, errorStringwithoutException);
            errorStringwithoutException = "";
        }
    }


}
