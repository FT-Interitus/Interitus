/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.Generators;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockUpdate.BlockUpdate;

public interface BlockUpdateGenerator {
    BlockUpdate generate(Block block);
}
