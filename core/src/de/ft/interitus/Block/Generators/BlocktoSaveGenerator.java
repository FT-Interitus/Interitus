/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.Generators;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Saving.SaveBlockV1;
import de.ft.interitus.projecttypes.ProjectVar;


public interface BlocktoSaveGenerator {
    SaveBlockV1 generate(Block block, ProjectVar projectVar);
}
