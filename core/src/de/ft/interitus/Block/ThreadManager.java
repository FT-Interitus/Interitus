/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

import de.ft.interitus.Block.BlockUpdate.BlockUpdate;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.Program;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectVar;

import java.util.Timer;
import java.util.TimerTask;

public class ThreadManager {




    public static Thread add(Thread thread, Object obj) {
        Thread createThread = new Thread();
        ProjectManager.getActProjectVar().threads.add(thread);
        ProjectManager.getActProjectVar().requestobj.add(obj);
        return createThread;
    }

    public static void stopall() {
        for (int i = 0; i < ProjectManager.getActProjectVar().threads.size(); i++) {
            ((BlockUpdate) ProjectManager.getActProjectVar().threads.get(i)).time.cancel();
        }
    }


    public synchronized static void init() {


        Thread init = new Thread(() -> {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {

                    if (!UIVar.isdialogeopend) {
                        ProjectVar projectVar =ProjectManager.getActProjectVar();
                        try {



                            for (int i = 0; i < projectVar.blocks.size(); i++) {
                                //Program.logger.config("Test"+i);
//                            Program.logger.config(camfr.boundsInFrustum(BlockVar.blocks.get(10).getX(), BlockVar.blocks.get(10).getY(), 0, BlockVar.blocks.get(10).getW(), BlockVar.blocks.get(10).getH(),0));
                                try {
                                    Block block = ((BlockUpdate) projectVar.threads.get(i)).block;
                                    if (!block.isVisible() && !block.isMarked() && ((BlockUpdate) projectVar.threads.get(i)).isrunning) {

                                        if (((BlockUpdate) projectVar.threads.get(i)).isrunning) {
                                            try {
                                                ((BlockUpdate) projectVar.threads.get(i)).time.cancel();
                                            } catch (Exception ignored) {
                                            }
                                        }
                                        projectVar.threads.get(i).interrupt();
                                        ((BlockUpdate) projectVar.threads.get(i)).isrunning = false;
                                        projectVar.visible_blocks.remove(block);
                                    }

                                    if (block.isVisible() && !((BlockUpdate) projectVar.threads.get(i)).isrunning) {
                                        projectVar.visible_blocks.add(block);
                                        projectVar.threads.set(i, ((BlockUpdate) projectVar.threads.get(i)).block.allowedRestart());


                                        Program.logger.config("Started " + block.getIndex());

                                        ((BlockUpdate) projectVar.threads.get(i)).isrunning = true;
                                    }


                                } catch (Exception e) {
                                    DisplayErrors.error = e;
                                    e.printStackTrace();
                                }
                            }


                            for (int i = 0; i < projectVar.wires.size(); i++) {
                                if (projectVar.wires.get(i).isvisible()) {
                                    if (!projectVar.visible_wires.contains(projectVar.wires.get(i))) {
                                        projectVar.visible_wires.add(projectVar.wires.get(i));
                                    }
                                } else {
                                    projectVar.visible_wires.remove(projectVar.wires.get(i));
                                }
                            }

                            for (int i = 0; i < projectVar.wireNodes.size(); i++) {
                                if (projectVar.wireNodes.get(i).isVisible()) {
                                    if (!projectVar.visibleWireNodes.contains(projectVar.wireNodes.get(i))) {
                                        projectVar.visibleWireNodes.add(projectVar.wireNodes.get(i));
                                    }
                                } else {
                                    projectVar.visibleWireNodes.remove(projectVar.wireNodes.get(i));
                                }
                            }


                        } catch (Exception e) {
                            e.printStackTrace(); //for debug to find errors
                        }


                        //Enable or disable Wire System

                        projectVar.wires_allowed = !projectVar.ismoving;
                    }

                }
            }, 0, 200);


        });

        init.start();

    }


}
