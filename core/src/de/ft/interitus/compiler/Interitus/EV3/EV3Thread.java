/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.compiler.Interitus.EV3;

import java.util.ArrayList;
import java.util.Arrays;

public class EV3Thread {
    private String name;
    ArrayList<String>lines=new ArrayList<>();
    public EV3Thread(String name){
        this.name=name;
    }
    public EV3Thread(String name, String... insert){
        this.name=name;
        this.lines.addAll(Arrays.asList(insert));
    }
    public void addLine(String line){
        lines.add(line);
    }
    public StringBuilder getThread(){
        StringBuilder Programm=new StringBuilder();
        Programm.append("\nvmthread "+this.name+"\n{\n");
        for(int i=0;i<this.lines.size();i++){
            Programm.append("  "+this.lines.get(i)+"\n");
        }
        Programm.append("\n}");
        return Programm;
    }
}
