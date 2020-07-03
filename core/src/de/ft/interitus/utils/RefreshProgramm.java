/*
 * Copyright (c) 2020.
 * Author Tim & Felix
 */

package de.ft.interitus.utils;

import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.data.user.BlockCalculator;

import java.util.ArrayList;

public class RefreshProgramm {
    public static void refresh()  {


        ArrayList<SaveBlock> saveBlocks = BlockCalculator.save();



        ClearActOpenProgramm.clear();


        BlockCalculator.extract(saveBlocks);



    }
}
