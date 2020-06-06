package de.ft.interitus.Block.Generators;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockUpdate;

public interface BlockUpdateGenerator  {
    BlockUpdate generate(Block block);
}
