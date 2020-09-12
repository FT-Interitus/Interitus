/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.actionblocks.digitalwrite;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.utils.ArrayList;

import java.awt.*;

public class digitalWrite extends PlatformSpecificBlock {

    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter pin;
    Parameter mode;


    public digitalWrite(ProjectTypes type, Addon addon) {
        super(type,addon);

        blockModis.add(new digitalWriteNormal());
        actBlockModiIndex=0;


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
    public BlockCategories getBlockCategorie() {
        return BlockCategories.ActionBlocks;
    }




    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.DigitalWrite_description_image;
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
