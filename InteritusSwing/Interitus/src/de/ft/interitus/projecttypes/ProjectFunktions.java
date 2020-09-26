/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes;


import de.ft.interitus.Block.Block;
import de.ft.interitus.UI.ManualConfig.DeviceConfiguration;

import java.awt.*;

public interface ProjectFunktions {
    void create();

    void update();

    void switchedto();

    void runconfigsettings(Dialog builder, DeviceConfiguration configuration);

    void projectsettings(Dialog builder,Object settings);

    /**
     * will be called by chancing the current run config to an other on
     */
    void changedrunconfig();


    boolean isblockconnected(Block block);

    boolean isVariableAvailable(String name);





}
