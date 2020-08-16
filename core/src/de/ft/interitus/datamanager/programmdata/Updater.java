/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.datamanager.programmdata;

import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.Programm;
import de.ft.interitus.Settings;
import de.ft.interitus.UI.Notification.Notification;
import de.ft.interitus.UI.Notification.NotificationManager;
import de.ft.interitus.UI.UI;
import de.ft.interitus.Var;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.utils.DownloadFile;
import de.ft.interitus.utils.OSChecker;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Updater {

    public static void check() {
        check(true);
    }

    private static void check(boolean userrequest) {

        try {
            String releases = "";

            try {
                releases = DownloadFile.downloadFile("https://api.github.com/repos/ft-interitus/interitus/releases"); //TODO replace with Interitus
            } catch (IOException e) {
                e.printStackTrace();
            }

            JSONArray jsonArray = new JSONArray(releases);
            JSONObject jsonObject = null;
            if (Settings.betaupdates) {

                jsonObject = jsonArray.getJSONObject(0);

            } else {
                int counter = 0;

                while (true) {
                    try {
                        jsonArray.getJSONObject(counter);
                    } catch (Exception e) {
                        break;
                    }

                    counter++;
                }


                for (int i = 0; i < counter + 1; i++) {

                    if (!jsonArray.getJSONObject(i).getBoolean("prerelease")) {

                        jsonObject = jsonArray.getJSONObject(i);
                        break;
                    }
                }

            }

            if (!jsonObject.getString("tag_name").contentEquals(Var.PROGRAMM_VERSION)) {

                if (!new File(System.getProperty("user.dir") + "").canWrite()) {
                    if (userrequest) {
                        Dialogs.showOKDialog(UI.stage, "Update fehlgeschlagen", "\nStarte Interitus mit erweiterten bzw administrativen Rechten!\nUnd versuche es erneut...\n");
                    }
                } else {
                    InputStream in = null;
                    try {
                        in = new URL("https://github.com/FT-Interitus/interitus/releases/download/" + jsonObject.getString("tag_name") + "/interitus.jar").openStream();
                        Files.copy(in, Paths.get(System.getProperty("user.dir") + "/interitus.update"), StandardCopyOption.REPLACE_EXISTING);

                        String java = "";
                        if (OSChecker.isWindows()) {

                            java = System.getProperty("java.home") + "/bin/java.exe";

                        } else if (OSChecker.isUnix()) {
                            java = System.getProperty("java.home") + "/bin/java";

                        } else if (OSChecker.isMac()) {

                            java = System.getProperty("java.home") + "/bin/java";

                        }


                        try {
                            Process proc = Runtime.getRuntime().exec(java + " -jar updater.jar -u -r");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);


                    } catch (IOException e) {
                        Dialogs.showOKDialog(UI.stage, "Update fehlgeschlagen", "\nEin unbekannter Fehler ist aufgetreten!\nVersuche es erneut mit Admin-Rechten\n");
                    }

                }


            } else {
                if (userrequest) {
                    Dialogs.showOKDialog(UI.stage, "Update", "\nZurzeit kein Update verfügbar!\n");

                }
            }

        } catch (Exception e) {


        }
    }

    public static boolean isupdateavailable() {
        try {
            String releases = "";

            try {
                releases = DownloadFile.downloadFile("https://api.github.com/repos/ft-interitus/interitus/releases");
            } catch (IOException e) {
                e.printStackTrace();
            }

            JSONArray jsonArray = new JSONArray(releases);
            JSONObject jsonObject = null;
            if (Settings.betaupdates) {

                jsonObject = jsonArray.getJSONObject(0);

            } else {
                int counter = 0;

                while (true) {
                    try {
                        jsonArray.getJSONObject(counter);
                    } catch (Exception e) {
                        break;
                    }

                    counter++;
                }


                for (int i = 0; i < counter + 1; i++) {

                    if (!jsonArray.getJSONObject(i).getBoolean("prerelease")) {

                        jsonObject = jsonArray.getJSONObject(i);
                        break;
                    }
                }

            }


            return !jsonObject.getString("tag_name").contentEquals(Var.PROGRAMM_VERSION);

        }catch (Exception e) {
            return false;
        }
    }

    public static void initprogress() {

        /**
         * If Interitus is in an Update Loop you can enable Caps-Lock and the Update will be canceled
         */
        if (!Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)) {
            if (Updater.isupdateavailable()) {
                if (!new File(System.getProperty("user.dir") + "").canWrite()) {
                    NotificationManager.sendNotification(new Notification(AssetLoader.information, "Update verfügbar", "Ein Update ist verfügbar!\nBitte starte Interitus mit Admin-Rechten!"));
                } else {
                    Updater.check(false);
                }
            }


        }else{
            Programm.logger.warning("Update Canceled");
        }
    }
}
