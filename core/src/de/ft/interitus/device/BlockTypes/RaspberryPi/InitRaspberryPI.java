package de.ft.interitus.device.BlockTypes.RaspberryPi;

import de.ft.interitus.device.BlockTypes.Ev3.Wait;
import de.ft.interitus.device.BlockTypes.PlatformSpecificBlock;

import java.util.ArrayList;

public class InitRaspberryPI {
    static ArrayList<PlatformSpecificBlock> platformSpecificBlocks = new ArrayList<>();
    public static ArrayList<PlatformSpecificBlock> init() {
        platformSpecificBlocks.add(new Wait());
        return platformSpecificBlocks;
    }
}
