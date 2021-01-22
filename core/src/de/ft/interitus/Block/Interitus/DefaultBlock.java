/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Generators.BlockToSaveGenerator;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.projecttypes.BlockTypes.BlockMode;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;

public class DefaultBlock extends Block {


    public DefaultBlock(int index, int x, int y, int w, int h, PlatformSpecificBlock platformSpecificBlock, BlockToSaveGenerator blocktoSaveGenerator, boolean isSubBlock) {
        super(x, y, w, h, platformSpecificBlock, blocktoSaveGenerator, isSubBlock);


        for (BlockMode blockMode : platformSpecificBlock.getBlockModes()) {

            if (blockMode.getBlockParameter() == null) {
                continue;
            }
            for (Parameter parameter : blockMode.getBlockParameter()) {
                parameter.setBlock(this);
            }


        }


    }
}
