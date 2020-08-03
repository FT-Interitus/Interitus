/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.projectsettings.subitems;

import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.projecttypes.ProjectManager;

public class Informations {
    public static void add(VisTable builder) {
        builder.add(new VisLabel(ProjectManager.getActProjectVar().projectType.getName())).expandX().fillY(); //TODO add Stats
        builder.row();

    }
}
