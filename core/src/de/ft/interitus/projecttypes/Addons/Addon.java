/*
 * Copyright (c) 2020. 
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.Addons;

import de.ft.interitus.plugin.Plugin;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.Tool;

import java.util.ArrayList;

public abstract class Addon {
    public  int ProjectTypeindex = -1;
   public   abstract String getProjectTypebyName();
    public  abstract ArrayList<PlatformSpecificBlock> getaddBlocks();
    public abstract ArrayList<Tool> getTools(); //TODO Add
    public abstract String getName();
    public abstract Plugin getPlugin();

    public final  void setProjectTypeindex(int projectTypeindex) {
        ProjectTypeindex = projectTypeindex;
    }

    public final  int getProjectTypeindex() {
        return ProjectTypeindex;
    }
}
