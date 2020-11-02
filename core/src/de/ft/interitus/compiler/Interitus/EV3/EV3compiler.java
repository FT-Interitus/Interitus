/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.compiler.Interitus.EV3;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Program;
import de.ft.interitus.compiler.Compiler;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.Ev3Block;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.programmsequence.Thread.ThreadBlock;
import de.ft.interitus.projecttypes.ProjectManager;

import java.util.ArrayList;

public class EV3compiler implements Compiler {


    public StringBuilder compileSketch(){
        Program.logger.config("Compile ev3");
        StringBuilder Programm = new StringBuilder();

        EV3Thread MainThread=new EV3Thread("Main");
        ArrayList<EV3Thread>userthreads=new ArrayList<>();

        for(Block block:ProjectManager.getActProjectVar().blocks){
            if(block.getBlocktype() instanceof ThreadBlock){
                Block neighbour=block.getRight();
                EV3Thread tempThread = null;
                if(neighbour!=null){
                tempThread = new EV3Thread("UserThreadNr" + (userthreads.size() + 1));
                while(neighbour!=null) {
                    //Program.logger.config(((Ev3Block) neighbour.getBlocktype().getBlockModis().get(neighbour.getBlocktype().getActBlockModiIndex())).getCode());
                    tempThread.addLine(((Ev3Block) neighbour.getBlocktype().getBlockModis().get(neighbour.getBlocktype().getActBlockModiIndex())).getCode());
                    neighbour = neighbour.getRight();
                }
                    MainThread.addLine("OBJECT_START(UserThreadNr" + (userthreads.size() + 1) + ")");
                    userthreads.add(tempThread);


            }
        }}

        Programm.append(MainThread.getThread());
        for(EV3Thread thread:userthreads){
            Programm.append(thread.getThread());
        }

        Program.logger.config("\n"+Programm.toString());
        return Programm;
    }
    @Override
    public String compile() {

        return compileSketch().toString();
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
