/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.Wait;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectType;

public class Wait extends PlatformSpecificBlock {



    public Wait(ProjectType arduino, Addon addon) {
        super(arduino,addon);

        blockModis.add(new DefaultWait());
       actBlockModiIndex = 0;

    }



    @Override
    public String getName() {
        return "Wait";
    }

    @Override
    public String getDescription() {
        return null;
    }




    @Override
    public BlockCategories getBlockCategory() {
        return BlockCategories.Programm_Sequence;
    }



    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.WaitBlock_description_image;
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
