/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin;


import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.UI.Notification.Notification;
import de.ft.interitus.UI.Notification.NotificationManager;
import de.ft.interitus.UI.UI;
import de.ft.interitus.loading.AssetLoader;
import org.json.JSONObject;

public class PluginInstallManager {

    protected static void startInstallPlugin(final String url, final String jsonInformation) {


        CheckInstallPermission(url, jsonInformation);

    }


    private static void proceedInstallation(String url, String jsonInformation) {
        JSONObject jsonObject = new JSONObject(jsonInformation);


        Notification notification = new Notification(AssetLoader.information, "Plugin herunterladen..", "Das Plugin " + jsonObject.getString("name") + "\nwird heruntergeladen");
        notification.setCloseable(false);
        notification.setProgressbarvalue(0);
        NotificationManager.sendNotification(notification);

        try {
            PluginDownloader.downloadPlugin(url, jsonObject.getString("name"), notification);
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            notification.setTitle("Fehler");
            notification.setProgressbarvalue(-1);
            notification.setCloseable(true);
            notification.setStayalive(false);
            notification.setMessage("Fehler beim Herunterladen");
        }

    }


    private static void CheckInstallPermission(final String url, final String jsonInformation) {
        JSONObject jsonObject = new JSONObject(jsonInformation);

        String[] obligations = {"Ja", "Nein"};
        final int yes = 1;
        final int no = 2;


        Dialogs.showConfirmDialog(UI.stage, "Plugin installieren?", "\nSicher, dass du das Plugin " + jsonObject.getString("name") + " installieren möchtest?\nEin Third-Party-Plugin kann unter Umständen Sicherheitsrisiken mit sich bringen!\n",
                obligations, new Integer[]{yes, no},
                result -> {

                    if (result == yes) {
                        Thread installPlugin = new Thread(() -> {
                            try {
                                proceedInstallation(url, jsonInformation);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        installPlugin.start();

                    }

                });
    }

}
