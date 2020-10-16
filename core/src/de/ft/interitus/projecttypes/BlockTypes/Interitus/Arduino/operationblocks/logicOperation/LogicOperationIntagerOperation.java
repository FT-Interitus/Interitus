/*
 * Copyright (c) 2020. 
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.logicOperation;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.utils.ArrayList;

public class LogicOperationIntagerOperation extends  ArduinoBlock {
    Parameter value1;
    Parameter operation;
    Parameter value2;
    Parameter output;
    public LogicOperationIntagerOperation(){
        value1 =new Parameter("", AssetLoader.Parameter_first,"Value 1", "", "", new ParameterType(InitArduino.floatvar, false, false), true);
        operation=new Parameter("<", AssetLoader.Parameter_IO, "Operation","", "",new ParameterType(InitArduino.stringvar, false, true).setSelectables(new String[]{"<",">","==","<=",">=","!="}),true);
        value2=new Parameter("", AssetLoader.Parameter_second,"Value 2", "", "", new ParameterType(InitArduino.floatvar, false, false), true);
        output=new Parameter("",AssetLoader.Parameter_isequal,"Output", "Output", "", new ParameterType(InitArduino.booleanvar, true, false), true);

        parameter.add(value1);
        parameter.add(operation);
        parameter.add(value2);
        parameter.add(output);
    }
    ArrayList<Parameter>parameter=new ArrayList<>();
    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return parameter;
    }

    @Override
    public BlockSettings getblocksettings() {
        return null;
    }

    @Override
    public int getWidth() {
        return 170;
    }

    @Override
    public String getname() {
        return "IntegerOperation";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.img_startbutton;
    }

    @Override
    public String getCode() {
        if(parameter.get(3).getDataWires().size()>0)  {

            return parameter.get(3).getParameter() + " = " + "("+parameter.get(0).getParameter()+parameter.get(1).getParameter()+parameter.get(2).getParameter()+");";


        }else{
            return "("+parameter.get(0).getParameter()+parameter.get(1).getParameter()+parameter.get(2).getParameter()+");";
        }    }


    @Override
    public String getHeaderCode(boolean inserted) {
        return null;
    }

}
