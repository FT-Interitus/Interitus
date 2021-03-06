/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.variable;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectType;



public class VariableBlock extends PlatformSpecificBlock {
    public VariableBlock(ProjectType projectType, Addon addon) {
        super(projectType, addon);

        super.actBlockModiIndex = 0;
        super.blockModis.add(new WriteValue());
        super.blockModis.add(new ReadValue());


    }

    @Override
    public String getName() {
        return "Variablen";
    }

    @Override
    public String getDescription() {
        return "Das ist ein Variablen Block";
    }


    @Override
    public BlockCategories getBlockCategory() {
        return BlockCategories.Data_Operation;
    }

    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.math_description;
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
