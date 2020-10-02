/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectType;

public class Wait extends PlatformSpecificBlock implements Ev3Block {
    public Wait(ProjectType projectType, Addon addon) {
        super(projectType,addon);
    }



    @Override
    public String getName() {
        return "Warten";
    }

    @Override
    public String getdescription() {
        return "Mit diesem Block lässt du dein Programm einen bestimmte Zeit warten.";
    }




    @Override
    public BlockCategories getBlockCategorie() {
        return BlockCategories.Programm_Sequence;
    }





    @Override
    public Texture getDescriptionImage() {
        return null;
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

    @Override
    public String getCode() {
        return null;
    }
}
