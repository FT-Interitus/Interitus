package de.ft.interitus.projecttypes.BlockTypes.Ev3;

import de.ft.interitus.Block.Interitus.*;
import de.ft.interitus.Block.Interitus.save.DefaultSaveBlockGenerator;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.DeviceTypes.Ev3;

import java.util.ArrayList;

public class InitEv3 {
    static ArrayList<PlatformSpecificBlock> platformSpecificBlocks = new ArrayList<>();

    public static ProjectTypes init() {

        platformSpecificBlocks.add(new Wait());
        return new ProjectTypes(ProgrammingSpace.nativ, new Ev3(), "Ev3-Projekt", platformSpecificBlocks, new DefaultBlockGenerator(), new DefaultBlockUpdateGenerator(), new DefaultWireGenerator(), new DefaultWireNodeGenerator(), new DefaultSaveBlockGenerator(), new DefaultBlockVarGenerator(), new EV3Creator(),null);
    }
}
