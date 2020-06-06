package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockUpdate;

import java.io.Serializable;

public class DefaultBlockUpdate extends BlockUpdate implements Serializable {

    public DefaultBlockUpdate(Block block) {
        super(block);
    }
}
