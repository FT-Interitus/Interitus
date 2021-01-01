/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.If;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectType;

public class If extends PlatformSpecificBlock {

    public If(ProjectType projectType, Addon addon) {
        super(projectType, addon);
        super.blockModis.add(new IfDefaultBlockMode());
        super.blockModis.add(new IfEnd());
        super.blockModis.add(new IfElse());
        super.actBlockModiIndex = 0;

    }

    @Override
    public String getName() {
        return "If";
    }

    @Override
    public String getDescription() {
        return "Dieser Block vergleicht mehrere Daten";
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
