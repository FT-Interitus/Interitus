package de.ft.robocontrol.data;

import java.util.ArrayList;

public class VerbindungsSpeicher {
    public static ArrayList<VerbindungsSpeicher> verbundungen = new ArrayList<VerbindungsSpeicher>();
    public String name="";
    public int Board=-1;



    public VerbindungsSpeicher() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
