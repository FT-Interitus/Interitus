/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Generators.BlockUpdateGenerator;
import de.ft.interitus.Block.Generators.BlocktoSaveGenerator;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;

public class DefaultBlock extends Block {


    public DefaultBlock(int index, int x, int y, int w, int h, PlatformSpecificBlock platformSpecificBlock, BlockUpdateGenerator update, BlocktoSaveGenerator blocktoSaveGenerator) {
        super(index, x, y, w, h, platformSpecificBlock, update, blocktoSaveGenerator);


        if (platformSpecificBlock.getBlockParameter() != null) {
            for (int i = 0; i < platformSpecificBlock.getBlockParameter().size(); i++) {
                platformSpecificBlock.getBlockParameter().get(i).setBlock(this);
            }
        }
    }
}
