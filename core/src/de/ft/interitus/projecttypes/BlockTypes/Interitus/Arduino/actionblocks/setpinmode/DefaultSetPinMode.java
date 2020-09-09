/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.actionblocks.setpinmode;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockModi;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.utils.ArrayList;

public class DefaultSetPinMode implements BlockModi, ArduinoBlock {
    ArrayList<Parameter>parameters=new ArrayList<>();
    Parameter pin;
    Parameter mode;
    public DefaultSetPinMode(){
        pin = new Parameter("", AssetLoader.Parameter_Pin, "Pin", "", null,new ParameterType(InitArduino.floatvar,false,false), true);
        String[] selecteables = new String[2];
        selecteables[0] = "INPUT";
        selecteables[1] = "OUTPUT";
        mode = new Parameter(selecteables[1], AssetLoader.Parameter_IO, "Mode(I/O)", "", null,new ParameterType(InitArduino.floatvar,false,true).setSelectables(selecteables), true);


        parameters.add(pin);
        parameters.add(mode);
    }
    @Override
    public ArrayList<Parameter> getBlockParameter() {


        return parameters;
    }

    @Override
    public BlockSettings getblocksettings() {
        return null;
    }

    @Override
    public int getWidth() {
        return 150;
    }

    @Override
    public String getname() {
        return "DefaultSetPinMode";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.connector;
    }

    @Override
    public String getCode() {
        return  "pinMode(" + this.parameters.get(0).getParameter() + "," + this.parameters.get(1).getParameter() + ");";
    }
}
