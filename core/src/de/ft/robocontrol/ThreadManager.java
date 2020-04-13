package de.ft.robocontrol;

import com.badlogic.gdx.math.Frustum;
import de.ft.robocontrol.Block.Block;
import de.ft.robocontrol.Block.BlockUpdate;
import de.ft.robocontrol.Block.BlockVar;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ThreadManager {
    public static ArrayList<Thread> threads = new ArrayList<Thread>();
    public static ArrayList<Object> requestobj = new ArrayList<Object>();


    static Frustum camfr;

    public static Thread add(Thread thread, Object obj) {
        Thread createThread = new Thread();
        threads.add(thread);
        requestobj.add(obj);
        return createThread;
    }


    public synchronized static void init() {


        Thread init = new Thread() {
            @Override
            public void run() {
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {

                        try {
                            camfr = ProgrammingSpace.cam.frustum;


                            for (int i = 0; i < BlockVar.blocks.size(); i++) {
                                //System.out.println("Test"+i);
//                            System.out.println(camfr.boundsInFrustum(BlockVar.blocks.get(10).getX(), BlockVar.blocks.get(10).getY(), 0, BlockVar.blocks.get(10).getW(), BlockVar.blocks.get(10).getH(),0));
                                try {
                                    Block block = ((BlockUpdate) threads.get(i)).block;
                                    if (!(camfr.boundsInFrustum(block.getX(), block.getY(), 0, block.getW(), block.getH(), 0)) && block.isMarked() == false && ((BlockUpdate) threads.get(i)).isrunning == true) {
                                        ((BlockUpdate) threads.get(i)).time.cancel();
                                        threads.get(i).interrupt();
                                        ((BlockUpdate) threads.get(i)).isrunning = false;
                                        BlockVar.visibleblocks.remove(block);
                                    }

                                    if (camfr.boundsInFrustum(block.getX(), block.getY(), 0, block.getW(), block.getH(), 0) && ((BlockUpdate) threads.get(i)).isrunning == false) {
                                        BlockVar.visibleblocks.add(block);
                                        threads.set(i, ((BlockUpdate) threads.get(i)).block.allowedRestart());
                                        System.out.println("Started");
                                        ((BlockUpdate) threads.get(i)).isrunning = true;
                                    }


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }


                            for(int i=0;i<BlockVar.wires.size();i++) {
                                if(BlockVar.wires.get(i).isvisible()) {
                                    if(BlockVar.visiblewires.indexOf(BlockVar.wires.get(i))==-1) {
                                        BlockVar.visiblewires.add(BlockVar.wires.get(i));
                                    }
                                }else{
                                    if(BlockVar.visiblewires.indexOf(BlockVar.wires.get(i))!=-1) {
                                        BlockVar.visiblewires.remove(BlockVar.wires.get(i));
                                    }
                                }
                            }

                            for(int i=0;i<BlockVar.wireNodes.size();i++) {
                                if(BlockVar.wireNodes.get(i).isVisible()) {
                                    if(BlockVar.visibleWireNodes.indexOf(BlockVar.wireNodes.get(i))==-1) {
                                        BlockVar.visibleWireNodes.add(BlockVar.wireNodes.get(i));
                                    }
                                }else{
                                    if(BlockVar.visibleWireNodes.indexOf(BlockVar.wireNodes.get(i))!=-1) {
                                        BlockVar.visibleWireNodes.remove(BlockVar.wireNodes.get(i));
                                    }
                                }
                            }



                        }catch (Exception e) {
                            e.printStackTrace(); //for debug to find errors
                        }
                    }
                }, 0, 100);


            }
        };

        init.start();

    }


}
