package de.ft.interitus.Block.Interitus.save;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Generators.BlocktoSaveGenerator;
import de.ft.interitus.Block.SaveBlock;

public class DefaultSaveBlockGenerator implements BlocktoSaveGenerator {

    @Override
    public SaveBlock generate(Block block) {
        return new DefaultSaveBlock(block.getX(),block.getY(),block.getIndex(),block.getLeft(),block.getRight());
    }
}
