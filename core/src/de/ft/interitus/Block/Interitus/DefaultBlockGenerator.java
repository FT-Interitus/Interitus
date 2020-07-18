package de.ft.interitus.Block.Interitus;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Generators.BlockGenerator;
import de.ft.interitus.Block.Generators.BlockUpdateGenerator;
import de.ft.interitus.Block.Generators.BlocktoSaveGenerator;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;

import java.lang.reflect.InvocationTargetException;

public class DefaultBlockGenerator implements BlockGenerator {

    @Override
    public Block generateBlock(int index, int x, int y, int w, int h, PlatformSpecificBlock platformSpecificBlock, BlockUpdateGenerator generator, BlocktoSaveGenerator blocktoSaveGenerator) {
        try {

            return new DefaultBlock(index, x, y, w, h,  platformSpecificBlock.getClass().getDeclaredConstructor(ProjectTypes.class).newInstance(ProjectManager.getActProjectVar().projectType), generator, blocktoSaveGenerator);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }



    }
}
