/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.utils;

import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.UI.UI;
import de.ft.interitus.datamanager.programmdata.Updater;

import java.io.IOException;

public class Restart {

    public static boolean restart() {

        String[] möglichkeiten = {"Ok", "Abbrechen"};
        final int yes = 1;
        final int no = 2;


        Dialogs.showConfirmDialog(UI.stage, "Programm neustarten", "\nDas Programm soll neugestartet werden!\nNeustart jetzt durchführen?\n",
                möglichkeiten, new Integer[]{yes, no},
                new ConfirmDialogListener<Integer>() {
                    @Override
                    public void result(Integer result) {

                        if (result == yes) {
                            String java = "";
                            if (OSChecker.isWindows()) {

                                java = System.getProperty("java.home") + "/bin/java.exe";

                            } else if (OSChecker.isUnix()) {
                                java = System.getProperty("java.home") + "/bin/java";

                            } else if (OSChecker.isMac()) {

                                java = System.getProperty("java.home") + "/bin/java";

                            }


                            try {
                                Process proc = Runtime.getRuntime().exec(java + " -jar updater.jar -r");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.exit(0);
                        }

                    }
                });


            return false;

    }
}
