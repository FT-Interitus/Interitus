/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.mapblock;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectType;

public class MapBlock extends PlatformSpecificBlock {
    public MapBlock(ProjectType projectType, Addon addon) {
        super(projectType, addon);
        super.blockModis.add(new MapModeDefault());
        super.actBlockModiIndex=0;
;    }



    @Override
    public String getName() {

        return "MapBlock";
    }

    @Override
    public String getDescription() {
        return "Dieser Block Mappt";
    }



    @Override
    public BlockCategories getBlockCategory() {
        return BlockCategories.Data_Operation;
    }

    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.bereich_description;
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
