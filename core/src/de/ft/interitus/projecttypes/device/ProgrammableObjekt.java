package de.ft.interitus.projecttypes.device;

public interface ProgrammableObjekt {
    String getName();

    /**
     * Will be called every Frame to update Device
     */
    void update();
}
