/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Generators.BlockGenerator;
import de.ft.interitus.Block.Generators.BlockUpdateGenerator;
import de.ft.interitus.Block.Generators.BlocktoSaveGenerator;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectType;

public class DefaultBlockGenerator implements BlockGenerator {

    @Override
    public Block generateBlock(int index, int x, int y, int w, int h, PlatformSpecificBlock platformSpecificBlock, BlockUpdateGenerator generator, BlocktoSaveGenerator blocktoSaveGenerator, boolean isSubBlock) {
        try {

            return new DefaultBlock(index, x, y, w, h, platformSpecificBlock.getClass().getDeclaredConstructor(ProjectType.class, Addon.class).newInstance(ProjectManager.getActProjectVar().projectType,platformSpecificBlock.getAdddon()), generator, blocktoSaveGenerator, isSubBlock);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
