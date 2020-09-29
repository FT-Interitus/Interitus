/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockUpdate.BlockUpdate;
import de.ft.interitus.Block.Generators.BlockUpdateGenerator;

public class DefaultBlockUpdateGenerator implements BlockUpdateGenerator {
    @Override
    public BlockUpdate generate(Block block) {
        return new DefaultBlockUpdate(block);
    }
}
