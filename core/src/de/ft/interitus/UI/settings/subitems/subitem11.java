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

public class subitem11 {
   private static final VisCheckBox disableblockgrayout = new VisCheckBox("Block-Ausgrauen deaktivieren");

    public static void add(VisTable builder) {

        disableblockgrayout.setChecked(Settings.disableblockgrayout);

        builder.add(disableblockgrayout).expandX().fillY();

        disableblockgrayout.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                Settings.disableblockgrayout= disableblockgrayout.isChecked();

            }
        });




    }
}
