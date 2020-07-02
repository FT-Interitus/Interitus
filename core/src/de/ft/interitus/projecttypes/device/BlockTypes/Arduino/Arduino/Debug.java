package de.ft.interitus.projecttypes.device.BlockTypes.Arduino.Arduino;


import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.projecttypes.device.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.device.BlockTypes.BlockTopParameter;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.device.BlockTypes.ProjectTypesVar;

import java.awt.*;
import java.util.ArrayList;

public class Debug implements PlatformSpecificBlock {
    private final ProjectTypes type;

    public Debug(ProjectTypes arduino) {
        this.type = arduino;
    }

    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return null;
    }

    @Override
    public String getName() {
        return "Debug Block";
    }

    @Override
    public String getdescription() {
        return "Block debug";
    }

    @Override
    public ArrayList<BlockTopParameter> getblocktopparamter() {
        return null;
    }

    @Override
    public Color blockcolor() {
        return null;
    }

    @Override
    public BlockCategories getBlockCategoration() {
        return BlockCategories.ActionBlocks;
    }

    @Override
    public Texture getSmallImage() {
        return AssetLoader.img_mappe2;
    }

    @Override
    public Texture getImage() {
        return null;
    }

    @Override
    public int getID() {
        return ProjectTypesVar.projectTypes.get(ProjectTypesVar.projectTypes.indexOf(type)).getProjectblocks().indexOf(this);
    }

    @Override
    public ProjectTypes getProjectType() {
        return type;
    }
}
