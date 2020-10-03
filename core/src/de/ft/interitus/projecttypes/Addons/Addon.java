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

       String getProjectTypebyName();
       ArrayList<PlatformSpecificBlock> getaddBlocks();
      ArrayList<Tool> getTools();
      String getName();
      Plugin getPlugin();
     void getAddonSettings(VisTable table);
     Serializable getAddonSettings();

    void setAddonSettings(Object object);
}
