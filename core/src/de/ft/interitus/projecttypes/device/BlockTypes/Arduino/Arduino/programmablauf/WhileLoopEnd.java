package de.ft.interitus.projecttypes.device.BlockTypes.Arduino.Arduino.programmablauf;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.projecttypes.device.BlockTypes.Arduino.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.device.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.device.BlockTypes.BlockTopParameter;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.device.BlockTypes.ProjectTypesVar;

import java.awt.*;
import java.util.ArrayList;

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
    public Texture getImage() {
        return AssetLoader.img_block;
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
    public String getCode() {
        return "}";
    }
}
