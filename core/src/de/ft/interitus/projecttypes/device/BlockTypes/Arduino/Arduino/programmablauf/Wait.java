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

public class Wait implements PlatformSpecificBlock, ArduinoBlock {

    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter waitdauer;

    private final ProjectTypes type;

    public Wait(ProjectTypes arduino) {

        this.type = arduino;
        waitdauer=new Parameter(0, AssetLoader.img_WaitBlock_warteZeit_Parameter);
        parameters.add(waitdauer);
    }


    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return parameters;
    }

    @Override
    public String getName() {
        return "Wait";
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
        return BlockCategories.Programm_Sequence;
    }

    @Override
    public Texture getSmallImage() {
        return AssetLoader.img_mappe1;
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

    @Override
    public String getCode() {
        return "delay(1000);";
    }
}
