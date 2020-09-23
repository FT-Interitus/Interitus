/*
 * Copyright (c) 2020. 
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.Addons;

import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.plugin.Plugin;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.Tool;

import java.io.Serializable;
import de.ft.interitus.utils.ArrayList;

public interface Addon {

   public    String getProjectTypebyName();
    public   ArrayList<PlatformSpecificBlock> getaddBlocks();
    public  ArrayList<Tool> getTools(); //TODO Add
    public  String getName();
    public  Plugin getPlugin();
    public void getAddonSettings(VisTable table);
    public Serializable getAddonSettings();

    void setAddonSettings(Object object);
}
