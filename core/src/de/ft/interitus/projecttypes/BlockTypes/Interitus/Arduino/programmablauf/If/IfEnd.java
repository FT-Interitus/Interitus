/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.If;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.projecttypes.ProgrammArea.ProgrammArea;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockModi;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.utils.ArrayList;

public class IfEnd extends BlockModi implements ArduinoBlock {

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
        return 50;
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

    @Override
    public String getHeaderCode(boolean inserted) {
        return null;
    }

    @Override
    public ProgrammArea getProgrammArea() {
        return ProgrammArea.PROGRAMM_AREA_END;
    }
}
