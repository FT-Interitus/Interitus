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
import de.ft.interitus.projecttypes.Addons.Interitus.Arduino.NeoPixel;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.actionblocks.setpinmode.SetPinMode;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.actionblocks.digitalwrite.digitalWrite;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.inputblocs.DigitalRead;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.Math.MathBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.If.If;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.Wait.Wait;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.WhileLoopEnd;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.WhileLoopStart;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmaufbau.LoopBlock.LoopBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmaufbau.SetupBlock.SetupBlock;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.BlockTypes.ProjectTypesVar;
import de.ft.interitus.projecttypes.ParameterVariableType;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.utils.ArrayList;

public class InitArduino {
    static ArrayList<PlatformSpecificBlock> blocks = new ArrayList<>();
   public static ProjectTypes arduino = new ProjectTypes(ProgrammingSpace.nativ, "Arduino-Projekt", blocks, new DefaultBlockGenerator(), new DefaultBlockUpdateGenerator(), new DefaultWireGenerator(), new DefaultWireNodeGenerator(), new DefaultSaveBlockGenerator(), new DefaultBlockVarGenerator(), new ArduinoFunktions(), new ArduinoCompiler());


    public static ParameterVariableType floatvar;
    public static ParameterVariableType stringvar;
    public static ParameterVariableType booleanvar;


    public static ProjectTypes init() {



        floatvar = new ParameterVariableType("float", AssetLoader.Plug_IntParameter,new Color(23f/255f,141f/255f,209f/255f,1f),"int");
        stringvar = new ParameterVariableType("String", AssetLoader.Plug_StringParameter,new Color(156f/255f,19f/255f,19f/255f,1f),"char[]");
        booleanvar = new ParameterVariableType("boolean", AssetLoader.Plug_BooleanParameter,new Color(245f/255f,169f/255f,56f/255f,1f));
        blocks.add(new SetupBlock(arduino,null));
        blocks.add(new LoopBlock(arduino,null));
        blocks.add(new Wait(arduino,null));
        //blocks.add(new WhileLoopStart(arduino));
        //blocks.add(new WhileLoopEnd(arduino));
        blocks.add(new SetPinMode(arduino,null));
        blocks.add(new digitalWrite(arduino,null));
        blocks.add(new DigitalRead(arduino,null));
        blocks.add(new MathBlock(arduino,null));
        blocks.add(new If(arduino,null));



        return arduino;
    }


}
