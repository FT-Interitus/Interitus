/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

import de.ft.interitus.Program;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectVar;

import java.util.Timer;
import java.util.TimerTask;

public class ThreadManager {


    public static void stopAll() {

        assert ProjectManager.getActProjectVar() != null;
        ProjectManager.getActProjectVar().visible_blocks.clear();

    }


    public synchronized static void init() {


        //Program.logger.config("Test"+i);
        //Program.logger.config(camfr.boundsInFrustum(BlockVar.blocks.get(10).getX(), BlockVar.blocks.get(10).getY(), 0, BlockVar.blocks.get(10).getW(), BlockVar.blocks.get(10).getH(),0));
        Thread init = new Thread(() -> {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {

                    if (!UIVar.isdialogeopend && Var.inProgram && ProjectManager.getActProjectVar() != null) {
                        ProjectVar projectVar = ProjectManager.getActProjectVar();

                        for (int i = 0; i < projectVar.blocks.size(); i++) {
                            //Program.logger.config("Test"+i);
//                            Program.logger.config(camfr.boundsInFrustum(BlockVar.blocks.get(10).getX(), BlockVar.blocks.get(10).getY(), 0, BlockVar.blocks.get(10).getW(), BlockVar.blocks.get(10).getH(),0));

                            Block block = projectVar.blocks.get(i);
                            if (!block.isVisible() && !block.isMarked() && projectVar.visible_blocks.contains(block)) {


                                projectVar.visible_blocks.remove(block);
                            }

                            if (block.isVisible() && !projectVar.visible_blocks.contains(block)) {
                                projectVar.visible_blocks.add(block);


                                Program.logger.config("Visible " + block.getIndex());

                            }

                        }

                    }

                }
            }, 0, 200);


        });

        init.start();

    }


}
