/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.settings.subitems;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisRadioButton;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.Settings;

public class subitem12 {
    public static void add(VisTable builder) {

        VisRadioButton betatoggle = new VisRadioButton("Beta-Channel");
        VisRadioButton stabletoggle = new VisRadioButton("Stable-Channel");

        if (Settings.betaupdates) {
            betatoggle.setChecked(true);
            stabletoggle.setChecked(false);
        } else {
            betatoggle.setChecked(false);
            stabletoggle.setChecked(true);
        }

        betatoggle.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if (betatoggle.isChecked()) {
                    stabletoggle.setChecked(false);
                    Settings.betaupdates = true;
                }

                if (!betatoggle.isChecked() && !stabletoggle.isChecked()) {
                    betatoggle.setChecked(true);
                }


            }
        });


        stabletoggle.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if (stabletoggle.isChecked()) {
                    betatoggle.setChecked(false);
                    Settings.betaupdates = false;
                }

                if (!stabletoggle.isChecked() && !betatoggle.isChecked()) {
                    stabletoggle.setChecked(true);
                }

            }
        });


        builder.add(new VisLabel("Updates:")).expandX().fillY().padBottom(25).row();

        builder.add(stabletoggle).expandX().fillY().row();
        builder.add(betatoggle).expandX().fillY().row();

    }
}
