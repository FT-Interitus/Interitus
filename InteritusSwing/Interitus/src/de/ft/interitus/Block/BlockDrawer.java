/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.shortcut.shortcuts.BlockShortcuts;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
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
                    //TODO L ProgrammingSpace.batch.begin();
                    //TODO L ProgrammingSpace.batch.end();

                } catch (IllegalStateException e) {
                    //TODO L ProgrammingSpace.batch.end();
                }

                try {
                    if (block.isMarked()) {
                        if (block.isMoving()) {
                            Temp2 = block;
                        } else {
                            Temp = block;
                        }
                    } else {
                        //TODO g2dmitgeben block.draw(ProgrammingSpace.batch, ProgrammingSpace.BlockshapeRenderer, ProgrammingSpace.font);
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
                    //TODO g2dmitgeben Temp.draw(ProgrammingSpace.batch, ProgrammingSpace.BlockshapeRenderer, ProgrammingSpace.font);
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

            UI.updatedragui(ProgrammingSpace.shapeRenderer, false, ProgrammingSpace.batch);
            BlockTappedBar.tb.setX(UIVar.BlockBarX + UIVar.BlockBarW / 2);
            BlockTappedBar.tb.setY(UIVar.BlockBarY + UIVar.BlockBarH / 2 - (BlockTappedBar.tb.getHeight() + UIVar.abstandvonRand * 2) / 2);
            BlockTappedBar.tb.draw();

            if (Temp2 != null) {

                try {
                    //TODO g2dmitgeben Temp2.draw(ProgrammingSpace.batch, ProgrammingSpace.BlockshapeRenderer, ProgrammingSpace.font);


                } catch (Exception ignored) {

                }
            }


        }
    }
}
