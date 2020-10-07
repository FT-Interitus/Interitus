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
    private static Throwable lastError = null;

    public static void checkerror() {

        try {


            if (error != null && (lastError == null || !lastError.getMessage().contentEquals(error.getMessage()))) {


                Dialogs.showErrorDialog(UI.stage, customErrorstring, error);

                EventVar.globalEventManager.erroroccurred(new GlobalErrorOccurredEvent(Program.INSTANCE, error));
                lastError = error;
                error = null;
                customErrorstring = "Ein Fehler ist aufgetreten!";


            }

            if (!errorStringwithoutException.equals("")) {
                Dialogs.showErrorDialog(UI.stage, errorStringwithoutException);
                errorStringwithoutException = "";

            }
        }catch (Exception e) {

            error = null;
            lastError = null;
            errorStringwithoutException = "";
        }
    }

}
