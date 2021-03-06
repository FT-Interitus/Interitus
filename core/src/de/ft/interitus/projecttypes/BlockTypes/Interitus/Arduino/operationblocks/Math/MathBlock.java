/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.Math;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectType;

public class MathBlock extends PlatformSpecificBlock {




    public MathBlock(ProjectType type, Addon addon) {
        super(type,addon);
        super.blockModis.add(new AdditionalMode());
        super.blockModis.add(new DifferenzMode());
        super.blockModis.add(new MultiplicationMode());
        super.blockModis.add(new DivisionMode());
        super.blockModis.add(new AbsolutMode());
        super.blockModis.add(new SquareRootMode());
        super.blockModis.add(new PowerMode());
        super.blockModis.add(new ExpandedMath());

        super.actBlockModiIndex = 0;



    }



    @Override
    public String getName() {
        return "Math";
    }

    @Override
    public String getDescription() {
        return "Zwei zahlen plus rechnen";
    }





    @Override
    public BlockCategories getBlockCategory() {
        return BlockCategories.Data_Operation;
    }




    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.math_description;
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
