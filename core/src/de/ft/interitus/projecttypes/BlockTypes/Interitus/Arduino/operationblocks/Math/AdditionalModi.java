/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.Math;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockModi;
import de.ft.interitus.projecttypes.BlockTypes.BlockTopParameter;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.utils.ArrayList;

public class AdditionalModi implements BlockModi, ArduinoBlock {
    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter Summand_1;
    Parameter Summand_2;
    Parameter Ergebnis;

    public AdditionalModi(){
        Summand_1=new Parameter("", AssetLoader.Parameter_IO,"1. Summand", "erster Summand", "", new ParameterType(InitArduino.floatvar, false, false), true);
        Summand_2=new Parameter("",AssetLoader.Plug_IntParameter,"2. Summand", "zweiter Summand", "", new ParameterType(InitArduino.floatvar, false, false), true);
        Ergebnis=new Parameter("",AssetLoader.Plug_IntParameter,"Ergebnis", "Das was bei einer Addition meistens raus kommt", "", new ParameterType(InitArduino.floatvar, true, false), true);
        parameters.add(Summand_1);
        parameters.add(Summand_2);
        parameters.add(Ergebnis);

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
        return 200;
    }

    @Override
    public String getname() {
        return "addidionalModi";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.connector_offerd;
    }

    @Override
    public String getCode() {
        if( parameters.get(2).getDatawire().size()>0){
            return parameters.get(2).getVarName()+ " = "+parameters.get(0).getParameter()+" + "+parameters.get(1).getParameter()+";";

        }else {
            return parameters.get(0).getParameter()+" + "+parameters.get(1).getParameter()+";";
        }
    }
}
