/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.compiler.Interitus.EV3;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Program;
import de.ft.interitus.compiler.Compiler;
import de.ft.interitus.projecttypes.BlockTypes.BlockMode;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.Ev3Block;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.programmsequence.Thread.ThreadBlock;
import de.ft.interitus.projecttypes.ProjectManager;

public class EV3compiler implements Compiler {
    public String compileSketch(){
        Program.logger.config("Compile ev3");
        StringBuilder Programm = new StringBuilder();
        for(Block block:ProjectManager.getActProjectVar().blocks){
            if(block.getBlocktype() instanceof ThreadBlock){
                Block neighbour=block.getRight();
                while(neighbour!=null){
                    //Program.logger.config(((Ev3Block) neighbour.getBlocktype().getBlockModis().get(neighbour.getBlocktype().getActBlockModiIndex())).getCode());

                    neighbour=neighbour.getRight();
                }
            }
        }
        return Programm.toString();
    }
    @Override
    public String compile() {

        return compileSketch();
    }

    @Override
    public boolean compileandrun() {
        compileSketch();
        return true;
    }

    @Override
    public String getCompilerVersion() {
        return null;
    }

    @Override
    public void interupt() {

    }
}
