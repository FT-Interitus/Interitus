/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.RaspberryPi;

import de.ft.interitus.Block.Interitus.*;
import de.ft.interitus.Block.Interitus.save.DefaultSaveBlockGenerator;
import de.ft.interitus.ProgramingSpace;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.BlockTypes.ProjectTypesVar;
import de.ft.interitus.projecttypes.ProjectType;
import de.ft.interitus.utils.ArrayList;

public class InitRaspberryPI {
    static ArrayList<PlatformSpecificBlock> platformSpecificBlocks = new ArrayList<>();

    public static ProjectType init() {


        return new ProjectType(ProjectTypesVar.nativ, "RaspberryPi-Projekt", platformSpecificBlocks, new DefaultBlockGenerator(), new DefaultSaveBlockGenerator(), new DefaultBlockVarGenerator(), new RaspberryPiFunktions(), null);
    }
}
