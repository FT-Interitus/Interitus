package de.ft.interitus.device.BlockTypes.Arduino.Arduino;

import de.ft.interitus.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.device.DeviceTypes.Arduino.Arduino.Arduino;
import de.ft.interitus.plugin.PluginManagerHandler;
import de.ft.interitus.projecttypes.ProjektTypes;

import java.util.ArrayList;

public class InitArduino {
   static ArrayList<PlatformSpecificBlock> blocks  = new ArrayList<>();




    public static ArrayList<PlatformSpecificBlock> init() {

        PluginManagerHandler.projekttypes.add(new ProjektTypes(new Arduino(),"Arduino-Projekt"));

        blocks.add(new Wait());

        return blocks;
    }
}
