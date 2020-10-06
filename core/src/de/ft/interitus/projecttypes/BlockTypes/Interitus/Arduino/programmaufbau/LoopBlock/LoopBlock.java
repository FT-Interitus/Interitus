/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmaufbau.LoopBlock;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectType;

public class LoopBlock extends PlatformSpecificBlock  {


    public LoopBlock(ProjectType projectType, Addon addon) {
        super(projectType,addon);

        super.blockModis.add(new LoopModus());
        super.actBlockModiIndex = 0;
    }




    public String getName() {
        return "Loop";
    }

    @Override
    public String getdescription() {
        return "Alles was an diesen Block angehängt wird immer wiederholt";
    }




    @Override
    public BlockCategories getBlockCategorie() {
        return null;
    }





    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.loop_description;
    }




    @Override
    public boolean canbedeleted() {
        return false;
    }

    @Override
    public boolean canhasrightconnector() {
        return true;
    }

    @Override
    public boolean canhasleftconnector() {
        return false;
    }


}
