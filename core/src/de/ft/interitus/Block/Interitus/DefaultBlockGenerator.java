package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Generators.BlockGenerator;
import de.ft.interitus.Block.Generators.BlockUpdateGenerator;
import de.ft.interitus.Block.Generators.BlocktoSaveGenerator;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;

public class DefaultBlockGenerator implements BlockGenerator {

    @Override
    public Block generateBlock(int index, int x, int y, int w, int h, PlatformSpecificBlock platformSpecificBlock, BlockUpdateGenerator generator, BlocktoSaveGenerator blocktoSaveGenerator) {
        return new DefaultBlock(index, x, y, w, h, platformSpecificBlock, generator, blocktoSaveGenerator);
    }
}
