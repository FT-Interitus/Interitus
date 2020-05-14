package de.ft.interitus.projecttypes;

import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.device.ProgrammableObjekt;

import java.util.ArrayList;

public class ProjektTypes {
    ProgrammableObjekt PO;
    String name;
    ArrayList<PlatformSpecificBlock> projectblocks;

    public ProjektTypes(ProgrammableObjekt PO, String name,ArrayList<PlatformSpecificBlock> blocks) {
        this.projectblocks = blocks;
        this.PO = PO;
        this.name = name;
    }

    public ProgrammableObjekt getPO() {
        return PO;
    }

    public void setPO(ProgrammableObjekt PO) {
        this.PO = PO;
    }

    public String getName() {
        return name;
    }

    public ArrayList<PlatformSpecificBlock> getProjectblocks() {
        return projectblocks;
    }

    public void setProjectblocks(ArrayList<PlatformSpecificBlock> projectblocks) {
        this.projectblocks = projectblocks;
    }

    public void setName(String name) {
        this.name = name;
    }
}
