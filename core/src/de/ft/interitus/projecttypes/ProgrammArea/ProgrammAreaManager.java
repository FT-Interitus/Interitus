/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.ProgrammArea;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Program;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.ArrayList;

public class ProgrammAreaManager {
    public static void update(){
        Program.logger.config(ProjectManager.getActProjectVar().blocks.getLastObject().getBlockType().blockModis.get(ProjectManager.getActProjectVar().blocks.get(0).getBlockType().actBlockModiIndex).getProgrammArea().toString());
    }
    public static ProgrammArea getDirectProgrammAreaOfBlock(int i){
        return ProjectManager.getActProjectVar().blocks.get(i).getBlockType().blockModis.get(ProjectManager.getActProjectVar().blocks.get(i).getBlockType().actBlockModiIndex).getProgrammArea();
    }
    public static int getProgrammArea(int index){
        int PA=0;


        Block tempblock = ProjectManager.getActProjectVar().blocks.get(index);
        de.ft.interitus.utils.ArrayList<Block> checkedBlocks = new ArrayList<>();

        while (tempblock != null) {
            if(checkedBlocks.contains(tempblock)) {
                checkedBlocks.clear();
                break;
            }
            checkedBlocks.add(tempblock);
            if(tempblock.getLeft()==null) {
                break;
            }



            if(getDirectProgrammAreaOfBlock(tempblock.getIndex())==ProgrammArea.PROGRAMM_AREA_START){
                PA++;
            }
            if(getDirectProgrammAreaOfBlock(tempblock.getIndex())==ProgrammArea.PROGRAMM_AREA_END){
                PA--;
            }

            tempblock = tempblock.getLeft();

        }



        return PA;
    }
}
