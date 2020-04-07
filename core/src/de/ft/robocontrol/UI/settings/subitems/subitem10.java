package de.ft.robocontrol.UI.settings.subitems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.building.TableBuilder;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import de.ft.robocontrol.Programm;
import de.ft.robocontrol.Settings;
import de.ft.robocontrol.UI.UI;
import de.ft.robocontrol.UI.settings.SettingsUI;
import de.ft.robocontrol.data.programm.Data;
import de.ft.robocontrol.data.user.DataSaver;
import de.ft.robocontrol.data.user.LoadSave;
import de.ft.robocontrol.data.user.changes.DataManager;
import de.ft.robocontrol.utils.ClearActOpenProgramm;

public class subitem10 {
   static VisTextButton delete = new VisTextButton("Daten Löschen");
    static VisTextButton export = new VisTextButton("Daten exportieren");
    public static void add(VisTable builder) {
        builder.add(new VisLabel("Daten, die im laufe der Zeit generieren.\nDarunter zählen zuletzt geöffnete Projekte, vorgenommene Einstellungen\nund konfigurierte Geräte sowie Aktivitäts-Daten.\nDiese belegen zurzeit "+ Data.getprogrammfoldersize() +" Bytes an Festplattenspeicher.")).padLeft(25).padTop(-450);


        builder.row();
        export.setDisabled(true);

        builder.add(export);
        builder.add(delete);

        delete.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) { //TODO hier dangerous settings häckchen einbauen

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

                                    Data.delete();

                                    Programm.INSTANCE.setLwjglApplication.exit();


                                }

                            }
                        });


            }
        });


    }
}
