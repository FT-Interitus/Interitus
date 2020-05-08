package de.ft.interitus.device.BlockTypes.Arduino.RoboArm;

import de.ft.interitus.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.device.DeviceTypes.Arduino.RoboArm.RoboArm;
import de.ft.interitus.plugin.PluginManagerHandler;
import de.ft.interitus.projecttypes.ProjektTypes;

import java.util.ArrayList;

public class InitRoboArm {
    static ArrayList<PlatformSpecificBlock> blocks  = new ArrayList<>();

    public static ArrayList<PlatformSpecificBlock> init() {

        PluginManagerHandler.projekttypes.add(new ProjektTypes(new RoboArm(),"Arduino-RoboterArm-Projekt",blocks));



        return blocks;
    }
}
