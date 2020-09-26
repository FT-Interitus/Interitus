/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmaufbau.SetupBlock;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectTypes;

public class SetupBlock extends PlatformSpecificBlock {

    public SetupBlock(ProjectTypes arduino, Addon addon) {
        super(arduino,  addon);
        super.blockModis.add(new SetupModi());
        super.actBlockModiIndex=0;


    }



    @Override
    public String getName() {
        return "Setup";
    }

    @Override
    public String getdescription() {
        return "Alles was an diesen Block angehängt wird einmal bei Programmstart ausgeführt";
    }



    @Override
    public BlockCategories getBlockCategorie() {
        return null;
    }





    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.setup_description;
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
