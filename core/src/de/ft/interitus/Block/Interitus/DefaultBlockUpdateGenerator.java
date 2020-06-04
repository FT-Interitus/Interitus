package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockUpdate;
import de.ft.interitus.Block.BlockUpdateGenerator;
import de.ft.interitus.Block.WireGenerator;

public class DefaultBlockUpdateGenerator implements BlockUpdateGenerator {
    @Override
    public BlockUpdate generate(Block block) {
        return new DefaultBlockUpdate(block);
    }
}
