/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.inputblocs.DigitalReadDefault;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectTypes;

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
    public BlockCategories getBlockCategorie() {
        return BlockCategories.Programm_Sequence;
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

    @Override
    public String getHeaderCode() {
        return null;
    }

}
