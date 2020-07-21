package de.ft.interitus.utils;

import de.ft.interitus.Block.Block;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.Programm;
import de.ft.interitus.Var;

import de.ft.interitus.projecttypes.ProjectManager;

import java.util.ArrayList;

public class ClearActOpenProgramm {


    public static ArrayList<Block> blockstoclear = new ArrayList<>();
    static Thread clear;

    public static void clear() {
        Var.isclearing = true;
        blockstoclear = (ArrayList<Block>) ProjectManager.getActProjectVar().blocks.clone();


        clear = new Thread() {
            @Override
            public void run() {

                try {

                    blockstoclear.removeAll(ProjectManager.getActProjectVar().visibleblocks);


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
            while (ProjectManager.getActProjectVar().visibleblocks.size() != 0) {
                ProjectManager.getActProjectVar().visibleblocks.get(0).delete(true);
                ProjectManager.getActProjectVar().visibleblocks.remove(0);


            }

                Programm.logger.config("Clearing Finished");

        } catch (Exception e) {
            DisplayErrors.error = e;
            e.printStackTrace();
            System.exit(-121);
        }

        ProjectManager.getActProjectVar().visibleblocks.clear();

        ProjectManager.getActProjectVar().blocks.clear();

        ProjectManager.getActProjectVar().biggestblock = null;
        ProjectManager.getActProjectVar().markedblock = null;
        ProjectManager.getActProjectVar().ismoving = false;
        ProjectManager.getActProjectVar().showduplicat.clear();


        ProjectManager.getActProjectVar().uberlapptmitmarkedblock.clear();
        ProjectManager.getActProjectVar().blockmitdergrostenuberlappungmitmarkiertemblock = null;

       // DataManager.saved();
        ProjectManager.getActProjectVar().filename = "New File";
        ProjectManager.getActProjectVar().path = "";


        ProjectManager.getActProjectVar().wireNodes.clear();
        ProjectManager.getActProjectVar().showleftdocker = false;
        ProjectManager.getActProjectVar().connetor_offerd_hoverd_block = null;
        ProjectManager.getActProjectVar().wires.clear();
        ProjectManager.getActProjectVar().movingwires = null;
        ProjectManager.getActProjectVar().visiblewires.clear();
        ProjectManager.getActProjectVar().visibleWireNodes.clear();
        ProjectManager.getActProjectVar().wire_beginn = null;


        //clear.start();
        blockstoclear.clear();
        Var.isclearing = false;


    }
}
