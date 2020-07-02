package de.ft.interitus;

import com.badlogic.gdx.math.Frustum;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockUpdate;
import de.ft.interitus.projecttypes.ProjectManager;

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

                        if (!Var.isdialogeopend) {

                            try {
                                camfr = ProgrammingSpace.cam.frustum;

                                for (int i = 0; i < ProjectManager.getActProjectVar().blocks.size(); i++) {
                                    //System.out.println("Test"+i);
//                            System.out.println(camfr.boundsInFrustum(BlockVar.blocks.get(10).getX(), BlockVar.blocks.get(10).getY(), 0, BlockVar.blocks.get(10).getW(), BlockVar.blocks.get(10).getH(),0));
                                    try {
                                        Block block = ((BlockUpdate) ProjectManager.getActProjectVar().threads.get(i)).block;
                                        if (!(camfr.boundsInFrustum(block.getX(), block.getY(), 0, block.getW(), block.getH(), 0)) && block.isMarked() == false && ((BlockUpdate) ProjectManager.getActProjectVar().threads.get(i)).isrunning == true) {

                                            if (((BlockUpdate) ProjectManager.getActProjectVar().threads.get(i)).isrunning) {
                                                try {
                                                    ((BlockUpdate) ProjectManager.getActProjectVar().threads.get(i)).time.cancel();
                                                } catch (Exception e) {
                                                }
                                            }
                                            ProjectManager.getActProjectVar().threads.get(i).interrupt();
                                            ((BlockUpdate) ProjectManager.getActProjectVar().threads.get(i)).isrunning = false;
                                            ProjectManager.getActProjectVar().visibleblocks.remove(block);
                                        }

                                        if (camfr.boundsInFrustum(block.getX(), block.getY(), 0, block.getW(), block.getH(), 0) && ((BlockUpdate) ProjectManager.getActProjectVar().threads.get(i)).isrunning == false) {
                                            ProjectManager.getActProjectVar().visibleblocks.add(block);
                                            ProjectManager.getActProjectVar().threads.set(i, ((BlockUpdate) ProjectManager.getActProjectVar().threads.get(i)).block.allowedRestart());


                                                Programm.logger.config("Started " + block.getIndex());

                                            ((BlockUpdate) ProjectManager.getActProjectVar().threads.get(i)).isrunning = true;
                                        }


                                    } catch (Exception e) {
                                        DisplayErrors.error = e;
                                        e.printStackTrace();
                                    }
                                }


                                for (int i = 0; i < ProjectManager.getActProjectVar().wires.size(); i++) {
                                    if (ProjectManager.getActProjectVar().wires.get(i).isvisible()) {
                                        if (ProjectManager.getActProjectVar().visiblewires.indexOf(ProjectManager.getActProjectVar().wires.get(i)) == -1) {
                                            ProjectManager.getActProjectVar().visiblewires.add(ProjectManager.getActProjectVar().wires.get(i));
                                        }
                                    } else {
                                        if (ProjectManager.getActProjectVar().visiblewires.indexOf(ProjectManager.getActProjectVar().wires.get(i)) != -1) {
                                            ProjectManager.getActProjectVar().visiblewires.remove(ProjectManager.getActProjectVar().wires.get(i));
                                        }
                                    }
                                }

                                for (int i = 0; i < ProjectManager.getActProjectVar().wireNodes.size(); i++) {
                                    if (ProjectManager.getActProjectVar().wireNodes.get(i).isVisible()) {
                                        if (ProjectManager.getActProjectVar().visibleWireNodes.indexOf(ProjectManager.getActProjectVar().wireNodes.get(i)) == -1) {
                                            ProjectManager.getActProjectVar().visibleWireNodes.add(ProjectManager.getActProjectVar().wireNodes.get(i));
                                        }
                                    } else {
                                        if (ProjectManager.getActProjectVar().visibleWireNodes.indexOf(ProjectManager.getActProjectVar().wireNodes.get(i)) != -1) {
                                            ProjectManager.getActProjectVar().visibleWireNodes.remove(ProjectManager.getActProjectVar().wireNodes.get(i));
                                        }
                                    }
                                }


                            } catch (Exception e) {
                                e.printStackTrace(); //for debug to find errors
                            }


                            //Enable or disable Wire System

                            ProjectManager.getActProjectVar().wirezulassung = !ProjectManager.getActProjectVar().ismoving;
                        }

                    }
                }, 0, 200);


            }
        };

        init.start();

    }


}
