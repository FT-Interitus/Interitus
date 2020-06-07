package de.ft.interitus.Block.Generators;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.SaveBlock;


public interface BlocktoSaveGenerator {
    SaveBlock generate(Block block);
}
