/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.compiler.Interitus.EV3;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Program;
import de.ft.interitus.compiler.Compiler;
import de.ft.interitus.compiler.Interitus.Arduino.EV3Thread;
import de.ft.interitus.projecttypes.BlockTypes.BlockMode;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.Ev3Block;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.programmsequence.Thread.ThreadBlock;
import de.ft.interitus.projecttypes.ProjectManager;

import java.util.ArrayList;
import java.util.UUID;

public class EV3compiler implements Compiler {
    private int userthreadcounter=0;

    public void vmthread(StringBuilder sb, String threadName,String... insert){
        sb.append("\nvmthread "+threadName+"\n{\n");
        for(int i=0;i<insert.length;i++){
            sb.append("  "+insert[i]+"\n");
        }
        sb.append("\n}");
    }
    public void userthread(StringBuilder sb,String... insert){
        vmthread(sb, "userthread"+userthreadcounter, insert);
        userthreadcounter++;
    }
    public StringBuilder compileSketch(){
        userthreadcounter=0;
        Program.logger.config("Compile ev3");
        StringBuilder Programm = new StringBuilder();

        EV3Thread MainThread=new EV3Thread("Main");
        ArrayList<EV3Thread>userthreads=new ArrayList<>();

        for(Block block:ProjectManager.getActProjectVar().blocks){
            if(block.getBlocktype() instanceof ThreadBlock){
                Block neighbour=block.getRight();
                EV3Thread tempThread = null;
                if(neighbour!=null) {
                    tempThread = new EV3Thread("UserThreadNr" + (userthreads.size() + 1));
                }
                while(neighbour!=null){
                    //Program.logger.config(((Ev3Block) neighbour.getBlocktype().getBlockModis().get(neighbour.getBlocktype().getActBlockModiIndex())).getCode());
                    MainThread.addLine("OBJECT_START(UserThreadNr"+(userthreads.size() + 1)+")");
                    tempThread.addLine(((Ev3Block) neighbour.getBlocktype().getBlockModis().get(neighbour.getBlocktype().getActBlockModiIndex())).getCode());
                    userthreads.add(tempThread);
                    neighbour=neighbour.getRight();
                }
            }
        }

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
