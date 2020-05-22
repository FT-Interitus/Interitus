package de.ft.interitus.projecttypes.device.DeviceTypes;

import de.ft.interitus.projecttypes.device.ProgrammableObjekt;

public class Ev3 implements ProgrammableObjekt {
    @Override
    public String getName() {
        return "EV3";
    }

    @Override
    public void update() {
//Bridge to Ev3 Connection 
        // TODO: 22.05.20
    }
}
