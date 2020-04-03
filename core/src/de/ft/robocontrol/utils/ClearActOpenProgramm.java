package de.ft.robocontrol.utils;

import com.badlogic.gdx.utils.Logger;
import de.ft.robocontrol.Block.Block;
import de.ft.robocontrol.Block.BlockVar;
import de.ft.robocontrol.MainGame;
import de.ft.robocontrol.Var;
import de.ft.robocontrol.data.user.changes.DataManager;
import de.ft.robocontrol.data.user.changes.SaveChanges;

import java.util.ArrayList;

public class ClearActOpenProgramm {


    public static ArrayList<Block> blockstoclear = new ArrayList<Block>();
    static Thread clear;

    public static void clear() {
        Var.isclearing = true;
        blockstoclear = (ArrayList<Block>) BlockVar.blocks.clone();

        //TODO stop ThreadManager Thread

        clear = new Thread() {
            @Override
            public void run() {

                blockstoclear.removeAll(BlockVar.visibleblocks);


                try {
                    while (blockstoclear.size() != 0) {
                        blockstoclear.get(0).delete(true);
                        blockstoclear.remove(0);
                       MainGame.logger.finer(String.valueOf(blockstoclear.size()));

                    }
                    Var.isclearing = false;

                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(-1);
                }


            }
        };


        try {
            while (BlockVar.visibleblocks.size() != 0) {
                BlockVar.visibleblocks.get(0).delete(true);
                BlockVar.visibleblocks.remove(0);


            }
            MainGame.logger.info("Clearing Finished");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-121);
        }

        BlockVar.visibleblocks.clear();

        BlockVar.blocks.clear();

        BlockVar.biggestblock = null;
        BlockVar.markedblock = null;
        BlockVar.ismoving = false;
        BlockVar.showduplicat.clear();


        BlockVar.uberlapptmitmarkedblock.clear();
        BlockVar.blockmitdergrostenuberlappungmitmarkiertemblock = null;

        DataManager.saved();
        DataManager.filename = "New File";
        DataManager.path = "";


        SaveChanges.clearstacks();
        //clear.start();
        blockstoclear.clear();
        Var.isclearing = false;

    }
}
