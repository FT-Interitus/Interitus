/*
 * Copyright (c) 2020.
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
        super.blockModis.add(new AdditionalModus());
        super.blockModis.add(new DifferenzModus());
        super.blockModis.add(new MultiplicationModus());
        super.blockModis.add(new DivisionModus());
        super.blockModis.add(new AbsolutModus());
        super.blockModis.add(new SquareRootModus());
        super.blockModis.add(new PowerModus());
        super.blockModis.add(new ExpandedMath());

        super.actBlockModiIndex = 0;



    }



    @Override
    public String getName() {
        return "Math";
    }

    @Override
    public String getdescription() {
        return "Zwei zahlen plus rechnen";
    }





    @Override
    public BlockCategories getBlockCategorie() {
        return BlockCategories.Data_Operation;
    }




    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.math_description;
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
