/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.random;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockMode;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.utils.ArrayList;

public class RandomNumeric extends ArduinoBlock {
    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter min;
    Parameter max;
    Parameter output;
    public RandomNumeric() {
        min = new Parameter("", AssetLoader.Parameter_lowerlimit,"Minimum","","",new ParameterType(InitArduino.floatvar,false,false),true);
        max = new Parameter("", AssetLoader.Parameter_upperlimit,"Maximum","","",new ParameterType(InitArduino.floatvar,false,false),true);
        output = new Parameter("", AssetLoader.Parameter_randomdice,"Output","","",new ParameterType(InitArduino.floatvar,true,false),true);


parameters.add(min);
parameters.add(max);
parameters.add(output);

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
        return "Zahlen-Zufall";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.Parameter_randomdice;
    }

    @Override
    public String getCode() {
        if( parameters.get(2).getDatawire().size()>0){
            return parameters.get(2).getVarName()+ " = "+"random("+parameters.get(0).getParameter()+","+parameters.get(1).getParameter()+");";

        }else {
            return "random("+parameters.get(0).getParameter()+","+parameters.get(1).getParameter()+");";
        }
    }

    @Override
    public String getHeaderCode(boolean inserted) {
        return null;
    }
}
