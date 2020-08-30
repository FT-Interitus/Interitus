/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.inputblocs;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.BlockModi;
import de.ft.interitus.projecttypes.BlockTypes.BlockTopParameter;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ParameterVariableType;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.utils.ArrayList;

import java.awt.*;

public class DigitalRead extends PlatformSpecificBlock {



    public DigitalRead(ProjectTypes arduino, Addon addon) {
        super(arduino,addon);

        blockModis.add(new DigitalReadDefault());
        actBlockModiIndex = 0;

    }



    @Override
    public String getName() {
        return "DigitalRead";
    }

    @Override
    public String getdescription() {
        return "Dieser Block list einen Pin digital aus";
    }



    @Override
    public Color blockcolor() {
        return null;
    }

    @Override
    public BlockCategories getBlockCategoration() {
        return BlockCategories.Sensors;
    }

    @Override
    public Texture getSmallImage() {
        return AssetLoader.WaitBlock_smallimage;
    }

    @Override
    public Texture getImageRight() {
        return AssetLoader.WaitBlock_right;
    }

    @Override
    public Texture getImageLeft() {
        return AssetLoader.WaitBlock_left;
    }

    @Override
    public Texture getImageCenter() {
        return AssetLoader.WaitBlock_middle;
    }

    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.WaitBlock_description_image;
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
