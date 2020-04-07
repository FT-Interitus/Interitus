package de.ft.robocontrol.UI.settings.subitems;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.kotcrab.vis.ui.building.TableBuilder;
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

public class instructions {
    public static void add(VisTable builder) {
        VisLabel label = new VisLabel("Einstellungen");
        label.setFontScale(2);

        builder.add(label).expandX().fillY().center();

    }
}
