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
        camfr = MainGame.cam.frustum;

        Thread init = new Thread() {
            @Override
            public void run() {
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {

                        for (int i = 0; i < threads.size(); i++) {

                            try {
                                Block block = ((BlockUpdate) threads.get(i)).block;
                                if( !(camfr.boundsInFrustum(block.getX(), block.getY(), 0, block.getW(), block.getH(), 0))&&block.isMarked()==false&&((BlockUpdate) threads.get(i)).isrunning == true){
                                    ((BlockUpdate) threads.get(i)).time.cancel();
                                    threads.get(i).interrupt();
                                    ((BlockUpdate) threads.get(i)).isrunning = false;
                                    BlockVar.visibleblocks.remove(block);
                                    System.out.println("Stop right");
                                }

                                if (camfr.boundsInFrustum(block.getX(), block.getY(), 0, block.getW(), block.getH(), 0) && ((BlockUpdate) threads.get(i)).isrunning == false) {

                                    BlockVar.visibleblocks.add(block);
                                    threads.set(i, ((BlockUpdate) threads.get(i)).block.allowedRestart());
                                    ((BlockUpdate) threads.get(i)).isrunning = true;
                                }


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, 0, 20);


            }
        };

        init.start();

    }


}
