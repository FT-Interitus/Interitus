/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.shortcut.shortcuts.BlockShortcuts;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.Var;
import de.ft.interitus.WindowManager;
import de.ft.interitus.projecttypes.ProjectManager;

public class BlockDrawer {
    public static void Draw(float delta) {
        if (!Var.isloading) {

            for (int i = 0; i < ProjectManager.getActProjectVar().visible_blocks.size(); i = i + 1) {
                Block block = ProjectManager.getActProjectVar().visible_blocks.get(i);


                if (block.isMarked()) continue;
                block.draw(WindowManager.blockBatch, WindowManager.BlockshapeRenderer, WindowManager.font);


            }


            for (int i = 0; i < ProjectManager.getActProjectVar().marked_blocks.size(); i++) {
                Block block = ProjectManager.getActProjectVar().marked_blocks.get(i);
                if (block.isMoving()) continue;
                block.draw(WindowManager.blockBatch, WindowManager.BlockshapeRenderer, WindowManager.font);

                if (BlockShortcuts.shortCut_deleteBlock.isPressed() && block.getBlocktype().canbedeleted()) {
                    block.delete(false);
                    i--;
                }
            }


            UI.updatedragui(WindowManager.shapeRenderer, false, WindowManager.blockBatch, delta);
            BlockTappedBar.tb.setX(UIVar.BlockBarX + UIVar.BlockBarW / 2);
            BlockTappedBar.tb.setY(UIVar.BlockBarY + UIVar.BlockBarH / 2 - (BlockTappedBar.tb.getHeight() + UIVar.abstandvonRand * 2) / 2);
            BlockTappedBar.tb.draw();

            if (ProjectManager.getActProjectVar().moving_block != null) {

                try {
                    ProjectManager.getActProjectVar().moving_block.draw(WindowManager.blockBatch, WindowManager.BlockshapeRenderer, WindowManager.font);


                } catch (Exception ignored) {

                }
            }

            if (ProjectManager.getActProjectVar().moveingdatawire != null) {
                ProjectManager.getActProjectVar().moveingdatawire.draw();
            }


        }
    }
}
