/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.projectsettings.subitems;

import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.projecttypes.ProjectManager;

public class Information {
    public static void add(VisTable builder) {
        builder.add(new VisLabel(ProjectManager.getActProjectVar().projectType.getName())).expandX().fillY().row();
        builder.add(new VisLabel("Compiler-Version: " + ProjectManager.getActProjectVar().projectType.getCompiler().getCompilerVersion())).expandX().fillY().padTop(10);

        builder.row();

    }
}
