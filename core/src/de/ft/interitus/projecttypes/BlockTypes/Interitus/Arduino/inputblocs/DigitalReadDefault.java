/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.inputblocs;/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */


import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockModi;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.utils.ArrayList;

public class DigitalReadDefault implements BlockModi, ArduinoBlock {
    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter Pin;
    Parameter Output;


    public DigitalReadDefault(){
        Pin=new Parameter("", AssetLoader.Parameter_first,"Pin", "Auszulesender Pin", "", new ParameterType(InitArduino.floatvar, false, false), true);
        Output=new Parameter("",AssetLoader.Parameter_isequal,"Output", "Output", "", new ParameterType(InitArduino.booleanvar, true, false), true);
        parameters.add(Pin);
        parameters.add(Output);

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
        return 120;
    }

    @Override
    public String getname() {
        return "DigitalRead";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.Parameter_minus;
    }

    @Override
    public String getCode() {
        if( parameters.get(1).getDatawire().size()>0){
            return parameters.get(1).getVarName()+ " = "+"digitalRead(" + parameters.get(0).getParameter() + ");";

        }else {
            return "digitalRead(" + parameters.get(0).getParameter() + ");";
        }
    }
}
