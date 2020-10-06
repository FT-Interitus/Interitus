/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes;

import de.ft.interitus.projecttypes.Addons.Addon;

public class Tools {
    public static void update() {
        ProjectManager.getActProjectVar().tools.clear();
        if(ProjectManager.getActProjectVar().projectType.getProjectFunktions().getProjectTools()!=null) {
            ProjectManager.getActProjectVar().tools.addAll(ProjectManager.getActProjectVar().projectType.getProjectFunktions().getProjectTools());
        }
        for(Addon addon:ProjectManager.getActProjectVar().enabledAddons) {
            if(addon.getTools()!=null) {

                ProjectManager.getActProjectVar().tools.addAll(addon.getTools());
            }
        }


    }
}
