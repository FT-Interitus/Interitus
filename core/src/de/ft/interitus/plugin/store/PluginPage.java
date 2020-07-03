package de.ft.interitus.plugin.store;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.settings.SettingsUI;
import de.ft.interitus.UI.settings.subitems.subitem17;
import de.ft.interitus.data.programm.Data;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.plugin.store.PluginInstalledNewEvent;
import de.ft.interitus.plugin.PluginManagerHandler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystemException;

public class PluginPage {


    PluginPage INSTANCE = this;

    /***
     * Shows the information of the Plugin if you click on it
     * @param table
     * @param Storeentry
     */
    public static void add(final VisTable table, final StorePluginEntry Storeentry, final float scrollpercent) {

        table.clearChildren();


        System.out.println(Storeentry.getId());
        VisTextButton back = new VisTextButton("<- Zurück", "blue");

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SettingsUI.SelectedItem = 12;
                table.clearChildren();
                subitem17.add(table, scrollpercent);
            }
        });

        table.add(back).padTop(-540).padLeft(-360);
        table.add(subitem17.pluginimage.get(Storeentry.id - 1)).padTop(-360).padLeft(-340);
        table.add(new VisLabel(Storeentry.getName())).padTop(-430).padLeft(-140);

        final VisTextButton download = new VisTextButton("Installieren", "blue");

        boolean isinstalled = false;
        for (int i = 0; i < PluginManagerHandler.registeredplugins.size(); i++) {
            if (PluginManagerHandler.registeredplugins.get(i).getName().toLowerCase().contains(Storeentry.getName().toLowerCase())) {
                isinstalled = true;


                break;
            }
        }
        if (isinstalled) {
            if (!new File(System.getProperty("user.home") + "/"+ Data.foldername+"/plugins/" + Storeentry.getName() + ".itpl").exists()) {
                download.setText("Programm bitte neustarten");
                download.setDisabled(true);
            } else {
                download.setText("Deinstallieren");
            }

        } else {
            if (new File(System.getProperty("user.home") + "/"+Data.foldername+"/plugins/" + Storeentry.getName() + ".itpl").exists()) {
                download.setText("Programm bitte neustarten");
                download.setDisabled(true);
            }
        }


        download.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if (!download.getText().toString().contains("Deinstallieren")) {
                    download.setText("Bitte Warten");
                    download.setDisabled(true);

                    try (BufferedInputStream in = new BufferedInputStream(new URL(Storeentry.path).openStream());
                         FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.home") + "/"+Data.foldername+"/plugins/" + Storeentry.getName() + ".jar")) {
                        byte[] dataBuffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                            fileOutputStream.write(dataBuffer, 0, bytesRead);
                        }
                    } catch (IOException e) {
                        download.setText("Installieren");
                        download.setDisabled(true);
                        e.printStackTrace();
                        DisplayErrors.error = e;
                        return;
                    }

                    EventVar.pluginStoreEventManager.plugininstalled(new PluginInstalledNewEvent(this, Storeentry));

                    download.setText("Programm bitte neustarten");
                    download.setDisabled(true);
                    String[] möglichkeiten = {"OK", "Abbrechen"};
                    final int nothing = 1;
                    final int everything = 2;


                    Dialogs.showConfirmDialog(UI.stage, "Plugin Installation", "\nUm das Plugin zu aktivieren musst du das Programm jetzt neustarten!\nWillst du das jetzt tun?\n",
                            möglichkeiten, new Integer[]{nothing, everything},
                            new ConfirmDialogListener<Integer>() {
                                @Override
                                public void result(Integer result) {
                                    if (result == nothing) {


                                        //TODO restart Programm
                                    }

                                    if (result == everything) {

                                    }


                                }
                            });
                } else {
                    File oldplugin = new File(System.getProperty("user.home") + "/"+Data.foldername+"/plugins/" + Storeentry.getName() + ".jar");


                    // TODO: 21.05.20 Disable Plugin vor dem löschen das Windows sich nicht beschwert -> Exception
                    if (oldplugin.delete()) {
                        download.setText("Programm bitte neustarten");
                        download.setDisabled(true);
                        String[] möglichkeiten = {"Ja", "Nein"};
                        final int nothing = 1;
                        final int everything = 2;
                        Dialogs.showConfirmDialog(UI.stage, "Plugin Deinstallation", "\nUm das Plugin zu deaktivieren musst du das Programm jetzt neustarten!\nWillst du das jetzt tun?\n",
                                möglichkeiten, new Integer[]{nothing, everything},
                                new ConfirmDialogListener<Integer>() {
                                    @Override
                                    public void result(Integer result) {
                                        if (result == nothing) {


                                            //TODO restart Programm
                                        }

                                        if (result == everything) {

                                        }


                                    }
                                });
                    } else {
                        DisplayErrors.error = new FileSystemException("Could not delete Plugin. Is the Plugin Folder protected?");
                        DisplayErrors.customErrorstring = "Das Plugin konnte nicht deinstalliert werden.";
                    }


                }


            }
        });

        table.add(download).padTop(-450).padLeft(-00).padRight(-300);

        table.add(new VisLabel(Storeentry.getDescription())).padTop(-350).padLeft(-90).padRight(-250);
        table.row();
        table.add(new VisLabel(Storeentry.detailed_description)).padLeft(-220).padTop(-200).padBottom(-50).padRight(-300).align(Align.bottomLeft);


    }
}
