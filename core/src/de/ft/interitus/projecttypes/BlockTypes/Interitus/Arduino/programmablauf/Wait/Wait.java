/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.Wait;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectTypes;

import java.awt.*;

public class Wait extends PlatformSpecificBlock {



    public Wait(ProjectTypes arduino, Addon addon) {
        super(arduino,addon);

        blockModis.add(new DefaultWait());
       actBlockModiIndex = 0;

    }



    @Override
    public String getName() {
        return "Wait";
    }

    @Override
    public String getdescription() {
        return null;
    }



    @Override
    public Color blockcolor() {
        return null;
    }

    @Override
    public BlockCategories getBlockCategoration() {
        return BlockCategories.Programm_Sequence;
    }



    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.WaitBlock_description_image;
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
