/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3;

import de.ft.interitus.Block.Interitus.*;
import de.ft.interitus.Block.Interitus.save.DefaultSaveBlockGenerator;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.utils.ArrayList;

public class InitEv3 {
    static ArrayList<PlatformSpecificBlock> platformSpecificBlocks = new ArrayList<>();

    public static ProjectTypes init() {

        platformSpecificBlocks.add(new Wait(null));
        return new ProjectTypes(ProgrammingSpace.nativ, "Ev3-Projekt", platformSpecificBlocks, new DefaultBlockGenerator(), new DefaultBlockUpdateGenerator(), new DefaultWireGenerator(), new DefaultWireNodeGenerator(), new DefaultSaveBlockGenerator(), new DefaultBlockVarGenerator(), new EV3Funktions(), null);
    }
}
