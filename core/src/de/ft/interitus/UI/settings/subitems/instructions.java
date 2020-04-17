package de.ft.interitus.UI.settings.subitems;

import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

public class instructions {
    public static void add(VisTable builder) {
        VisLabel label = new VisLabel("Einstellungen");
        label.setFontScale(2);

        builder.add(label).expandX().fillY().center();

    }
}
