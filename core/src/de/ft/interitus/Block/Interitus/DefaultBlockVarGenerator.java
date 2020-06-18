package de.ft.interitus.Block.Interitus;

import de.ft.interitus.projecttypes.ProjectVar;
import de.ft.interitus.Block.Generators.BlockVarGenerator;

public class DefaultBlockVarGenerator implements BlockVarGenerator {
    @Override
    public ProjectVar generate() {
        return new DefaultProjectVar();
    }
}
