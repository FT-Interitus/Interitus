/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.Math;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.utils.ArrayList;

public class DifferenzMode extends ArduinoBlock {
    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter Summand_1;
    Parameter Summand_2;
    Parameter Ergebnis;

    public DifferenzMode(){
        Summand_1=new Parameter("", AssetLoader.Parameter_first,"Minuend", "Minuend", "", new ParameterType(InitArduino.floatvar, false), true);
        Summand_2=new Parameter("",AssetLoader.Parameter_second,"Subtraend", "Subtraend", "", new ParameterType(InitArduino.floatvar, false), true);
        Ergebnis=new Parameter("",AssetLoader.Parameter_isequal,"Ergebnis", "Unterschied von zwei zahlen", "", new ParameterType(InitArduino.floatvar, true), true);
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
        return "Subtraktion";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.Parameter_minus;
    }

    @Override
    public String getCode() {
        if( parameters.get(2).getDataWires().size()>0){
            return parameters.get(2).getVarName()+ " = ("+parameters.get(0).getParameter()+") - ("+parameters.get(1).getParameter()+");";

        }else {
            return"("+ parameters.get(0).getParameter()+" - "+parameters.get(1).getParameter()+");";
        }
    }

    @Override
    public String getHeaderCode(boolean inserted) {
        return null;
    }

}
