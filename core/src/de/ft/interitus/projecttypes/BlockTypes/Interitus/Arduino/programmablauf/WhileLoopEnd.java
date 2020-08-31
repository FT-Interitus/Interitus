/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.BlockTopParameter;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.inputblocs.DigitalRead;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.inputblocs.DigitalReadDefault;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.utils.ArrayList;

import java.awt.*;

public class WhileLoopEnd extends PlatformSpecificBlock implements ArduinoBlock {

    public WhileLoopEnd(ProjectTypes arduino, Addon addon) {
        super(arduino,addon);
        blockModis.add(new DigitalReadDefault());
        actBlockModiIndex = 0;

    }


    @Override
    public String getName() {
        return "WhileLoopEnde";
    }

    @Override
    public String getdescription() {
        return "";
    }



    @Override
    public Color blockcolor() {
        return null;
    }

    @Override
    public BlockCategories getBlockCategoration() {
        return BlockCategories.Programm_Sequence;
    }

    @Override
    public Texture getSmallImage() {
        return null;
    }



    @Override
    public Texture getDescriptionImage() {
        return null;
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


    @Override
    public String getCode() {
        return "}";
    }

}
