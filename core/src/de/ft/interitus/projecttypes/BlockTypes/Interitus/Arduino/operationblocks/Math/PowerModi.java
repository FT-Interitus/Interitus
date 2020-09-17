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

public class PowerModi extends BlockModi implements ArduinoBlock {
    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter Basis;
    Parameter Exponent;
    Parameter Ergebnis;

    public PowerModi(){
        Basis =new Parameter("", AssetLoader.Parameter_first,"Basis", "", "", new ParameterType(InitArduino.floatvar, false, false), true);
        Exponent =new Parameter("",AssetLoader.Parameter_second,"Exponent", "", "", new ParameterType(InitArduino.floatvar, false, false), true);
        Ergebnis=new Parameter("",AssetLoader.Parameter_isequal,"Ergebnis", "", "", new ParameterType(InitArduino.floatvar, true, false), true);
        parameters.add(Basis);
        parameters.add(Exponent);
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
        return "Potenz";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.Parameter_Geteilt;
    }

    @Override
    public String getCode() {
        if( parameters.get(2).getDatawire().size()>0){
            return parameters.get(2).getVarName()+ " = "+"pow("+parameters.get(0).getParameter()+","+parameters.get(1).getParameter()+");";

        }else {
            return "pow("+parameters.get(0).getParameter()+","+parameters.get(1).getParameter()+");";
        }
    }

    @Override
    public String getHeaderCode(boolean inserted) {
        return null;
    }
}
