package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockUpdateGenerator;
import de.ft.interitus.Block.WireGenerator;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;

public class DefaultBlock extends Block {


    public DefaultBlock(int index, int x, int y, int w, int h, PlatformSpecificBlock platformSpecificBlock, BlockUpdateGenerator update) {
        super(index, x, y, w, h, platformSpecificBlock, update);
    }
}
