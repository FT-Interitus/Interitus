package de.ft.interitus.projecttypes.device.BlockTypes;

import de.ft.interitus.plugin.PluginGateway;
import de.ft.interitus.projecttypes.device.BlockTypes.Arduino.Arduino.InitArduino;
import de.ft.interitus.projecttypes.device.BlockTypes.Arduino.NeopxielController.InitNeoPixelController;
import de.ft.interitus.projecttypes.device.BlockTypes.Arduino.RoboArm.InitRoboArm;
import de.ft.interitus.projecttypes.device.BlockTypes.Ev3.InitEv3;
import de.ft.interitus.projecttypes.device.BlockTypes.RaspberryPi.InitRaspberryPI;

public class Init {

    public static void initBlocks() {

        ProjectTypesVar.projectTypes.add(InitArduino.init());
        ProjectTypesVar.projectTypes.add(InitRaspberryPI.init());
        ProjectTypesVar.projectTypes.add(InitEv3.init());
        ProjectTypesVar.projectTypes.add(InitNeoPixelController.init());
        ProjectTypesVar.projectTypes.add(InitRoboArm.init());


        ProjectTypesVar.projectTypes.addAll(PluginGateway.pluginprojekttypes);

    }
}
