/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.logicOperation;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectType;

public class LogicOperation extends PlatformSpecificBlock {
    public LogicOperation(ProjectType projectType, Addon addon) {
        super(projectType, addon);
        super.blockModis.add(new LogicOperationIntagerOperation());
        super.actBlockModiIndex=0;
    }

    @Override
    public String getName() {
        return "IntagerOperationen";
    }

    @Override
    public String getdescription() {
        return "Dieser Block vergleicht Intager";
    }


    @Override
    public BlockCategories getBlockCategorie() {
        return BlockCategories.Data_Operation;
    }

    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.vergleich_description;
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
