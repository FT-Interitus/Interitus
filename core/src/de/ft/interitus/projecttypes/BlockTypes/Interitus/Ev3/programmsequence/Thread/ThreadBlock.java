/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.programmsequence.Thread;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectType;

public class ThreadBlock extends PlatformSpecificBlock {
    public ThreadBlock(ProjectType projectType, Addon addon) {
        super(projectType, addon);
        super.actBlockModiIndex=0;
        super.blockModis.add(new StartThread());

    }

    @Override
    public String getName() {
        return "Start Block";
    }

    @Override
    public String getDescription() {
        return "Startet eine Programm Thread";
    }

    @Override
    public BlockCategories getBlockCategory() {
        return BlockCategories.Programm_Sequence;
    }

    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.Start_description;
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
        return false;
    }
}
