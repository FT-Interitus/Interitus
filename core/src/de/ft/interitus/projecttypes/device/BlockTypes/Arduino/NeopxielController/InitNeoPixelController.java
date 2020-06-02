package de.ft.interitus.projecttypes.device.BlockTypes.Arduino.NeopxielController;


import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.device.DeviceTypes.Arduino.NeoPixelController.NeoPixelController;

import java.util.ArrayList;

public class InitNeoPixelController {
    static ArrayList<PlatformSpecificBlock> blocks  = new ArrayList<>();

    public static ProjectTypes init() {
        blocks.add(new SetTableColor());




        return new ProjectTypes(new NeoPixelController(),"Arduino-Neopixel-Projekt",blocks);
    }
}
