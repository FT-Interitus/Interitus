package de.ft.interitus.UI.ManualConfig;

import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

public class RunConfigInformation {
    public static void add(VisTable builder) {
        VisLabel label = new VisLabel("Konfiguration bearbeiten oder neue erstellen");
        label.setFontScale(1);

        builder.add(label).expandX().fillY().center();

    }
}
