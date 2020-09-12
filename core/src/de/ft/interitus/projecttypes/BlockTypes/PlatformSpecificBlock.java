/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.utils.ArrayList;

import java.awt.*;

public abstract class PlatformSpecificBlock {
    public final ArrayList<BlockModi> blockModis = new ArrayList<>();
    private final Addon adddon;
    public int actBlockModiIndex = -1;
    private ProjectTypes projectTypes = null;


    public PlatformSpecificBlock(ProjectTypes projectTypes, Addon addon) {

        this.projectTypes = projectTypes;
        this.adddon = addon;

    }


    public abstract String getName();


    public abstract String getdescription();



    public abstract BlockCategories getBlockCategorie();



    public abstract Texture getDescriptionImage();


    final public int getID() {
        if (adddon == null) {
            for (int i = 0; i < ProjectTypesVar.projectTypes.get(ProjectTypesVar.projectTypes.indexOf(projectTypes)).getProjectblocks().size(); i++) {

                if (ProjectTypesVar.projectTypes.get(ProjectTypesVar.projectTypes.indexOf(projectTypes)).getProjectblocks().get(i).getClass() == this.getClass()) {

                    return i;
                }

            }
        } else {


            for (int i = 0; i < adddon.getaddBlocks().size(); i++) {

                if (adddon.getaddBlocks().get(i).getClass() == this.getClass()) {

                    return i;
                }

            }


        }


        return -1;
    }

    public final boolean isaddonblock() {
        return adddon != null;
    }

    public final String addonname() {

        if (adddon != null) {
            return adddon.getName();
        } else {

            return "";
        }

    }

    final public ProjectTypes getProjectType() {
        return projectTypes;
    }

    public final Addon getAdddon() {
        return adddon;
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

    public final void changeBlockModus(int index, Block block) {
        if (index == actBlockModiIndex) {
            return;
        }

        if (this.getBlockParameter() != null) {
            for (Parameter parameter : this.getBlockParameter()) {


                if (parameter.getDatawire() == null) {
                    continue;
                }

                while (parameter.getDatawire().size() > 0) {

                    parameter.getDatawire().get(0).delete();

                }


            }
        }



        int widthdiff = this.getWidth();
        actBlockModiIndex = index;

        widthdiff = this.getWidth()-widthdiff;

        block.changesize(widthdiff);

    }


}
