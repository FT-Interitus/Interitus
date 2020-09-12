/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI_old.shortcut.shortcuts.BlockShortcuts;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.ProjectManager;

public class BlockDrawer {
    public static void Draw() {
        if (!Var.isloading) {
            Block Temp = null;
            Block Temp2 = null;
            for (int i = 0; i < ProjectManager.getActProjectVar().visibleblocks.size(); i = i + 1) {
                Block block = ProjectManager.getActProjectVar().visibleblocks.get(i);

                try {
                    ProgrammingSpace.batch.begin();
                    ProgrammingSpace.batch.end();

                } catch (IllegalStateException e) {
                    ProgrammingSpace.batch.end();
                }

                try {
                    if (block.isMarked()) {
                        if (block.isMoving()) {
                            Temp2 = block;
                        } else {
                            Temp = block;
                        }
                    } else {
                        block.draw(ProgrammingSpace.batch, ProgrammingSpace.BlockshapeRenderer, ProgrammingSpace.font);
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
            if (Temp != null) {

                try {
                    Temp.draw(ProgrammingSpace.batch, ProgrammingSpace.BlockshapeRenderer, ProgrammingSpace.font);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            for (int i = 0; i < ProjectManager.getActProjectVar().visiblewires.size(); i++) {
                if (!Var.isloading) {
                    ProjectManager.getActProjectVar().visiblewires.get(i).draw();
                }
            }
            for (int i = 0; i < ProjectManager.getActProjectVar().visibleWireNodes.size(); i++) {
                ProjectManager.getActProjectVar().visibleWireNodes.get(i).draw();
            }

           // UI.updatedragui(ProgrammingSpace.shapeRenderer, false, ProgrammingSpace.batch);
          //  BlockTappedBar.tb.setX(UIVar.BlockBarX + UIVar.BlockBarW / 2);
           // BlockTappedBar.tb.setY(UIVar.BlockBarY + UIVar.BlockBarH / 2 - (BlockTappedBar.tb.getHeight() + UIVar.abstandvonRand * 2) / 2);
          //  BlockTappedBar.tb.draw();

            if (Temp2 != null) {

                try {
                    Temp2.draw(ProgrammingSpace.batch, ProgrammingSpace.BlockshapeRenderer, ProgrammingSpace.font);


                } catch (Exception ignored) {

                }
            }


        }
    }
}
