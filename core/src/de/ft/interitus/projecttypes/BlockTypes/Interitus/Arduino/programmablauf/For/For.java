/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.For;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectType;

public class For extends PlatformSpecificBlock {

    public For(ProjectType projectType, Addon addon) {
        super(projectType, addon);
        super.blockModis.add(new ForDefaultBlockMode());

        super.actBlockModiIndex = 0;

    }

    @Override
    public String getName() {
        return "For";
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
        return AssetLoader.if_description;
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
