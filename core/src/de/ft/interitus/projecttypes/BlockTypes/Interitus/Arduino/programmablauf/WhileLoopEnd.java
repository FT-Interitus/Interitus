/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.inputblocs.DigitalReadDefault;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectType;

public class WhileLoopEnd extends PlatformSpecificBlock {

    public WhileLoopEnd(ProjectType arduino, Addon addon) {
        super(arduino,addon);



    }


    @Override
    public String getName() {
        return "WhileLoopEnde";
    }

    @Override
    public String getDescription() {
        return "";
    }





    @Override
    public BlockCategories getBlockCategory() {
        return BlockCategories.Programm_Sequence;
    }





    @Override
    public Texture getDescriptionImage() {
        return null;
    }



    @Override
    public boolean isDeletable() {
        return true;
    }

    @Override
    public boolean canHasRightConnector() {
        return true;
    }

    @Override
    public boolean canHasLeftConnector() {
        return true;
    }




}
