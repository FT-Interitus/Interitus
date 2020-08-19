/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.utils.ArrayList;

import java.awt.*;

public abstract class PlatformSpecificBlock {
 public   final ArrayList<BlockModi> blockModis = new ArrayList<>();
    public  int actBlockModiIndex = -1;

    private ProjectTypes projectTypes = null;

    public PlatformSpecificBlock(ProjectTypes projectTypes) {

        this.projectTypes = projectTypes;

    }




    public abstract String getName();


    public abstract String getdescription();





    public abstract Color blockcolor();

    public abstract BlockCategories getBlockCategoration();

    public abstract Texture getSmallImage();

    public abstract Texture getImageRight();

    public abstract Texture getImageLeft();

    public abstract Texture getImageCenter();

    public abstract Texture getDescriptionImage();


    final public int getID() {
        for (int i = 0; i < ProjectTypesVar.projectTypes.get(ProjectTypesVar.projectTypes.indexOf(projectTypes)).getProjectblocks().size(); i++) {

            if (ProjectTypesVar.projectTypes.get(ProjectTypesVar.projectTypes.indexOf(projectTypes)).getProjectblocks().get(i).getClass() == this.getClass()) {

                return i;
            }

        }

        return -1;
    }

    final public ProjectTypes getProjectType() {
        return projectTypes;
    }


    public abstract boolean canbedeleted();

    public abstract boolean canhasrightconnector();

    public abstract boolean canhasleftconnector();

    public final ArrayList<BlockModi> getBlockModis() {
        return blockModis;
    }

    public final int getActBlockModiIndex() {
        return actBlockModiIndex;
    }

    public final ArrayList<Parameter> getBlockParameter() {
        return blockModis.get(actBlockModiIndex).getBlockParameter();
    }

    public final int getWidth() {
        return blockModis.get(actBlockModiIndex).getWidth();
    }
}
