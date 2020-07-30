package de.ft.interitus.projecttypes.BlockTypes.Arduino.programmablauf;

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

public class WhileLoopEnd implements PlatformSpecificBlock, ArduinoBlock {
    ProjectTypes type;
    public WhileLoopEnd(ProjectTypes arduino) {
        this.type = arduino;

    }

    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return null;
    }

    @Override
    public String getName() {
        return "WhileLoopEnde";
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
        return BlockCategories.Programm_Sequence;
    }

    @Override
    public Texture getSmallImage() {
        return AssetLoader.img_block;
    }

    @Override
    public Texture getImageRight() {
        return AssetLoader.WaitBlock_right;
    }

    @Override
    public Texture getImageLeft() {
        return AssetLoader.WaitBlock_left;
    }

    @Override
    public Texture getImageCenter() {
        return AssetLoader.WaitBlock_middle;
    }

    @Override
    public Texture getDescriptionImage() {
        return null;
    }

    @Override
    public int getID() {
        for(int i=0;i<ProjectTypesVar.projectTypes.get(ProjectTypesVar.projectTypes.indexOf(type)).getProjectblocks().size();i++) {

            if(ProjectTypesVar.projectTypes.get(ProjectTypesVar.projectTypes.indexOf(type)).getProjectblocks().get(i).getClass()==this.getClass()) {

                return i;
            }

        }

        return -1;    }

    @Override
    public ProjectTypes getProjectType() {
        return this.type;
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


    @Override
    public String getCode() {
        return "}";
    }
}
