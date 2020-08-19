/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.Wait;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockModi;
import de.ft.interitus.projecttypes.BlockTypes.BlockTopParameter;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.utils.ArrayList;

public class DefaultWait implements BlockModi, ArduinoBlock {
    final ArrayList<Parameter> parameter = new ArrayList<>();
    public DefaultWait() {

        parameter.add(new Parameter(0, AssetLoader.img_WaitBlock_warteZeit_Parameter, "Warte-Zeit", "Die Zeit die abgewartet werden soll", "ms",new ParameterType(InitArduino.floatvar,false,false), true));

    }
    @Override
    public ArrayList<Parameter> getBlockParameter() {

        return parameter;
    }

    @Override
    public ArrayList<BlockTopParameter> getblocktopparamter() {
        return null;
    }

    @Override
    public int getWidth() {
        return 80;
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
         return "delay(" + parameter.get(0).getParameter() + ");";
    }
}
