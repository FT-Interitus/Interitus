package de.ft.robocontrol.UI.settings.subitems;

import com.kotcrab.vis.ui.building.TableBuilder;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.robocontrol.UI.settings.SettingsUI;

public class subitem3 {
    public static void add(VisTable builder) {
        builder.add(new VisLabel("Empty")).expandX().fillY();
    }
}
