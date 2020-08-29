/*
 * Copyright (c) 2020. 
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.Addons;

import de.ft.interitus.plugin.Plugin;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.Tool;

import java.util.ArrayList;

public interface Addon {

   public    String getProjectTypebyName();
    public   ArrayList<PlatformSpecificBlock> getaddBlocks();
    public  ArrayList<Tool> getTools(); //TODO Add
    public  String getName();
    public  Plugin getPlugin();

}
