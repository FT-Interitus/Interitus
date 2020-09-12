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

public class SquareRootModi implements BlockModi, ArduinoBlock {
    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter Summand_1;

    Parameter Ergebnis;

    public SquareRootModi(){
        Summand_1=new Parameter("", AssetLoader.Parameter_first,"Wurzel", "Wurzel", "", new ParameterType(InitArduino.floatvar, false, false), true);
        Ergebnis=new Parameter("",AssetLoader.Parameter_isequal,"Ergebnis", "Unterschied von zwei zahlen", "", new ParameterType(InitArduino.floatvar, true, false), true);
        parameters.add(Summand_1);

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
        return 125;
    }

    @Override
    public String getname() {
        return "Wurzel ziehen";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.Parameter_squareroot;
    }

    @Override
    public String getCode() {
        if( parameters.get(1).getDatawire().size()>0){
            return parameters.get(1).getVarName()+ " = "+"sqrt("+parameters.get(0).getParameter()+");";

        }else {
            return "sqrt("+parameters.get(0).getParameter()+");";
        }
    }
    @Override
    public String getHeaderCode() {
        return null;
    }
}
