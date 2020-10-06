/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.For;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.projecttypes.ProgrammArea.ProgrammArea;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockModus;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.utils.ArrayList;
import org.apache.commons.lang3.RandomStringUtils;

public class ForDefaultBlockModus extends BlockModus implements ArduinoBlock {
    ArrayList<Parameter> parameters = new ArrayList<>();
    public ForDefaultBlockModus() {

        parameters.add(new Parameter("",AssetLoader.Parameter_first,"Counter","","int",new ParameterType(InitArduino.floatvar,false,false),true));
        parameters.add(new Parameter("",AssetLoader.Parameter_isequal,"Output","","int",new ParameterType(InitArduino.floatvar,true,false),true));

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
        return "For";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.Parameter_if;
    }

    @Override
    public String getCode() {
        String generatedString = RandomStringUtils.randomAlphabetic(4);
        if( parameters.get(1).getDatawire().size()>0) {
            return "for(int " + generatedString + "=0;" + generatedString + "<" + parameters.get(0).getParameter() + ";" + generatedString + "++) {\n"+
                    parameters.get(1).getVarName() +" = "+ generatedString+";"

                    ;


        }else{
            return "for(int " + generatedString + "=0;" + generatedString + "<" + parameters.get(0).getParameter() + ";" + generatedString + "++) {";

        }
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
