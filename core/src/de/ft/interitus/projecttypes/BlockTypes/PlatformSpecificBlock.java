/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.ProjectType;
import de.ft.interitus.utils.ArrayList;

public abstract class PlatformSpecificBlock {
    public final ArrayList<BlockMode> blockModis = new ArrayList<>();
    private final Addon addon;
    public int actBlockModiIndex = -1;
    private ProjectType projectType = null;


    public PlatformSpecificBlock(ProjectType projectType, Addon addon) {

        this.projectType = projectType;
        this.addon = addon;

    }

    public abstract String getName();


    public abstract String getDescription();



    public abstract BlockCategories getBlockCategory();



    public abstract Texture getDescriptionImage();


    final public int getID() {
        if (addon == null) {
            for (int i = 0; i < ProjectTypesVar.projectTypes.get(ProjectTypesVar.projectTypes.indexOf(projectType)).getProjectblocks().size(); i++) {

                if (ProjectTypesVar.projectTypes.get(ProjectTypesVar.projectTypes.indexOf(projectType)).getProjectblocks().get(i).getClass() == this.getClass()) {

                    return i;
                }

            }
        } else {


            for (int i = 0; i < addon.getaddBlocks().size(); i++) {

                if (addon.getaddBlocks().get(i).getClass() == this.getClass()) {

                    return i;
                }

            }


        }


        return -1;
    }

    public final boolean isAddonBlock() {
        return addon != null;
    }

    public final String getAddonName() {

        if (addon != null) {
            return addon.getName();
        } else {

            return "";
        }

    }

    final public ProjectType getProjectType() {
        return projectType;
    }

    public final Addon getAddon() {
        return addon;
    }

    public abstract boolean isDeletable();

    public abstract boolean canHasRightConnector();

    public abstract boolean canHasLeftConnector();

    public final ArrayList<BlockMode> getBlockModes() {
        return blockModis;
    }

    public final int getActBlockModeIndex() {
        return actBlockModiIndex;
    }

    public final ArrayList<Parameter> getBlockParameter() {
        return blockModis.get(actBlockModiIndex).getBlockParameter();
    }

    public final int getWidth() {
        return blockModis.get(actBlockModiIndex).getWidth();
    }

    public final void changeBlockMode(int index, Block block, boolean loading) {
        if (index == actBlockModiIndex) {
            return;
        }

        if (this.getBlockParameter() != null) {
            for (Parameter parameter : this.getBlockParameter()) {


                if (parameter.getDataWires() == null) {
                    continue;
                }

                while (parameter.getDataWires().size() > 0) {

                    parameter.getDataWires().get(0).delete();

                }


            }
        }



        int widthdiff = this.getWidth();
        actBlockModiIndex = index;



        widthdiff = this.getWidth()-widthdiff;

        if(!loading) {
            block.changeSize(widthdiff);
        }

    }


}
