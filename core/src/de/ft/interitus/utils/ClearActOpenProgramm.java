package de.ft.interitus.utils;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockVar;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.Var;
import de.ft.interitus.data.user.changes.DataManager;
import de.ft.interitus.data.user.changes.SaveChanges;

import java.util.ArrayList;

public class ClearActOpenProgramm {


    public static ArrayList<Block> blockstoclear = new ArrayList<>();
    static Thread clear;

    public static void clear() {
        Var.isclearing = true;
        blockstoclear = (ArrayList<Block>) Var.openprojects.get(Var.openprojectindex).blocks.clone();



        clear = new Thread() {
            @Override
            public void run() {

                try {

                    blockstoclear.removeAll(Var.openprojects.get(Var.openprojectindex).visibleblocks);


                    try {
                        while (blockstoclear.size() != 0) {
                            blockstoclear.get(0).delete(true);
                            blockstoclear.remove(0);
                            System.out.println(blockstoclear.size());

                        }
                        Var.isclearing = false;

                    } catch (Exception e) {
                        DisplayErrors.error = e;
                        e.printStackTrace();
                        System.exit(-1);
                    }

                } catch (Exception e) {
                    DisplayErrors.error = e;
                    e.printStackTrace(); //for debug to find errors
                }
            }
        };


        try {
            while (Var.openprojects.get(Var.openprojectindex).visibleblocks.size() != 0) {
                Var.openprojects.get(Var.openprojectindex).visibleblocks.get(0).delete(true);
                Var.openprojects.get(Var.openprojectindex).visibleblocks.remove(0);


            }
            if (Var.verboseoutput) {
                System.out.println("Clearing Finished");
            }
        } catch (Exception e) {
            DisplayErrors.error = e;
            e.printStackTrace();
            System.exit(-121);
        }

        Var.openprojects.get(Var.openprojectindex).visibleblocks.clear();

        Var.openprojects.get(Var.openprojectindex).blocks.clear();

        Var.openprojects.get(Var.openprojectindex).biggestblock = null;
        Var.openprojects.get(Var.openprojectindex).markedblock = null;
        Var.openprojects.get(Var.openprojectindex).ismoving = false;
        Var.openprojects.get(Var.openprojectindex).showduplicat.clear();


        Var.openprojects.get(Var.openprojectindex).uberlapptmitmarkedblock.clear();
        Var.openprojects.get(Var.openprojectindex).blockmitdergrostenuberlappungmitmarkiertemblock = null;

        DataManager.saved();
        DataManager.filename = "New File";
        DataManager.path = "";


        Var.openprojects.get(Var.openprojectindex).wireNodes.clear();
        Var.openprojects.get(Var.openprojectindex).showleftdocker = false;
        Var.openprojects.get(Var.openprojectindex).connetor_offerd_hoverd_block = null;
        Var.openprojects.get(Var.openprojectindex).wires.clear();
        Var.openprojects.get(Var.openprojectindex).movingwires = null;
        Var.openprojects.get(Var.openprojectindex).visiblewires.clear();
        Var.openprojects.get(Var.openprojectindex).visibleWireNodes.clear();
        Var.openprojects.get(Var.openprojectindex).wire_beginn = null;


        //clear.start();
        blockstoclear.clear();
        Var.isclearing = false;


    }
}
