/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Generators.BlockUpdateGenerator;
import de.ft.interitus.Block.Generators.BlocktoSaveGenerator;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.projecttypes.BlockTypes.BlockModus;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;

public class DefaultBlock extends Block {


    public DefaultBlock(int index, int x, int y, int w, int h, PlatformSpecificBlock platformSpecificBlock, BlockUpdateGenerator update, BlocktoSaveGenerator blocktoSaveGenerator, boolean isSubBlock) {
        super(index, x, y, w, h, platformSpecificBlock, update, blocktoSaveGenerator, isSubBlock);



            for(BlockModus blockModus :platformSpecificBlock.getBlockModis()) {

                if(blockModus.getBlockParameter()==null) {
                    continue;
                }
                for (Parameter parameter: blockModus.getBlockParameter()) {
                    parameter.setBlock(this);
                }


            }




    }
}
