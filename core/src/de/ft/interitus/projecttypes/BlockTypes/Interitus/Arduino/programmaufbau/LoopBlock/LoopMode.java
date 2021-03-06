/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmaufbau.LoopBlock;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.projecttypes.BlockTypes.BlockMode;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.utils.ArrayList;

public class LoopMode extends ArduinoBlock {
    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return null;
    }

    @Override
    public BlockSettings getblocksettings() {
        return null;
    }

    @Override
    public int getWidth() {
        return 74;
    }

    @Override
    public String getname() {
        return "Loop";
    }

    @Override
    public Texture getModiImage() {
        return null;
    }

    @Override
    public String getCode() {
         return "void loop(){";
    }
    @Override
    public String getHeaderCode(boolean inserted) {
        return null;
    }
}
