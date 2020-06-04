package de.ft.interitus.Block.Generators;

import de.ft.interitus.Block.Block;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;

public interface BlockGenerator {
     Block generateBlock(int index, int x, int y, int w, int h, PlatformSpecificBlock platformSpecificBlock, BlockUpdateGenerator generator);
}
