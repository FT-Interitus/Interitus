/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.programmsequence.Wait;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.Ev3Block;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.InitEv3;
import de.ft.interitus.utils.ArrayList;

import java.util.UUID;

public class WaitSeconds extends Ev3Block {
    ArrayList<Parameter>parameters=new ArrayList<>();

    public WaitSeconds() {
        parameters.add(new Parameter("0", AssetLoader.Parameter_wait, "Wartezeit", "", "ms", new ParameterType(InitEv3.floatvar,false), true));
    }



    @Override
    public String getCode() {

        String name = UUID.randomUUID().toString().replace("-","");

        return "DATA32 "+name+"\n" +
                "TIMER_WAIT("+parameters.get(0)+","+name+")\n" +
                "TIMER_READY("+name+")";
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
        return 90;
    }

    @Override
    public String getname() {
        return "Wait Seconds";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.WaitBlock_description_image;
    }
}
