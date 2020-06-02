package de.ft.interitus.projecttypes.device.BlockTypes.Arduino.Arduino;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.device.BlockTypes.BlockCategories;
import de.ft.interitus.projecttypes.device.BlockTypes.BlockTopParameter;
import de.ft.interitus.projecttypes.device.BlockTypes.PlatformSpecificBlock;

import java.awt.*;
import java.util.ArrayList;

public class Wait implements PlatformSpecificBlock {

    ArrayList<Parameter> parameters = new ArrayList<>();

    public Wait()  {

    }


    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return null;
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
        return null;
    }

    @Override
    public Texture getSmallImage() {
        return AssetLoader.img_mappe1;
    }

    @Override
    public Texture getImage() {
        return null;
    }
}
