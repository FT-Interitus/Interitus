package de.ft.interitus.projecttypes.device.BlockTypes.Arduino.NeopxielController;


import de.ft.interitus.Block.Interitus.*;
import de.ft.interitus.Block.Interitus.save.DefaultSaveBlockGenerator;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.device.DeviceTypes.Arduino.NeoPixelController.NeoPixelController;

import java.util.ArrayList;

public class InitNeoPixelController {
    static ArrayList<PlatformSpecificBlock> blocks = new ArrayList<>();
    static ProjectTypes type = new ProjectTypes(ProgrammingSpace.nativ, new NeoPixelController(), "Arduino-Neopixel-Projekt", blocks, new DefaultBlockGenerator(), new DefaultBlockUpdateGenerator(), new DefaultWireGenerator(), new DefaultWireNodeGenerator(), new DefaultSaveBlockGenerator(), new DefaultBlockVarGenerator());

    public static ProjectTypes init() {
        blocks.add(new SetTableColor(type));


        return type;
    }
}
