/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.variable;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockMode;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.utils.ArrayList;

public class WriteValue extends ArduinoBlock {


    ArrayList<Parameter> parameterArrayList = new ArrayList<>();
    BlockSettings blockSettings = new BlockSettings();

    Parameter value;


    public WriteValue() {

        value = new Parameter("", AssetLoader.Parameter_first,"Wert","","", new ParameterType(InitArduino.floatvar,false,false),true);

        parameterArrayList.add(value);

        blockSettings.setSettings("");

    }

    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return parameterArrayList;
    }

    @Override
    public BlockSettings getblocksettings() {


        return blockSettings;
    }

    @Override
    public int getWidth() {
        return 75;
    }

    @Override
    public String getname() {
        return "Variable Schreiben";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.Parameter_a;
    }

    @Override
    public String getCode() {
        return blockSettings.getSettings() + " = "+ value.getParameter()+";";
    }


    @Override
    public String getHeaderCode(boolean inserted) {
        return null;
    }
}
