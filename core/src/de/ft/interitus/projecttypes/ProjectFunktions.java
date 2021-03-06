/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes;

import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.Block.Block;
import de.ft.interitus.UI.ManualConfig.DeviceConfiguration;

import de.ft.interitus.utils.ArrayList;

public interface ProjectFunktions {
    void create();

    void update();

    /**
     * If the user Switched to a Project with this Type
     */
    void switchedTo();

    void runconfigsettings(VisTable builder, DeviceConfiguration configuration);

    void projectsettings(VisTable builder,Object settings);

    /**
     * will be called by chancing the current run config to an other on
     */
    void changedrunconfig();


    boolean isblockconnected(Block block);

    boolean isVariableAvailable(String name);

    ArrayList<Tool> getProjectTools();





}
