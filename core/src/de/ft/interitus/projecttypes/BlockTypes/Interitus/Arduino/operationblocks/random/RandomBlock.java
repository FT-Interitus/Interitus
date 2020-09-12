/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.random;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectTypes;

public class RandomBlock extends PlatformSpecificBlock {
    public RandomBlock(ProjectTypes projectTypes, Addon addon) {
        super(projectTypes, addon);
        super.actBlockModiIndex = 0;
        super.blockModis.add(new RandomNumeric());
    }

    @Override
    public String getName() {
        return "Zufalls Block";
    }

    @Override
    public String getdescription() {
        return "Gibt einen Zufall zurück";
    }

    @Override
    public BlockCategories getBlockCategorie() {
        return BlockCategories.Data_Operation;
    }

    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.math_description;
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
