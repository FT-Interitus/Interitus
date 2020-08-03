/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus;

import com.badlogic.gdx.math.Frustum;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockUpdate;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectVar;

import java.util.Timer;
import java.util.TimerTask;

public class ThreadManager {


    static Frustum camfr;

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


        Thread init = new Thread() {
            @Override
            public void run() {
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {

                        if (!UIVar.isdialogeopend) {
                            ProjectVar projectVar =ProjectManager.getActProjectVar();
                            try {
                                camfr = ProgrammingSpace.cam.frustum;


                                for (int i = 0; i < projectVar.blocks.size(); i++) {
                                    //System.out.println("Test"+i);
//                            System.out.println(camfr.boundsInFrustum(BlockVar.blocks.get(10).getX(), BlockVar.blocks.get(10).getY(), 0, BlockVar.blocks.get(10).getW(), BlockVar.blocks.get(10).getH(),0));
                                    try {
                                        Block block = ((BlockUpdate) projectVar.threads.get(i)).block;
                                        if (!(camfr.boundsInFrustum(block.getX(), block.getY(), 0, block.getW(), block.getH(), 0)) && block.isMarked() == false && ((BlockUpdate) projectVar.threads.get(i)).isrunning == true) {

                                            if (((BlockUpdate) projectVar.threads.get(i)).isrunning) {
                                                try {
                                                    ((BlockUpdate) projectVar.threads.get(i)).time.cancel();
                                                } catch (Exception e) {
                                                }
                                            }
                                            projectVar.threads.get(i).interrupt();
                                            ((BlockUpdate) projectVar.threads.get(i)).isrunning = false;
                                            projectVar.visibleblocks.remove(block);
                                        }

                                        if (camfr.boundsInFrustum(block.getX(), block.getY(), 0, block.getW(), block.getH(), 0) && ((BlockUpdate) projectVar.threads.get(i)).isrunning == false) {
                                            projectVar.visibleblocks.add(block);
                                            projectVar.threads.set(i, ((BlockUpdate) projectVar.threads.get(i)).block.allowedRestart());


                                            Programm.logger.config("Started " + block.getIndex());

                                            ((BlockUpdate) projectVar.threads.get(i)).isrunning = true;
                                        }


                                    } catch (Exception e) {
                                        DisplayErrors.error = e;
                                        e.printStackTrace();
                                    }
                                }


                                for (int i = 0; i < projectVar.wires.size(); i++) {
                                    if (projectVar.wires.get(i).isvisible()) {
                                        if (projectVar.visiblewires.indexOf(projectVar.wires.get(i)) == -1) {
                                            projectVar.visiblewires.add(projectVar.wires.get(i));
                                        }
                                    } else {
                                        if (projectVar.visiblewires.indexOf(projectVar.wires.get(i)) != -1) {
                                            projectVar.visiblewires.remove(projectVar.wires.get(i));
                                        }
                                    }
                                }

                                for (int i = 0; i < projectVar.wireNodes.size(); i++) {
                                    if (projectVar.wireNodes.get(i).isVisible()) {
                                        if (projectVar.visibleWireNodes.indexOf(projectVar.wireNodes.get(i)) == -1) {
                                            projectVar.visibleWireNodes.add(projectVar.wireNodes.get(i));
                                        }
                                    } else {
                                        if (projectVar.visibleWireNodes.indexOf(projectVar.wireNodes.get(i)) != -1) {
                                            projectVar.visibleWireNodes.remove(projectVar.wireNodes.get(i));
                                        }
                                    }
                                }


                            } catch (Exception e) {
                                e.printStackTrace(); //for debug to find errors
                            }


                            //Enable or disable Wire System

                            projectVar.wirezulassung = !projectVar.ismoving;
                        }

                    }
                }, 0, 200);


            }
        };

        init.start();

    }


}
