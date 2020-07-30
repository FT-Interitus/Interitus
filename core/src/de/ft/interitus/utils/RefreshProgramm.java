/*
 * Copyright (c) 2020.
 * Author Tim & Felix
 */

package de.ft.interitus.utils;

import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.datamanager.BlockCalculator;

import de.ft.interitus.utils.ArrayList;

public class RefreshProgramm {
    public static void refresh()  {


        ArrayList<SaveBlock> saveBlocks = BlockCalculator.save();



        ClearActOpenProgramm.clear();


        BlockCalculator.extract(saveBlocks);



    }
}
