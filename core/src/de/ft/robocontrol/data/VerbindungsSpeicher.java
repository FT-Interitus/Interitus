package de.ft.robocontrol.data;

import de.ft.robocontrol.Block.Block;

import java.util.ArrayList;

public class VerbindungsSpeicher {
    public String name;

    public static ArrayList<VerbindungsSpeicher> verbundungen = new ArrayList<VerbindungsSpeicher>();


    public VerbindungsSpeicher(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
