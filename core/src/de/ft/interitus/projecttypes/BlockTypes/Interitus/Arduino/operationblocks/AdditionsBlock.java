/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.BlockTypes.BlockTopParameter;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.utils.ArrayList;

import java.awt.*;

public class AdditionsBlock extends PlatformSpecificBlock implements ArduinoBlock {

    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter Summand_1;
    Parameter Summand_2;
    Parameter Ergebnis;


    public AdditionsBlock(ProjectTypes type) {
        super(type);

        Summand_1=new Parameter("",AssetLoader.Parameter_IO,"1. Summand", "erster Summand", "", new ParameterType("float", false, false), true);
        Summand_2=new Parameter("",AssetLoader.Plug_ZahlParameter,"2. Summand", "zweiter Summand", "", new ParameterType("float", false, false), true);
        Ergebnis=new Parameter("",AssetLoader.Plug_ZahlParameter,"Ergebnis", "Das was bei einer Addition meistens raus kommt", "", new ParameterType("float", true, false), true);
        parameters.add(Summand_1);
        parameters.add(Summand_2);
        parameters.add(Ergebnis);


    }

    @Override
    public String getCode() {
        if( parameters.get(2).getDatawire()!=null){
            return parameters.get(2).getVarName()+ " = "+parameters.get(0).getParameter()+" + "+parameters.get(1).getParameter()+";";

        }else {
            return parameters.get(0).getParameter()+" + "+parameters.get(1).getParameter()+";";
        }
    }

    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return parameters;
    }

    @Override
    public String getName() {
        return "Addition";
    }

    @Override
    public String getdescription() {
        return "Zwei zahlen plus rechnen";
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
        return BlockCategories.Data_Operation;
    }

    @Override
    public Texture getSmallImage() {
        return AssetLoader.DigitalWrite_smallimage;
    }

    @Override
    public Texture getImageRight() {
        return AssetLoader.DigitalWrite_right;
    }

    @Override
    public Texture getImageLeft() {
        return AssetLoader.DigitalWrite_left;
    }

    @Override
    public Texture getImageCenter() {
        return AssetLoader.DigitalWrite_middle;
    }

    @Override
    public Texture getDescriptionImage() {
        return AssetLoader.DigitalWrite_description_image;
    }


    @Override
    public int getWidth() {
        return 200;
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
    public boolean canhasleftconnector() {
        return true;
    }
}
