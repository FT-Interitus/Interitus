package de.ft.robocontrol.data;

import de.ft.robocontrol.Block.Devices;

import java.util.ArrayList;

public class VerbindungsSpeicher {
    public static ArrayList<VerbindungsSpeicher> verbundungen = new ArrayList<VerbindungsSpeicher>();
    public String name="";
    public int device= Devices.UNDEFINIERT;



    public VerbindungsSpeicher() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
