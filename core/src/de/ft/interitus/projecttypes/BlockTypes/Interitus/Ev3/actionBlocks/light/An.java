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




public class An extends Ev3Block {
    ArrayList<Parameter>parameters=new ArrayList<>();
    Parameter Farbe;
    Parameter Mode;

    public An() {
        Farbe=new Parameter("", AssetLoader.Parameter_farbe, "Farbe", "description", "", new ParameterType(InitEv3.floatvar,false,true).setSelectables("Rot","Grün","Orange"), true);
        Mode=new Parameter("", AssetLoader.Parameter_wait, "Mode", "description", "", new ParameterType(InitEv3.floatvar,false,true).setSelectables("Leuchten","Pulsierend","Blinkend"), true);
        parameters.add(Farbe);
        parameters.add(Mode);
    }

    @Override
    public String getCode() {
        byte mode=0x00;
        switch ((String) Farbe.getParameter()){
            case "Rot":{
                mode+=0x02;
                break;
            }
            case "Grün":{
                mode+=0x01;
                break;
            }
            case "Orange":{
                mode+=0x03;
                break;
            }
        }
        switch ((String)Mode.getParameter()){
            case "Leuchten":{
                mode+=0x00;
                break;
            }
            case "Pulsierend":{
                mode+=0x06;
                break;
            }
            case "Blinkend":{
                mode+=0x03;
                break;
            }
        }
        return "UI_WRITE(LED,"+mode+")";
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
