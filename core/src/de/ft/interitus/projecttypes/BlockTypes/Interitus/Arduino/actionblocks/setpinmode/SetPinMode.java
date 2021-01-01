/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.actionblocks.setpinmode;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectType;

public class SetPinMode extends PlatformSpecificBlock  {




    public SetPinMode(ProjectType type, Addon addon) {
        super(type,addon);
        blockModis.add(new DefaultSetPinMode());
        actBlockModiIndex=0;


    }


    @Override
    public String getName() {
        return "SetPinMode";
    }

    @Override
    public String getDescription() {
        return "";
    }



    @Override
    public BlockCategories getBlockCategory() {
        return BlockCategories.ActionBlocks;
    }



    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.PinModeBlock_description_image;
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
