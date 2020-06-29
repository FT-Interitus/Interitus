package de.ft.interitus.projecttypes.device.BlockTypes.Arduino.Arduino;

import de.ft.interitus.Block.Interitus.*;
import de.ft.interitus.Block.Interitus.save.DefaultSaveBlockGenerator;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.device.DeviceTypes.Arduino.Arduino.Arduino;

import java.util.ArrayList;

public class InitArduino {
   static ArrayList<PlatformSpecificBlock> blocks  = new ArrayList<>();
    static ProjectTypes arduino = new ProjectTypes(ProgrammingSpace.nativ,new Arduino(),"Arduino-Projekt",blocks, new DefaultBlockGenerator(),new DefaultBlockUpdateGenerator(),new DefaultWireGenerator(),new DefaultWireNodeGenerator(),new DefaultSaveBlockGenerator(), new DefaultBlockVarGenerator());



    public static ProjectTypes init() {


        blocks.add(new Wait(arduino));
        blocks.add(new Debug(arduino));







        return arduino;
    }
}
