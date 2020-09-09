/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmaufbau.LoopBlock;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.BlockTopParameter;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.utils.ArrayList;

import java.awt.*;

public class LoopBlock extends PlatformSpecificBlock  {


    public LoopBlock(ProjectTypes projectTypes, Addon addon) {
        super(projectTypes,addon);

        super.blockModis.add(new LoopModi());
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
    public Color blockcolor() {
        return null;
    }

    @Override
    public BlockCategories getBlockCategoration() {
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
