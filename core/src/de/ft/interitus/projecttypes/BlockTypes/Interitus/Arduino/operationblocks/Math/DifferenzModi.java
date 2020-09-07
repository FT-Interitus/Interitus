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

public class DifferenzModi implements BlockModi, ArduinoBlock {
    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter Summand_1;
    Parameter Summand_2;
    Parameter Ergebnis;

    public DifferenzModi(){
        Summand_1=new Parameter("", AssetLoader.Parameter_erstens,"Minuend", "Minuend", "", new ParameterType(InitArduino.floatvar, false, false), true);
        Summand_2=new Parameter("",AssetLoader.Parameter_zweitens,"Subtraend", "Subtraend", "", new ParameterType(InitArduino.floatvar, false, false), true);
        Ergebnis=new Parameter("",AssetLoader.Parameter_istgleich,"Ergebnis", "Unterschied von zwei zahlen", "", new ParameterType(InitArduino.floatvar, true, false), true);
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
        return "Differenz Block";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.Parameter_Minus;
    }

    @Override
    public String getCode() {
        if( parameters.get(2).getDatawire().size()>0){
            return parameters.get(2).getVarName()+ " = "+parameters.get(0).getParameter()+" - "+parameters.get(1).getParameter()+";";

        }else {
            return parameters.get(0).getParameter()+" - "+parameters.get(1).getParameter()+";";
        }
    }
}