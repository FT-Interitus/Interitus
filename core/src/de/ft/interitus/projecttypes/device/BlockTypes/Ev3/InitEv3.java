package de.ft.interitus.projecttypes.device.BlockTypes.Ev3;

import de.ft.interitus.Block.Interitus.DefaultBlockGenerator;
import de.ft.interitus.Block.Interitus.DefaultBlockUpdateGenerator;
import de.ft.interitus.Block.Interitus.DefaultWireGenerator;
import de.ft.interitus.Block.Interitus.DefaultWireNodeGenerator;
import de.ft.interitus.Block.Interitus.save.DefaultSaveBlockGenerator;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.device.DeviceTypes.Ev3;

import java.util.ArrayList;

public class InitEv3 {
   static ArrayList<PlatformSpecificBlock> platformSpecificBlocks = new ArrayList<>();
    public static ProjectTypes init() {

        platformSpecificBlocks.add(new Wait());
        return new ProjectTypes(new Ev3(),"Ev3-Projekt",platformSpecificBlocks,new DefaultBlockGenerator(), new DefaultBlockUpdateGenerator(),new DefaultWireGenerator(),new DefaultWireNodeGenerator(), new DefaultSaveBlockGenerator());
    }
}
