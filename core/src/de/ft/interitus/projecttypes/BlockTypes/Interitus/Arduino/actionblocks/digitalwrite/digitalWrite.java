/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.actionblocks.digitalwrite;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectType;
import de.ft.interitus.utils.ArrayList;

public class digitalWrite extends PlatformSpecificBlock {

    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter pin;
    Parameter mode;


    public digitalWrite(ProjectType type, Addon addon) {
        super(type,addon);

        blockModis.add(new digitalWriteNormal());
        actBlockModiIndex=0;


    }





    @Override
    public String getName() {
        return "digitalWrite";
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
        return AssetLoader.output_description;
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
