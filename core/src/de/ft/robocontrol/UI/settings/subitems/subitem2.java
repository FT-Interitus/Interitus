package de.ft.robocontrol.UI.settings.subitems;

import com.kotcrab.vis.ui.building.TableBuilder;
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.robocontrol.UI.settings.SettingsUI;

public class subitem2 {
    public static void add(VisTable builder) {
        builder.add(SettingsUI.darktoggle).expandX().fillY();

    }
}
