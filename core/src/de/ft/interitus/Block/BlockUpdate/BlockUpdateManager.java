/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.BlockUpdate;

import de.ft.interitus.Block.Block;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectVar;

public class BlockUpdateManager {
    public static void updateBlocks() {

        if (UIVar.isdialogeopend) return;

        ProjectVar projectVar = ProjectManager.getActProjectVar();
        assert projectVar != null;

        BlockMarkManager.update();

        for(int i=0;i<projectVar.blocks.size();i++) {
            Block block = projectVar.blocks.get(i);

            BlockMovingManager.update(block);
            BlockDataWireManager.update(block);

            //If Block gets deleted the block before will be used
            if(!projectVar.blocks.contains(block)) i--;


        }

    }


}
