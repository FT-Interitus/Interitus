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

public class AbsolutMode extends ArduinoBlock  {
    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter value;

    Parameter Ergebnis;

    public AbsolutMode(){
        value =new Parameter("", AssetLoader.Parameter_first,"Wert", "", "", new ParameterType(InitArduino.floatvar, false), true);
        Ergebnis=new Parameter("",AssetLoader.Parameter_isequal,"Ergebnis", "Das Ergebnis der Multiplikation (Produkt)", "", new ParameterType(InitArduino.floatvar, true), true);
        parameters.add(value);

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
        return "Absoluter Wert";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.Parameter_abs;
    }

    @Override
    public String getCode() {
        if( parameters.get(1).getDataWires().size()>0){
            return parameters.get(1).getVarName()+ " = "+"abs("+parameters.get(0).getParameter()+")"+";";

        }else {
            return "abs("+parameters.get(0).getParameter()+")"+";";
        }
    }
    @Override
    public String getHeaderCode(boolean inserted) {
        return null;
    }

}
