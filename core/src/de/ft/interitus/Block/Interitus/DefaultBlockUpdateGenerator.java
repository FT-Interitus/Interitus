package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockUpdate;
import de.ft.interitus.Block.Generators.BlockUpdateGenerator;

import java.io.Serializable;

public class DefaultBlockUpdateGenerator implements BlockUpdateGenerator, Serializable {
    @Override
    public BlockUpdate generate(Block block) {
        return new DefaultBlockUpdate(block);
    }
}
