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

public class ExpandedMath implements BlockModi, ArduinoBlock {
    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter a;
    Parameter b;
    Parameter c;
    Parameter d;
    Parameter Ergebnis;
    BlockSettings blockSettings = new BlockSettings();

    public ExpandedMath(){
        a =new Parameter("", AssetLoader.Parameter_first,"A", "Erster Parameter", "", new ParameterType(InitArduino.floatvar, false, false), true);
        b =new Parameter("",AssetLoader.Parameter_second,"B", "Zweiter Parameter", "", new ParameterType(InitArduino.floatvar, false, false), true);
        c =new Parameter("",AssetLoader.Parameter_third,"C", "Dritter Parameter", "", new ParameterType(InitArduino.floatvar, false, false), true);
        d =new Parameter("",AssetLoader.Parameter_fourth,"D", "Vierter Parameter", "", new ParameterType(InitArduino.floatvar, false, false), true);
        Ergebnis=new Parameter("",AssetLoader.Parameter_isequal,"Ergebnis", "Das Ergebnis", "", new ParameterType(InitArduino.floatvar, true, false), true);
        parameters.add(a);
        parameters.add(b);
        parameters.add(c);
        parameters.add(d);
        parameters.add(Ergebnis);
        blockSettings.setSettings("a+b-c/d");

    }
    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return parameters;
    }

    @Override
    public BlockSettings getblocksettings() {
        return blockSettings;
    }

    @Override
    public int getWidth() {
        return 200;
    }

    @Override
    public String getname() {
        return "Erweiterte Funktion";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.Parameter_Geteilt;
    }

    @Override
    public String getCode() {
        if( parameters.get(4).getDatawire().size()>0){
            return parameters.get(4).getVarName()+ " = "+"("+blockSettings.getSettings().replace("a", (CharSequence) a.getParameter()).replace("b", (CharSequence) b.getParameter()).replace("c", (CharSequence) c.getParameter()).replace("d", (CharSequence) d.getParameter())+");";

        }else {
            return "("+blockSettings.getSettings().replace("a", (CharSequence) a.getParameter()).replace("b", (CharSequence) b.getParameter()).replace("c", (CharSequence) c.getParameter()).replace("d", (CharSequence) d.getParameter())+");";
        }
    }
}
