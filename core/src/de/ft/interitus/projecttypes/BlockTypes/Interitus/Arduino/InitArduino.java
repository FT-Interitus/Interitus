/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino;

import com.badlogic.gdx.graphics.Color;
import de.ft.interitus.Block.Interitus.*;
import de.ft.interitus.Block.Interitus.save.DefaultSaveBlockGenerator;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.compiler.Interitus.Arduino.ArduinoCompiler;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.actionblocks.SetPinMode;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.actionblocks.digitalWrite;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.AdditionsBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.Wait;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.WhileLoopEnd;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.WhileLoopStart;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmaufbau.LoopBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmaufbau.SetupBlock;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ParameterVariableType;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.utils.ArrayList;

public class InitArduino {
    static ArrayList<PlatformSpecificBlock> blocks = new ArrayList<>();
    static ProjectTypes arduino = new ProjectTypes(ProgrammingSpace.nativ, "Arduino-Projekt", blocks, new DefaultBlockGenerator(), new DefaultBlockUpdateGenerator(), new DefaultWireGenerator(), new DefaultWireNodeGenerator(), new DefaultSaveBlockGenerator(), new DefaultBlockVarGenerator(), new ArduinoFunktions(), new ArduinoCompiler());


    public static ParameterVariableType floatvar;
    public static ParameterVariableType stringvar;


    public static ProjectTypes init() {

        floatvar = new ParameterVariableType("float", AssetLoader.Plug_ZahlParameter,new Color(23f/255f,141f/255f,209f/255f,1f),"int");
        stringvar = new ParameterVariableType("String", AssetLoader.Plug_ZahlParameter,new Color(156f/255f,19f/255f,19f/255f,1f),"char[]");
        blocks.add(new SetupBlock(arduino));
        blocks.add(new LoopBlock(arduino));
        blocks.add(new Wait(arduino));
        blocks.add(new WhileLoopStart(arduino));
        blocks.add(new WhileLoopEnd(arduino));
        blocks.add(new SetPinMode(arduino));
        blocks.add(new digitalWrite(arduino));
        blocks.add(new TestOutput(arduino));
        blocks.add(new AdditionsBlock(arduino));


        return arduino;
    }


}
