/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.variable;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.projecttypes.BlockTypes.BlockModi;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.utils.ArrayList;

public class CreateVariable implements BlockModi, ArduinoBlock {


    ArrayList<Parameter> parameterArrayList = new ArrayList<>();


    public CreateVariable() {



    }

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
        return 0;
    }

    @Override
    public String getname() {
        return null;
    }

    @Override
    public Texture getModiImage() {
        return null;
    }

    @Override
    public String getCode() {
        return null;
    }
}
