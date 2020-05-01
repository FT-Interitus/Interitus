package de.ft.interitus.device.BlockTypes.Arduino.NeopxielController;


import de.ft.interitus.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.device.DeviceTypes.Arduino.NeoPixelController.NeoPixelController;
import de.ft.interitus.plugin.PluginManagerHandler;
import de.ft.interitus.projecttypes.ProjektTypes;

import java.util.ArrayList;

public class InitNeoPixelController {
    static ArrayList<PlatformSpecificBlock> blocks  = new ArrayList<>();

    public static ArrayList<PlatformSpecificBlock> init() {

        PluginManagerHandler.projekttypes.add(new ProjektTypes(new NeoPixelController(),"Arduino"));

        blocks.add(new SetTableColor());

        return blocks;
    }
}
