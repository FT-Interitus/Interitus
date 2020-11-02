/*
 * Copyright (c) 2020.
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
    public String getdescription() {
        return null;
    }

    @Override
    public BlockCategories getBlockCategorie() {
        return null;
    }

    @Override
    public Texture getDescriptionImage() {
        return null;
    }

    @Override
    public boolean canbedeleted() {
        return false;
    }

    @Override
    public boolean canhasrightconnector() {
        return false;
    }

    @Override
    public boolean canhasleftconnector() {
        return false;
    }
}
