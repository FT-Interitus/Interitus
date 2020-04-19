package de.ft.interitus.utils;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockVar;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.Var;
import de.ft.interitus.data.user.changes.DataManager;
import de.ft.interitus.data.user.changes.SaveChanges;
import de.ft.interitus.displayErrors;

import java.util.ArrayList;

public class ClearActOpenProgramm {


    public static ArrayList<Block> blockstoclear = new ArrayList<>();
    static Thread clear;

    public static void clear() {
        Var.isclearing = true;
        blockstoclear = (ArrayList<Block>) BlockVar.blocks.clone();

        //TODO stop ThreadManager Thread

        clear = new Thread() {
            @Override
            public void run() {

                try {

                    blockstoclear.removeAll(BlockVar.visibleblocks);


                    try {
                        while (blockstoclear.size() != 0) {
                            blockstoclear.get(0).delete(true);
                            blockstoclear.remove(0);
                            System.out.println(String.valueOf(blockstoclear.size()));

                        }
                        Var.isclearing = false;

                    } catch (Exception e) {
                        displayErrors.error = e;
                        e.printStackTrace();
                        System.exit(-1);
                    }

                }catch (Exception e) {
                    displayErrors.error = e;
                    e.printStackTrace(); //for debug to find errors
                }
            }
        };


        try {
            while (BlockVar.visibleblocks.size() != 0) {
                BlockVar.visibleblocks.get(0).delete(true);
                BlockVar.visibleblocks.remove(0);


            }
            System.out.println("Clearing Finished");
        } catch (Exception e) {
            displayErrors.error = e;
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
