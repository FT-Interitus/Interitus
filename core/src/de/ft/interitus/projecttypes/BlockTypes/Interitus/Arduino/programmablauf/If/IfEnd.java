/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.If;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockModi;
import de.ft.interitus.projecttypes.BlockTypes.BlockTopParameter;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.utils.ArrayList;

public class IfEnd implements BlockModi, ArduinoBlock {

    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return null;
    }

    @Override
    public ArrayList<BlockTopParameter> getblocktopparamter() {
        return null;
    }

    @Override
    public int getWidth() {
        return 35;
    }

    @Override
    public String getname() {
        return "If Ende";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.WaitBlock_description_image;
    }

    @Override
    public String getCode() {
        return "}";
    }

}
