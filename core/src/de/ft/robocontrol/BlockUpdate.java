package de.ft.robocontrol;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import jdk.tools.jaotc.Main;

import java.util.Timer;
import java.util.TimerTask;

public class BlockUpdate extends Thread {
Block block;
public Timer time;

    BlockUpdate(Block block) {
        this.block=block;

    }

    @Override
    public void run() {
        time = new Timer();

        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {



                    System.out.println(block.getIndex()+ " " +de.ft.robocontrol.utils.CheckKollision.checkmousewithcar(block));
                    //System.out.println(block.getIndex());


                            }
        }, 0,50);

    }

}
