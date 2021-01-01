/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.actionBlocks.sound;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectType;

public class SoundBlock extends PlatformSpecificBlock {
    public SoundBlock(ProjectType projectType, Addon addon) {
        super(projectType, addon);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public BlockCategories getBlockCategory() {
        return null;
    }

    @Override
    public Texture getDescriptionImage() {
        return null;
    }

    @Override
    public boolean isDeletable() {
        return false;
    }

    @Override
    public boolean canHasRightConnector() {
        return false;
    }

    @Override
    public boolean canHasLeftConnector() {
        return false;
    }
}
