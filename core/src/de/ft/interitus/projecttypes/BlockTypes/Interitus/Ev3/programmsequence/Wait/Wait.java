/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.programmsequence.Wait;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectType;

public class Wait extends PlatformSpecificBlock {

    public Wait(ProjectType projectType, Addon addon) {
        super(projectType, addon);
        super.actBlockModiIndex=0;
        super.blockModis.add(new WaitSeconds());
    }

    @Override
    public String getName() {
        return "Wait Block";
    }

    @Override
    public String getDescription() {
        return "Dieser Block blockiert den programm ablauf";
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
