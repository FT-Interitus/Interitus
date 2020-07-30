/*
 * Copyright (c) 2020.
 * Author Tim & Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Arduino.actionblocks;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.projecttypes.BlockTypes.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.BlockTopParameter;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.BlockTypes.ProjectTypesVar;

import java.awt.*;
import de.ft.interitus.utils.ArrayList;

public class SetPinMode implements PlatformSpecificBlock, ArduinoBlock {

    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter pin;
    Parameter mode;



    private final ProjectTypes type;

    public SetPinMode(ProjectTypes type) {


        pin = new Parameter("", AssetLoader.Parameter_Pin,"Pin","",null);
        mode = new Parameter("",AssetLoader.Parameter_IO,"Mode(I/O)","",null);


        parameters.add(pin);
        parameters.add(mode);
        this.type = type;



    }
    @Override
    public String getCode() {
        return "pinMode("+this.parameters.get(0).getParameter()+","+this.parameters.get(1).getParameter()+");";
    }

    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return parameters;
    }

    @Override
    public String getName() {
        return "SetPinMode";
    }

    @Override
    public String getdescription() {
        return "";
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
        return AssetLoader.PinModeBlock_smallimage;
    }

    @Override
    public Texture getImageRight() {
        return AssetLoader.PinModeBlock_right;
    }

    @Override
    public Texture getImageLeft() {
        return AssetLoader.PinModeBlock_left;
    }

    @Override
    public Texture getImageCenter() {
        return AssetLoader.PinModeBlock_middle;
    }

    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.PinModeBlock_description_image;
    }


    @Override
    public ProjectTypes getProjectType() {
        return null;
    }

    @Override
    public int getWidth() {
        return 150;
    }

    @Override
    public int getID() {
        for(int i = 0; i< ProjectTypesVar.projectTypes.get(ProjectTypesVar.projectTypes.indexOf(type)).getProjectblocks().size(); i++) {

            if(ProjectTypesVar.projectTypes.get(ProjectTypesVar.projectTypes.indexOf(type)).getProjectblocks().get(i).getClass()==this.getClass()) {

                return i;
            }

        }

        return -1;    }

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
