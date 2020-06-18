package de.ft.interitus;

import com.badlogic.gdx.math.Frustum;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockUpdate;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ThreadManager {
    public static ArrayList<Thread> threads = new ArrayList<>();
    public static ArrayList<Object> requestobj = new ArrayList<>();


    static Frustum camfr;

    public static Thread add(Thread thread, Object obj) {
        Thread createThread = new Thread();
        threads.add(thread);
        requestobj.add(obj);
        return createThread;
    }

    public static void stopall() {
        for(int i=0;i<threads.size();i++) {
            ((BlockUpdate) threads.get(i)).time.cancel();
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

                        if(!Var.isdialogeopend) {

                            try {
                                camfr = ProgrammingSpace.cam.frustum;


                                for (int i = 0; i < Var.openprojects.get(Var.openprojectindex).blocks.size(); i++) {
                                    //System.out.println("Test"+i);
//                            System.out.println(camfr.boundsInFrustum(BlockVar.blocks.get(10).getX(), BlockVar.blocks.get(10).getY(), 0, BlockVar.blocks.get(10).getW(), BlockVar.blocks.get(10).getH(),0));
                                    try {
                                        Block block = ((BlockUpdate) threads.get(i)).block;
                                        if (!(camfr.boundsInFrustum(block.getX(), block.getY(), 0, block.getW(), block.getH(), 0)) && block.isMarked() == false && ((BlockUpdate) threads.get(i)).isrunning == true) {

                                            if (((BlockUpdate) threads.get(i)).isrunning) {
                                                try {
                                                    ((BlockUpdate) threads.get(i)).time.cancel();
                                                } catch (Exception e) {
                                                }
                                            }
                                            threads.get(i).interrupt();
                                            ((BlockUpdate) threads.get(i)).isrunning = false;
                                            Var.openprojects.get(Var.openprojectindex).visibleblocks.remove(block);
                                        }

                                        if (camfr.boundsInFrustum(block.getX(), block.getY(), 0, block.getW(), block.getH(), 0) && ((BlockUpdate) threads.get(i)).isrunning == false) {
                                            Var.openprojects.get(Var.openprojectindex).visibleblocks.add(block);
                                            threads.set(i, ((BlockUpdate) threads.get(i)).block.allowedRestart());
                                            if (Var.verboseoutput) {
                                                System.out.println("Started " + block.getIndex());
                                            }
                                            ((BlockUpdate) threads.get(i)).isrunning = true;
                                        }


                                    } catch (Exception e) {
                                        DisplayErrors.error = e;
                                        e.printStackTrace();
                                    }
                                }


                                for (int i = 0; i < Var.openprojects.get(Var.openprojectindex).wires.size(); i++) {
                                    if (Var.openprojects.get(Var.openprojectindex).wires.get(i).isvisible()) {
                                        if (Var.openprojects.get(Var.openprojectindex).visiblewires.indexOf(Var.openprojects.get(Var.openprojectindex).wires.get(i)) == -1) {
                                            Var.openprojects.get(Var.openprojectindex).visiblewires.add(Var.openprojects.get(Var.openprojectindex).wires.get(i));
                                        }
                                    } else {
                                        if (Var.openprojects.get(Var.openprojectindex).visiblewires.indexOf(Var.openprojects.get(Var.openprojectindex).wires.get(i)) != -1) {
                                            Var.openprojects.get(Var.openprojectindex).visiblewires.remove(Var.openprojects.get(Var.openprojectindex).wires.get(i));
                                        }
                                    }
                                }

                                for (int i = 0; i < Var.openprojects.get(Var.openprojectindex).wireNodes.size(); i++) {
                                    if (Var.openprojects.get(Var.openprojectindex).wireNodes.get(i).isVisible()) {
                                        if (Var.openprojects.get(Var.openprojectindex).visibleWireNodes.indexOf(Var.openprojects.get(Var.openprojectindex).wireNodes.get(i)) == -1) {
                                            Var.openprojects.get(Var.openprojectindex).visibleWireNodes.add(Var.openprojects.get(Var.openprojectindex).wireNodes.get(i));
                                        }
                                    } else {
                                        if (Var.openprojects.get(Var.openprojectindex).visibleWireNodes.indexOf(Var.openprojects.get(Var.openprojectindex).wireNodes.get(i)) != -1) {
                                            Var.openprojects.get(Var.openprojectindex).visibleWireNodes.remove(Var.openprojects.get(Var.openprojectindex).wireNodes.get(i));
                                        }
                                    }
                                }


                            } catch (Exception e) {
                                e.printStackTrace(); //for debug to find errors
                            }


                            //Enable or disable Wire System

                            if (Var.openprojects.get(Var.openprojectindex).ismoving) {
                                Var.openprojects.get(Var.openprojectindex).wirezulassung = false;
                            } else {
                                Var.openprojects.get(Var.openprojectindex).wirezulassung = true;
                            }
                        }

                    }
                }, 0, 100);


            }
        };

        init.start();

    }


}
