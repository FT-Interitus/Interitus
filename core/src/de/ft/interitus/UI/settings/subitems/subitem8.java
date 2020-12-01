/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.settings.subitems;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.Settings;

public class subitem8 {

    public static VisCheckBox snapping = new VisCheckBox("Block einrasten aktivieren");
    public static VisCheckBox activeSnapping = new VisCheckBox("Aktives Block einrasten");

    public static void add(VisTable builder) {

        snapping.setChecked(Settings.blockSnapping);
        activeSnapping.setChecked(Settings.blockActiveSnapping);


        activeSnapping.setDisabled(!snapping.isChecked());


        builder.add(new VisLabel("Block einrasten")).expandX().fillY();
        builder.row();
        builder.add(snapping).expandX().fillY().padTop(25);
        builder.row();
        builder.add(activeSnapping).expandX().fillY().padTop(10);

        snapping.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {


                Settings.blockSnapping = snapping.isChecked();

                activeSnapping.setDisabled(!snapping.isChecked());

            }
        });
        activeSnapping.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Settings.blockActiveSnapping = activeSnapping.isChecked();
            }
        });
    }
}
