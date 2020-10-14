/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.If;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.projecttypes.ProgrammArea.ProgrammArea;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockMode;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.utils.ArrayList;

public class IfDefaultBlockMode extends ArduinoBlock {
    ArrayList<Parameter> parameters = new ArrayList<>();
    public IfDefaultBlockMode() {

        parameters.add(new Parameter("",AssetLoader.Parameter_first,"Bedingung","","boolean",new ParameterType(InitArduino.booleanvar,false,true).setSelectables(new String[]{"true","false"}),true));

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
        return 100;
    }

    @Override
    public String getname() {
        return "If";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.Parameter_if;
    }

    @Override
    public String getCode() {
        return "if("+parameters.get(0).getParameter()+") {";
    }

    @Override
    public String getHeaderCode(boolean inserted) {
        return null;
    }

    @Override
    public ProgrammArea getProgrammArea() {
        return ProgrammArea.PROGRAMM_AREA_START;
    }
}
