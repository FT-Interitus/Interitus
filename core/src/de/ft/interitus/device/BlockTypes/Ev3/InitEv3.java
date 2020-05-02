package de.ft.interitus.device.BlockTypes.Ev3;

import de.ft.interitus.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.device.DeviceTypes.Ev3;
import de.ft.interitus.plugin.PluginManagerHandler;
import de.ft.interitus.projecttypes.ProjektTypes;

import java.util.ArrayList;

public class InitEv3 {
   static ArrayList<PlatformSpecificBlock> platformSpecificBlocks = new ArrayList<>();
    public static ArrayList<PlatformSpecificBlock> init() {
        PluginManagerHandler.projekttypes.add(new ProjektTypes(new Ev3(),"EV3-Projekt"));
        return platformSpecificBlocks;
    }
}
