/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.actionblocks;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.BlockTopParameter;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ParameterVariableType;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.utils.ArrayList;

import java.awt.*;

public class digitalWrite extends PlatformSpecificBlock implements ArduinoBlock {

    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter pin;
    Parameter mode;


    public digitalWrite(ProjectTypes type) {
        super(type);


        pin = new Parameter("", AssetLoader.Parameter_Pin, "Pin", "", null,new ParameterType(InitArduino.floatvar,false,false), true);
        String[] selectables = new String[2];
        selectables[0] = "HIGH";
        selectables[1] = "LOW";
        mode = new Parameter(selectables[1], AssetLoader.Parameter_High_Low, "Mode", "", null,new ParameterType(InitArduino.floatvar,false,true).setSelectables(selectables), true);


        parameters.add(pin);
        parameters.add(mode);


    }

    @Override
    public String getCode() {
        return "digitalWrite(" + this.parameters.get(0).getParameter() + "," + this.parameters.get(1).getParameter() + ");";
    }

    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return parameters;
    }

    @Override
    public String getName() {
        return "digitalWrite";
    }

    @Override
    public String getdescription() {
        return "";
    }

    @Override
    public ArrayList<BlockTopParameter> getblocktopparamter() {
        return null;
    }

    @Override
    public Color blockcolor() {
        return null;
    }

    @Override
    public BlockCategories getBlockCategoration() {
        return BlockCategories.ActionBlocks;
    }

    @Override
    public Texture getSmallImage() {
        return AssetLoader.DigitalWrite_smallimage;
    }

    @Override
    public Texture getImageRight() {
        return AssetLoader.DigitalWrite_right;
    }

    @Override
    public Texture getImageLeft() {
        return AssetLoader.DigitalWrite_left;
    }

    @Override
    public Texture getImageCenter() {
        return AssetLoader.DigitalWrite_middle;
    }

    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.DigitalWrite_description_image;
    }


    @Override
    public int getWidth() {
        return 150;
    }


    @Override
    public boolean canbedeleted() {
        return true;
    }

    @Override
    public boolean canhasrightconnector() {
        return true;
    }

    @Override
    public boolean canhasleftconnector() {
        return true;
    }
}
