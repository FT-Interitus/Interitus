package de.ft.interitus.projecttypes.device.BlockTypes.Arduino.Arduino;

import de.ft.interitus.plugin.PluginManagerHandler;
import de.ft.interitus.projecttypes.ProjektTypes;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.device.DeviceTypes.Arduino.Arduino.Arduino;

import java.util.ArrayList;

public class InitArduino {
   static ArrayList<PlatformSpecificBlock> blocks  = new ArrayList<>();




    public static ArrayList<PlatformSpecificBlock> init() {


        blocks.add(new Wait());



        PluginManagerHandler.projekttypes.add(new ProjektTypes(new Arduino(),"Arduino-Projekt",blocks));



        return blocks;
    }
}
