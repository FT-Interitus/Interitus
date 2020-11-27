/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

import de.ft.interitus.WindowManager;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.shortcut.shortcuts.BlockShortcuts;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.ProjectManager;

public class BlockDrawer {
    public static void Draw(float delta) {
        if (!Var.isloading) {
            Block markedBlock = null;
            Block movingBlock = null;
            for (int i = 0; i < ProjectManager.getActProjectVar().visible_blocks.size(); i = i + 1) {
                Block block = ProjectManager.getActProjectVar().visible_blocks.get(i);

                try {
                    WindowManager.blockBatch.begin();
                    WindowManager.blockBatch.end();

                } catch (IllegalStateException e) {
                    WindowManager.blockBatch.end();
                }

                try {
                    if (block.isMarked()) {
                        if (block.isMoving()) {
                            movingBlock = block;
                        } else {
                            markedBlock = block;
                        }
                    } else {
                        block.draw(WindowManager.blockBatch, WindowManager.BlockshapeRenderer, WindowManager.font);
                    }

                    if (block.isMarked()) {


                        if (BlockShortcuts.shortCut_deleteBlock.isPressed() && block.getBlocktype().canbedeleted()) {
                            block.delete(false);
                        }


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (markedBlock != null) {

                try {
                    markedBlock.draw(WindowManager.blockBatch, WindowManager.BlockshapeRenderer, WindowManager.font);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }




            UI.updatedragui(WindowManager.shapeRenderer, false, WindowManager.blockBatch,delta);
            BlockTappedBar.tb.setX(UIVar.BlockBarX + UIVar.BlockBarW / 2);
            BlockTappedBar.tb.setY(UIVar.BlockBarY + UIVar.BlockBarH / 2 - (BlockTappedBar.tb.getHeight() + UIVar.abstandvonRand * 2) / 2);
            BlockTappedBar.tb.draw();

            if (movingBlock != null) {

                try {
                    movingBlock.draw(WindowManager.blockBatch, WindowManager.BlockshapeRenderer, WindowManager.font);


                } catch (Exception ignored) {

                }
            }

            if(ProjectManager.getActProjectVar().moveingdatawire!=null) {
                ProjectManager.getActProjectVar().moveingdatawire.draw();
            }


        }
    }
}
