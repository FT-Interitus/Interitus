package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Generators.BlockUpdateGenerator;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;

import java.io.Serializable;

public class DefaultBlock extends Block implements Serializable {


    public DefaultBlock(int index, int x, int y, int w, int h, PlatformSpecificBlock platformSpecificBlock, BlockUpdateGenerator update) {
        super(index, x, y, w, h, platformSpecificBlock, update);
    }
}
