package de.ft.interitus.device.BlockTypes.Ev3;

import de.ft.interitus.device.BlockTypes.PlatformSpecificBlock;

import java.util.ArrayList;

public class InitEv3 {
   static ArrayList<PlatformSpecificBlock> platformSpecificBlocks = new ArrayList<>();
    public static ArrayList<PlatformSpecificBlock> init() {
        return platformSpecificBlocks;
    }
}
