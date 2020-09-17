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
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.utils.ArrayList;

public class AdditionalModi extends BlockModi implements ArduinoBlock {
    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter Summand_1;
    Parameter Summand_2;
    Parameter Ergebnis;

    public AdditionalModi(){
        Summand_1=new Parameter("", AssetLoader.Parameter_first,"1. Summand", "erster Summand", "", new ParameterType(InitArduino.floatvar, false, false), true);
        Summand_2=new Parameter("",AssetLoader.Parameter_second,"2. Summand", "zweiter Summand", "", new ParameterType(InitArduino.floatvar, false, false), true);
        Ergebnis=new Parameter("",AssetLoader.Parameter_isequal,"Ergebnis", "Das Ergebnis der Summe (Wert der Summe)", "", new ParameterType(InitArduino.floatvar, true, false), true);
        parameters.add(Summand_1);
        parameters.add(Summand_2);
        parameters.add(Ergebnis);

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
        return "Addition";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.Parameter_plus;
    }

    @Override
    public String getCode() {
        if( parameters.get(2).getDatawire().size()>0){
            return parameters.get(2).getVarName()+ " = "+parameters.get(0).getParameter()+" + "+parameters.get(1).getParameter()+";";

        }else {
            return parameters.get(0).getParameter()+" + "+parameters.get(1).getParameter()+";";
        }
    }
    @Override
    public String getHeaderCode(boolean inserted) {
        return null;
    }

}
