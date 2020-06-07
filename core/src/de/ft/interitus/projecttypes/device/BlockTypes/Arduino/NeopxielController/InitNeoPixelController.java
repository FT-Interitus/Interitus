package de.ft.interitus.projecttypes.device.BlockTypes.Arduino.NeopxielController;


import de.ft.interitus.Block.Interitus.DefaultBlockGenerator;
import de.ft.interitus.Block.Interitus.DefaultBlockUpdateGenerator;
import de.ft.interitus.Block.Interitus.DefaultWireGenerator;
import de.ft.interitus.Block.Interitus.DefaultWireNodeGenerator;
import de.ft.interitus.Block.Interitus.save.DefaultSaveBlockGenerator;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.device.DeviceTypes.Arduino.NeoPixelController.NeoPixelController;

import java.util.ArrayList;

public class InitNeoPixelController {
    static ArrayList<PlatformSpecificBlock> blocks  = new ArrayList<>();

    public static ProjectTypes init() {
        blocks.add(new SetTableColor());




        return new ProjectTypes(new NeoPixelController(),"Arduino-Neopixel-Projekt",blocks,new DefaultBlockGenerator(), new DefaultBlockUpdateGenerator(),new DefaultWireGenerator(),new DefaultWireNodeGenerator(), new DefaultSaveBlockGenerator());
    }
}
