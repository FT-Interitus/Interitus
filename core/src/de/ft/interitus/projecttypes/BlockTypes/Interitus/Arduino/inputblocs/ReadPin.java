/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.inputblocs;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectType;

public class ReadPin extends PlatformSpecificBlock {



    public ReadPin(ProjectType arduino, Addon addon) {
        super(arduino,addon);

        blockModis.add(new DigitalReadDefault());
        blockModis.add(new AnalogRead());
        actBlockModiIndex = 0;

    }



    @Override
    public String getName() {
        return "Read Pin";
    }

    @Override
    public String getDescription() {
        return "Dieser Block list einen Pin digital aus";
    }




    @Override
    public BlockCategories getBlockCategory() {
        return BlockCategories.Sensors;
    }




    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.input_description;
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
