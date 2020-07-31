package de.ft.interitus.projecttypes.BlockTypes.Arduino;

import de.ft.interitus.Block.Interitus.*;
import de.ft.interitus.Block.Interitus.save.DefaultSaveBlockGenerator;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.compiler.Arduino.ArduinoCompiler;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.projecttypes.BlockTypes.Arduino.actionblocks.SetPinMode;
import de.ft.interitus.projecttypes.BlockTypes.Arduino.actionblocks.digitalWrite;
import de.ft.interitus.projecttypes.BlockTypes.Arduino.programmablauf.Wait;
import de.ft.interitus.projecttypes.BlockTypes.Arduino.programmablauf.WhileLoopEnd;
import de.ft.interitus.projecttypes.BlockTypes.Arduino.programmablauf.WhileLoopStart;
import de.ft.interitus.projecttypes.BlockTypes.Arduino.programmaufbau.LoopBlock;
import de.ft.interitus.projecttypes.BlockTypes.Arduino.programmaufbau.SetupBlock;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.DeviceTypes.Arduino.Arduino.Arduino;

import de.ft.interitus.utils.ArrayList;

public class InitArduino {
    static ArrayList<PlatformSpecificBlock> blocks = new ArrayList<>();
    static ProjectTypes arduino = new ProjectTypes(ProgrammingSpace.nativ, new Arduino(), "Arduino-Projekt", blocks, new DefaultBlockGenerator(), new DefaultBlockUpdateGenerator(), new DefaultWireGenerator(), new DefaultWireNodeGenerator(), new DefaultSaveBlockGenerator(), new DefaultBlockVarGenerator(),new ArduinoFunktions(),new ArduinoCompiler());


    public static ProjectTypes init() {

        blocks.add(new SetupBlock(arduino));
        blocks.add(new LoopBlock(arduino));
        blocks.add(new Wait(arduino));
        blocks.add(new WhileLoopStart(arduino));
        blocks.add(new WhileLoopEnd(arduino));
        blocks.add(new SetPinMode(arduino));
        blocks.add(new digitalWrite(arduino));




        return arduino;
    }


}
