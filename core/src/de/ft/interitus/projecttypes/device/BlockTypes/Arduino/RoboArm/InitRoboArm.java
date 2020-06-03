package de.ft.interitus.projecttypes.device.BlockTypes.Arduino.RoboArm;

import de.ft.interitus.Block.Interitus.DefaultBlockGenerator;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.device.DeviceTypes.Arduino.RoboArm.RoboArm;

import java.util.ArrayList;

public class InitRoboArm {
    static ArrayList<PlatformSpecificBlock> blocks  = new ArrayList<>();

    public static ProjectTypes init() {





        return new ProjectTypes(new RoboArm(),"Arduino-RoboterArm-Projekt",blocks,new DefaultBlockGenerator());
    }
}
