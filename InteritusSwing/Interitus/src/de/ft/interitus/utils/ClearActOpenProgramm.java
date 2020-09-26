/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.utils;

import de.ft.interitus.Block.Block;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.Programm;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.ProjectManager;

public class ClearActOpenProgramm {


    public static ArrayList<Block> blockstoclear = new ArrayList<>();
    static Thread clear;

    public static void clear(int index) {
        Var.isclearing = true;
        blockstoclear = (ArrayList<Block>) ProjectManager.getProjectVar(index).blocks.clone();


        clear = new Thread() {
            @Override
            public void run() {

                try {

                    blockstoclear.removeAll(ProjectManager.getProjectVar(index).visibleblocks);


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
            while (ProjectManager.getProjectVar(index).visibleblocks.size() != 0) {
                ProjectManager.getProjectVar(index).visibleblocks.get(0).delete(true);
                ProjectManager.getProjectVar(index).visibleblocks.remove(0);


            }

            Programm.logger.config("Clearing Finished");

        } catch (Exception e) {
            DisplayErrors.error = e;
            e.printStackTrace();
            System.exit(-121);
        }

        ProjectManager.getProjectVar(index).visibleblocks.clear();

        ProjectManager.getProjectVar(index).blocks.clear();

        ProjectManager.getProjectVar(index).biggestblock = null;
        ProjectManager.getProjectVar(index).markedblock = null;
        ProjectManager.getProjectVar(index).ismoving = false;
        ProjectManager.getProjectVar(index).showduplicat.clear();


        ProjectManager.getProjectVar(index).uberlapptmitmarkedblock.clear();
        ProjectManager.getProjectVar(index).blockmitdergrostenuberlappungmitmarkiertemblock = null;

        // DataManager.saved();
        ProjectManager.getProjectVar(index).setFilename("New File");
        ProjectManager.getProjectVar(index).path = "";


        ProjectManager.getProjectVar(index).wireNodes.clear();
        ProjectManager.getProjectVar(index).showleftdocker = false;
        ProjectManager.getProjectVar(index).connetor_offerd_hoverd_block = null;
        ProjectManager.getProjectVar(index).wires.clear();
        ProjectManager.getProjectVar(index).movingwires = null;
        ProjectManager.getProjectVar(index).visiblewires.clear();
        ProjectManager.getProjectVar(index).visibleWireNodes.clear();
        ProjectManager.getProjectVar(index).wire_beginn = null;


        //clear.start();
        blockstoclear.clear();
        Var.isclearing = false;


    }
}
