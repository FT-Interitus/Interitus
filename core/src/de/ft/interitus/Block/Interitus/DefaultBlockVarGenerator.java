package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.BlockVar;
import de.ft.interitus.Block.Generators.BlockVarGenerator;

public class DefaultBlockVarGenerator implements BlockVarGenerator {
    @Override
    public BlockVar generate() {
        return new DefaultBlockVar();
    }
}
