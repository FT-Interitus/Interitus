/*
 * Copyright (c) 2020.
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
    public String getdescription() {
        return "Dieser Block blockiert den programm ablauf";
    }

    @Override
    public BlockCategories getBlockCategorie() {
        return BlockCategories.Programm_Sequence;
    }

    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.green_bar_right;
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
