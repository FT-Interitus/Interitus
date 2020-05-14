package de.ft.interitus.projecttypes.device.BlockTypes.Arduino.NeopxielController;


import de.ft.interitus.plugin.PluginManagerHandler;
import de.ft.interitus.projecttypes.ProjektTypes;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.device.DeviceTypes.Arduino.NeoPixelController.NeoPixelController;

import java.util.ArrayList;

public class InitNeoPixelController {
    static ArrayList<PlatformSpecificBlock> blocks  = new ArrayList<>();

    public static ArrayList<PlatformSpecificBlock> init() {
        blocks.add(new SetTableColor());
        PluginManagerHandler.projekttypes.add(new ProjektTypes(new NeoPixelController(),"Arduino-Neopixel-Projekt",blocks));



        return blocks;
    }
}
