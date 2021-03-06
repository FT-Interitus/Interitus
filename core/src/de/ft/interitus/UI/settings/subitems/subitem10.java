/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.settings.subitems;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import de.ft.interitus.UI.UI;
import de.ft.interitus.datamanager.programmdata.Data;

public class subitem10 {
    static VisTextButton delete = new VisTextButton("Daten Löschen");
    static VisTextButton export = new VisTextButton("Daten exportieren");

    public static void add(VisTable builder) {
        builder.add(new VisLabel("Daten, die sich im Laufe der Zeit generieren.\nDarunter zählen zuletzt geöffnete Projekte, vorgenommene Einstellungen\nund Plugins sowie Aktivitäts-Daten.\nDiese belegen zurzeit " + Data.getprogrammfoldersize() + " Bytes an Festplattenspeicher.")).padLeft(25).padTop(-450);


        builder.row();
        export.setDisabled(true); //TODO include
        delete.setDisabled(true);

        builder.add(export).padLeft(-250);
        builder.add(delete).padLeft(-250);

        delete.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                String[] möglichkeiten = {"Nein", "Ja"};


                final int nothing = 1;
                final int everything = 2;


                //confirmdialog may return result of any type, here we are just using ints
                Dialogs.showConfirmDialog(UI.stage, "Programm-Daten Löschen", "\nACHTUNG! Das wird alle Einstellungs-, alle Geräts- und alle Log- Daten löschen. Diese können nicht wiederhergestellt werden! \n",
                        möglichkeiten, new Integer[]{nothing, everything},
                        new ConfirmDialogListener<Integer>() {
                            @Override
                            public void result(Integer result) {
                                if (result == nothing) {

                                }

                                if (result == everything) {


                                }

                            }
                        });


            }
        });


    }
}
