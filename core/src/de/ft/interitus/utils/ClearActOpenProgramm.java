/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.utils;

import de.ft.interitus.Block.Block;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.Program;
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

                    blockstoclear.removeAll(ProjectManager.getProjectVar(index).visible_blocks);


                    try {
                        while (blockstoclear.size() != 0) {
                            blockstoclear.get(0).delete(true);
                            blockstoclear.remove(0);


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
            while (ProjectManager.getProjectVar(index).visible_blocks.size() != 0) {
                ProjectManager.getProjectVar(index).visible_blocks.get(0).delete(true);
                ProjectManager.getProjectVar(index).visible_blocks.remove(0);


            }

            Program.logger.config("Clearing Finished");

        } catch (Exception e) {
            DisplayErrors.error = e;
            e.printStackTrace();
            System.exit(-121);
        }

        ProjectManager.getProjectVar(index).visible_blocks.clear();

        ProjectManager.getProjectVar(index).blocks.clear();


        ProjectManager.getProjectVar(index).marked_block.clear();





        // DataManager.saved();
        ProjectManager.getProjectVar(index).setFilename("New File");
        ProjectManager.getProjectVar(index).path = "";


        ProjectManager.getProjectVar(index).showleftdocker = false;
        ProjectManager.getProjectVar(index).connetor_offerd_hoverd_block = null;



        //clear.start();
        blockstoclear.clear();
        Var.isclearing = false;


    }
}
