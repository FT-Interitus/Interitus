/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.utils;

import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.Var;
import de.ft.interitus.datamanager.BlockCalculator;

public class RefreshProgramm {
    public static void refresh() {


        ArrayList<SaveBlock> saveBlocks = BlockCalculator.save();


        ClearActOpenProgramm.clear(Var.openprojectindex);


        BlockCalculator.extract(saveBlocks);


    }
}
