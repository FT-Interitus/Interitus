/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.Generators.BlockVarGenerator;
import de.ft.interitus.projecttypes.ProjectVar;

public class DefaultBlockVarGenerator implements BlockVarGenerator {
    @Override
    public ProjectVar generate() {
        return new DefaultProjectVar();
    }
}
