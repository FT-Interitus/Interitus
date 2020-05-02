package de.ft.interitus.device.BlockTypes;

import de.ft.interitus.device.BlockTypes.Arduino.Arduino.InitArduino;
import de.ft.interitus.device.BlockTypes.Arduino.NeopxielController.InitNeoPixelController;
import de.ft.interitus.device.BlockTypes.Arduino.RoboArm.InitRoboArm;
import de.ft.interitus.device.BlockTypes.Ev3.InitEv3;
import de.ft.interitus.device.BlockTypes.RaspberryPi.InitRaspberryPI;
import de.ft.interitus.plugin.PluginManagerHandler;

public class Init {

    public static void initBlocks() {

        BlockTypesVar.blocks.add( InitArduino.init());
        BlockTypesVar.blocks.add( InitRaspberryPI.init());
        BlockTypesVar.blocks.add( InitEv3.init());
        BlockTypesVar.blocks.add(InitNeoPixelController.init());
        BlockTypesVar.blocks.add(InitRoboArm.init());
        for(int i=0;i< PluginManagerHandler.platformSpecificBlock.size();i++) {

            BlockTypesVar.blocks.add( PluginManagerHandler.platformSpecificBlock.get(i));
        }
    }
}
