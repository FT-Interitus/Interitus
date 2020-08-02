package de.ft.interitus.projecttypes;

import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.UI.ManualConfig.DeviceConfiguration;

public interface ProjectFunktions {
    void create();
    void update();
    void runconfigsettings(VisTable builder, DeviceConfiguration  configuration);
}
