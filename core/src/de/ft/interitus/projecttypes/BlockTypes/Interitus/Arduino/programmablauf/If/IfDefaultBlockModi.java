/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.If;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockModi;
import de.ft.interitus.projecttypes.BlockTypes.BlockTopParameter;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.utils.ArrayList;

public class IfDefaultBlockModi implements BlockModi, ArduinoBlock {
    ArrayList<Parameter> parameters = new ArrayList<>();
    public IfDefaultBlockModi() {

        parameters.add(new Parameter("",AssetLoader.Parameter_first,"Bedingung","","boolean",new ParameterType(InitArduino.booleanvar,false,true).setSelectables(new String[]{"true","false"}),true));

    }

    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return parameters;
    }

    @Override
    public ArrayList<BlockTopParameter> getblocktopparamter() {
        return null;
    }

    @Override
    public int getWidth() {
        return 100;
    }

    @Override
    public String getname() {
        return "If";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.Parameter_if;
    }

    @Override
    public String getCode() {
        return "if("+parameters.get(0).getParameter()+") {";
    }
}
