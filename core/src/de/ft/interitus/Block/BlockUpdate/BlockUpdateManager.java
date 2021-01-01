/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.BlockUpdate;

import de.ft.interitus.Block.Block;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.global.GlobalEventAdapter;
import de.ft.interitus.events.global.GlobalEventManager;
import de.ft.interitus.events.global.GlobalFocusLostEvent;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectVar;

public class BlockUpdateManager {
    public static void init() {
        EventVar.globalEventManager.addListener(new GlobalEventAdapter() {
            @Override
            public void focuslost(GlobalFocusLostEvent e) {
                if(ProjectManager.getActProjectVar()!=null) {
                    if(ProjectManager.getActProjectVar().movingWire!=null) ProjectManager.getActProjectVar().movingWire.delete();
                    if(ProjectManager.getActProjectVar().movingDataWire!=null) ProjectManager.getActProjectVar().movingDataWire.delete();
                }

            }
        });
    }

    public static void updateBlocks() {

        if (UIVar.isdialogeopend) return;

        ProjectVar projectVar = ProjectManager.getActProjectVar();
        assert projectVar != null;

        BlockMarkManager.update();

        for(int i=0;i<projectVar.blocks.size();i++) {
            Block block = projectVar.blocks.get(i);

            BlockMovingManager.update(block);
            BlockDataWireManager.update(block);
            BlockWireManager.update(block);

            //If Block gets deleted the block before will be used
            if(!projectVar.blocks.contains(block)) i--;


        }

        if(ProjectManager.getActProjectVar().movingWire!=null) {
            BlockWireManager.updateMovingWire();
        }

    }


}
