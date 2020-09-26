/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.variable;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockModi;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.utils.ArrayList;

public class ReadValue extends BlockModi implements ArduinoBlock {


    ArrayList<Parameter> parameterArrayList = new ArrayList<>();
    BlockSettings blockSettings = new BlockSettings();

    Parameter initialValue;


    public ReadValue() {

        //initialValue = new Parameter("", AssetLoader.Parameter_first,"Wert",);

blockSettings.setSettings("float");

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
        return null;
    }


    @Override
    public String getHeaderCode(boolean inserted) {
        return null;
    }
}
