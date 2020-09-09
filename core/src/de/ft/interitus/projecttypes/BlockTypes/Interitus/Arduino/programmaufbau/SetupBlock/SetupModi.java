/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmaufbau.SetupBlock;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockModi;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.utils.ArrayList;

public class SetupModi implements BlockModi, ArduinoBlock {
    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return null;
    }

    @Override
    public ArrayList<BlockSettings> getblocksettings() {
        return null;
    }

    @Override
    public int getWidth() {
        return 74;
    }

    @Override
    public String getname() {
        return "SetupModi";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.connector_offerd;
    }

    @Override
    public String getCode() {
        return "void setup(){";
    }
}
