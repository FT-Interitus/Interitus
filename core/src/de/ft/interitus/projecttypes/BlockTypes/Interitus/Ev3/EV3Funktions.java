/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3;

import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Devices;
import de.ft.interitus.UI.ManualConfig.DeviceConfiguration;
import de.ft.interitus.deviceconnection.ev3connection.ConnectionHandle;
import de.ft.interitus.deviceconnection.ev3connection.Device;
import de.ft.interitus.deviceconnection.ev3connection.usb.USBConnectionHandle;
import de.ft.interitus.projecttypes.ProjectFunktions;

import java.util.ArrayList;

public class EV3Funktions implements ProjectFunktions {
   public ArrayList<Device> ev3devices = new ArrayList<>();
    public  ConnectionHandle usbConnectionHandle = new USBConnectionHandle();

    @Override
    public void create() {

    }

    @Override
    public void update() {
        usbConnectionHandle.update();


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


}
