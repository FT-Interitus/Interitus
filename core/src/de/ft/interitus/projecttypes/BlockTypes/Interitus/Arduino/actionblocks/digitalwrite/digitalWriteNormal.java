/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.actionblocks.digitalwrite;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.Block.Selectable;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.utils.ArrayList;

public class digitalWriteNormal extends ArduinoBlock {

    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter pin;
    Parameter mode;

    public digitalWriteNormal(){
        pin = new Parameter("", AssetLoader.Parameter_Pin, "Pin", "", null,new ParameterType(InitArduino.floatvar,false), true);
        Selectable[] selectables = new Selectable[2];
        selectables[0] =new Selectable("LOW");
        selectables[1] = new Selectable("HIGH");
        mode = new Parameter(selectables[0].getDropDownText(), AssetLoader.Parameter_High_Low, "Mode", "", null,new ParameterType(InitArduino.booleanvar,false).setSelectables(selectables), true);


        parameters.add(pin);
        parameters.add(mode);
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
        return "digitalWriteNormal";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.connector_offerd;
    }

    @Override
    public String getCode() {
        return "digitalWrite(" + this.parameters.get(0).getParameter() + "," + this.parameters.get(1).getParameter() + ");";
    }

    @Override
    public String getHeaderCode(boolean inserted) {
        return null;
    }
}
