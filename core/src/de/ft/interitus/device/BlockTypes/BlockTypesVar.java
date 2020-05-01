package de.ft.interitus.device.BlockTypes;

import java.util.ArrayList;

public class BlockTypesVar {


    /**
     * 0 -> Arduino
     * 1 -> Raspberry Pi
     * 2 -> Ev3
     * 3 -> Arduino Neopixel
     * 4 -> Arduino RoboArm
     * ... -> Plugins
     */
   public static ArrayList<ArrayList<PlatformSpecificBlock>> blocks = new ArrayList<>();

}
