package de.ft.interitus.projecttypes.device.BlockTypes.Arduino.NeopxielController;

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

public class SetTableColor implements PlatformSpecificBlock {
    ProjectTypes type;

    public SetTableColor(ProjectTypes type) {
        this.type = type;
    } //TODO Description

    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return null;
    }

    @Override
    public String getName() {
        return "Neopixel-Farben";
    }

    @Override
    public String getdescription() {
        return null;
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
        return null;
    }

    @Override
    public Texture getSmallImage() {
        return AssetLoader.img_mappe1;
    }



    @Override
    public int getID() {
        return ProjectTypesVar.projectTypes.get(ProjectTypesVar.projectTypes.indexOf(type)).getProjectblocks().indexOf(this);
    }

    @Override
    public ProjectTypes getProjectType() {
        return type;
    }

    @Override
    public int getWidth() {
        return 150;
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
    public boolean canhasleftconnect() {
        return true;
    }

}
