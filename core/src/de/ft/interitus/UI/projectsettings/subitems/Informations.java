package de.ft.interitus.UI.projectsettings.subitems;

import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.Var;

public class Informations {
    public static void add(VisTable builder) {
        builder.add(new VisLabel(Var.openprojects.get(Var.openprojectindex).projectType.getName())).expandX().fillY(); //TODO add Stats
        builder.row();

    }
}
