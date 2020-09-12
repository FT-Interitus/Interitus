/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.If;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectTypes;

import java.awt.*;

public class If extends PlatformSpecificBlock {

    public If(ProjectTypes projectTypes, Addon addon) {
        super(projectTypes, addon);
        super.blockModis.add(new IfDefaultBlockModi());
        super.blockModis.add(new IfEnd());
        super.blockModis.add(new IfElse());
        super.actBlockModiIndex = 0;

    }

    @Override
    public String getName() {
        return "If";
    }

    @Override
    public String getdescription() {
        return "Dieser Block vergleicht mehrere Daten";
    }



    @Override
    public BlockCategories getBlockCategorie() {
        return BlockCategories.Programm_Sequence;
    }




    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.if_description;
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
