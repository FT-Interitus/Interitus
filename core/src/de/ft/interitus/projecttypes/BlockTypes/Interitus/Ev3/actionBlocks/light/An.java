/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.actionBlocks.light;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.Ev3Block;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.InitEv3;
import de.ft.interitus.utils.ArrayList;

import java.lang.reflect.ParameterizedType;


public class An extends Ev3Block {
    ArrayList<Parameter>parameters=new ArrayList<>();
    Parameter Farbe;
    Parameter Mode;

    public An() {
        Farbe=new Parameter("", AssetLoader.Parameter_wait, "Farbe", "description", "", new ParameterType(InitEv3.floatvar,false,true).setSelectables("Rot","Grün","Orange"), true);
        Mode=new Parameter("", AssetLoader.Parameter_wait, "Mode", "description", "", new ParameterType(InitEv3.floatvar,false,true).setSelectables("Leuchten","Pulsierend","Blinkend"), true);
        parameters.add(Farbe);
        parameters.add(Mode);
    }

    @Override
    public String getCode() {
        return null;
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
        return 120;
    }

    @Override
    public String getname() {
        return "StaturLicht An";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.PinModeBlock_description_image;
    }
}
