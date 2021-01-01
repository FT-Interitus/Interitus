/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectType;

public class WhileLoopStart extends PlatformSpecificBlock {

    public WhileLoopStart(ProjectType arduino, Addon addon) {
        super(arduino,addon);


    }



    @Override
    public String getName() {
        return "WhileLoopStart";
    }

    @Override
    public String getDescription() {
        return "Das ist ein Block um eine while schleife zu starten mehr dazu findest du auf der GitHub Seite von Interitus.";
    }




    @Override
    public BlockCategories getBlockCategory() {
        return BlockCategories.Programm_Sequence;
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


    @Override
    public Texture getDescriptionImage() {
        return null;
    }




}
