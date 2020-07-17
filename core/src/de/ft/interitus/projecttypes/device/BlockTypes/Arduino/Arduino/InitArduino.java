package de.ft.interitus.projecttypes.device.BlockTypes.Arduino.Arduino;

import de.ft.interitus.Block.Interitus.*;
import de.ft.interitus.Block.Interitus.save.DefaultSaveBlockGenerator;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.projecttypes.device.BlockTypes.Arduino.Arduino.programmablauf.Wait;
import de.ft.interitus.projecttypes.device.BlockTypes.Arduino.Arduino.programmablauf.WhileLoopEnd;
import de.ft.interitus.projecttypes.device.BlockTypes.Arduino.Arduino.programmablauf.WhileLoopStart;
import de.ft.interitus.projecttypes.device.BlockTypes.Arduino.Arduino.programmaufbau.LoopBlock;
import de.ft.interitus.projecttypes.device.BlockTypes.Arduino.Arduino.programmaufbau.SetupBlock;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.device.DeviceTypes.Arduino.Arduino.Arduino;
import de.ft.interitus.projecttypes.types.ArduinoCreator;

import java.util.ArrayList;

public class InitArduino {
    static ArrayList<PlatformSpecificBlock> blocks = new ArrayList<>();
    static ProjectTypes arduino = new ProjectTypes(ProgrammingSpace.nativ, new Arduino(), "Arduino-Projekt", blocks, new DefaultBlockGenerator(), new DefaultBlockUpdateGenerator(), new DefaultWireGenerator(), new DefaultWireNodeGenerator(), new DefaultSaveBlockGenerator(), new DefaultBlockVarGenerator(),new ArduinoCreator());


    public static ProjectTypes init() {

        blocks.add(new SetupBlock(arduino));
        blocks.add(new LoopBlock(arduino));
        blocks.add(new Wait(arduino));
        blocks.add(new Debug(arduino));
        blocks.add(new WhileLoopStart(arduino));
        blocks.add(new WhileLoopEnd(arduino));




        return arduino;
    }


}
