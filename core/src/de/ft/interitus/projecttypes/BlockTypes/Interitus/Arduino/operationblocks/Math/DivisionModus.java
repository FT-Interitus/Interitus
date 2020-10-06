/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.Math;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockModus;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.utils.ArrayList;

public class DivisionModus extends BlockModus implements ArduinoBlock {
    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter Divident;
    Parameter Divisor;
    Parameter Ergebnis;

    public DivisionModus(){
        Divident =new Parameter("", AssetLoader.Parameter_first,"1. Divident", "Der Divident (Die erste zahl)", "", new ParameterType(InitArduino.floatvar, false, false), true);
        Divisor =new Parameter("",AssetLoader.Parameter_second,"2. Divisor", "Der Divisor (Die zweite Zahl)", "", new ParameterType(InitArduino.floatvar, false, false), true);
        Ergebnis=new Parameter("",AssetLoader.Parameter_isequal,"Ergebnis", "Der Quotient (Das Ergebnis)", "", new ParameterType(InitArduino.floatvar, true, false), true);
        parameters.add(Divident);
        parameters.add(Divisor);
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
        return "Division";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.Parameter_Geteilt;
    }

    @Override
    public String getCode() {
        if( parameters.get(2).getDatawire().size()>0){
            return parameters.get(2).getVarName()+ " = "+parameters.get(0).getParameter()+" / "+parameters.get(1).getParameter()+";";

        }else {
            return parameters.get(0).getParameter()+" / "+parameters.get(1).getParameter()+";";
        }
    }

    @Override
    public String getHeaderCode(boolean inserted) {
        return null;
    }
}
