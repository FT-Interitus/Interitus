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

public class Wait implements PlatformSpecificBlock, ArduinoBlock {

    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter waitdauer;



    private final ProjectTypes type;

    public Wait(ProjectTypes arduino) {

        this.type = arduino;
        waitdauer=new Parameter(0, AssetLoader.img_WaitBlock_warteZeit_Parameter, "Warte-Zeit", "Die Zeit die abgewartet werden soll","ms");


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
        return AssetLoader.WaitBlock_smallimage;
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
        return AssetLoader.WaitBlock_description_image;
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
        return type;
    }

    @Override
    public int getWidth() {
        return 80;
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
        return "delay("+this.parameters.get(0).getParameter()+");";
    }
}
