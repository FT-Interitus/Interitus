package de.ft.interitus.projecttypes;

import de.ft.interitus.device.ProgrammableObjekt;

public class ProjektTypes {
    ProgrammableObjekt PO;
    String name;

    public ProjektTypes(ProgrammableObjekt PO, String name) {
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

    public void setName(String name) {
        this.name = name;
    }
}
