/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectTypes;

import java.awt.*;

public class WhileLoopStart extends PlatformSpecificBlock implements ArduinoBlock {

    public WhileLoopStart(ProjectTypes arduino, Addon addon) {
        super(arduino,addon);


    }



    @Override
    public String getName() {
        return "WhileLoopStart";
    }

    @Override
    public String getdescription() {
        return "Das ist ein Block um eine while schleife zu starten mehr dazu findest du auf der GitHub Seite von Interitus.";
    }




    @Override
    public BlockCategories getBlockCategorie() {
        return BlockCategories.Programm_Sequence;
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
    public Texture getDescriptionImage() {
        return null;
    }

    @Override
    public String getCode() {
        return "while(true){";
    }

    @Override
    public String getHeaderCode() {
        return null;
    }


}
