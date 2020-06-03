package de.ft.interitus.projecttypes.device.BlockTypes.RaspberryPi;

import de.ft.interitus.Block.Interitus.DefaultBlockGenerator;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.projecttypes.device.BlockTypes.Ev3.Wait;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.device.DeviceTypes.Ev3;

import java.util.ArrayList;

public class InitRaspberryPI {
    static ArrayList<PlatformSpecificBlock> platformSpecificBlocks = new ArrayList<>();
    public static ProjectTypes init() {

        platformSpecificBlocks.add(new Wait());

        return new ProjectTypes(new Ev3(),"RaspberryPi-Projekt",platformSpecificBlocks,new DefaultBlockGenerator());
    }
}
