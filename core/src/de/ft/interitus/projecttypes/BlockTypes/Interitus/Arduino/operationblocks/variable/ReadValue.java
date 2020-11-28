/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.variable;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockMode;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoVariable;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectVar;
import de.ft.interitus.utils.ArrayList;

public class ReadValue extends ArduinoBlock {


    ArrayList<Parameter> parameterArrayList = new ArrayList<>();
    BlockSettings blockSettings = new BlockSettings();

    Parameter value;

    ArduinoVariable currentVariable = null;


    public ReadValue() {

        value = new Parameter("", AssetLoader.Parameter_first,"Wert","","", new ParameterType(InitArduino.floatvar,true),true);
        blockSettings.setSettings("0");
        parameterArrayList.add(value);

        blockSettings.setListener(() -> {
            try {
                value.getParameterType().setVariableType(ProjectManager.getActProjectVar().projectVariables.get(Integer.parseInt(blockSettings.getSettings())).getType());

                currentVariable = (ArduinoVariable) ProjectManager.getActProjectVar().projectVariables.get(Integer.parseInt(blockSettings.getSettings()));
                for (int i = 0; i < value.getDataWires().size(); i++) {
                    value.getDataWires().get(i).delete();
                    i--;
                }
            }catch (Exception e) {

            }
        }
            );

    }

    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return parameterArrayList;
    }

    @Override
    public BlockSettings getblocksettings() {
        return blockSettings;
    }

    @Override
    public int getWidth() {
        return 75;
    }

    @Override
    public String getname() {
        return "Read Value";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.PinModeBlock_description_image;
    }

    @Override
    public String getCode() {
        if(currentVariable==null) return "";
        if( value.getDataWires().size()>0){
            return value.getVarName()+ " = "+currentVariable.getName()+";";

        }else {
            return "";
        }
    }


    @Override
    public String getHeaderCode(boolean inserted) {
        return null;
    }
}
