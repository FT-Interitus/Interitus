/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.RaspberryPi;

import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.Block.Block;
import de.ft.interitus.UI.ManualConfig.DeviceConfiguration;
import de.ft.interitus.projecttypes.ProjectFunktions;
import de.ft.interitus.projecttypes.Tool;
import de.ft.interitus.utils.ArrayList;

public class RaspberryPiFunktions implements ProjectFunktions {
    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public void switchedto() {

    }

    @Override
    public void runconfigsettings(VisTable builder, DeviceConfiguration configuration) {

    }

    @Override
    public void projectsettings(VisTable builder, Object settings) {

    }



    @Override
    public void changedrunconfig() {

    }

    @Override
    public boolean isblockconnected(Block block) {
        return false;
    }

    @Override
    public boolean isVariableAvailable(String name) {
        return false;
    }

    @Override
    public ArrayList<Tool> getProjectTools() {
        return null;
    }


}
